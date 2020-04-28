package com.campus.common.security.mobile;

import com.campus.common.security.component.CustomPreAuthenticationChecks;
import com.campus.common.security.service.CustomUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author campus
 * @date 2018/8/5
 * 手机登录校验逻辑
 * 验证码登录、社交登录
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private UserDetailsChecker detailsChecker = new CustomPreAuthenticationChecks();

	@Getter
	@Setter
	private CustomUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

		String principal = mobileAuthenticationToken.getPrincipal().toString();
		UserDetails userDetails = userDetailsService.loadUserBySocial(principal);
		if (userDetails == null) {
			log.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.noopBindAccount",
					"Noop Bind Account"));
		}

		// 检查账号状态
		detailsChecker.check(userDetails);

		MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
		authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MobileAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
