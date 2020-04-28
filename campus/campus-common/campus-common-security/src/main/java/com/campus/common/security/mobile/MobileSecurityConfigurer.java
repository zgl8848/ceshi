package com.campus.common.security.mobile;

import com.campus.common.security.component.ResourceAuthExceptionEntryPoint;
import com.campus.common.security.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/8/5
 * 手机号登录配置入口
 */
@Getter
@Setter
@Component
public class MobileSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AuthenticationEventPublisher defaultAuthenticationEventPublisher;
	private AuthenticationSuccessHandler mobileLoginSuccessHandler;
	private CustomUserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
		mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		mobileAuthenticationFilter.setAuthenticationSuccessHandler(mobileLoginSuccessHandler);
		mobileAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
		mobileAuthenticationFilter.setAuthenticationEntryPoint(new ResourceAuthExceptionEntryPoint(objectMapper));

		MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
		mobileAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(mobileAuthenticationProvider)
				.addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
