package com.campus.grid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {


	@Autowired
	private AuthorityEffectIntercept authorityEffectIntercept;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
		// excludePathPatterns 用户排除拦截
		// <!-- 不拦截路径 exclude-mapping -->
		/******* 权限校验请求拦截器拦截登录后的请求（排除 支付回调、未登录的请求），校验登录后的参数的参数 *******/
		/*
		 * registry.addInterceptor(authorityEffectIntercept).addPathPatterns(
		 * "/emergency/**"); super.addInterceptors(registry);
		 */
	}
}