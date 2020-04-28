package com.campus.auth.config;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.data.tenant.TenantContextHolder;
import com.campus.common.security.component.CustomWebResponseExceptionTranslator;
import com.campus.common.security.service.CustomClientDetailsService;
import com.campus.common.security.service.CustomUser;
import com.campus.common.security.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author campus
 * @date 2018/6/22
 * 认证服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private final DataSource dataSource;
	private final CustomUserDetailsService customUserDetailsService;
	private final AuthenticationManager authenticationManager;
	private final RedisConnectionFactory redisConnectionFactory;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		CustomClientDetailsService clientDetailsService = new CustomClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
				.allowFormAuthenticationForClients()
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancer())
				.userDetailsService(customUserDetailsService)
				.authenticationManager(authenticationManager)
				.reuseRefreshTokens(false)
				.exceptionTranslator(new CustomWebResponseExceptionTranslator());
	}


	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(SecurityConstants.CAMPUS_PREFIX + SecurityConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication) + ":" + TenantContextHolder.getTenantId();
			}
		});
		return tokenStore;
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			final Map<String, Object> additionalInfo = new HashMap<>(8);
			CustomUser customUser = (CustomUser) authentication.getUserAuthentication().getPrincipal();
			additionalInfo.put("user_id", customUser.getId());
			additionalInfo.put("username", customUser.getUsername());
			additionalInfo.put("dept_id", customUser.getDeptId());
			additionalInfo.put("tenant_id", customUser.getTenantId());
			additionalInfo.put("license", SecurityConstants.CAMPUS_LICENSE);
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		};
	}
}
