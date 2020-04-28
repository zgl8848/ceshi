package com.campus.auth.handler;

import com.campus.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/10/8
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
	}
}
