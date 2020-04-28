package com.campus.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author campus
 * @date 2018/9/14
 * feign 租户信息拦截
 */
@Configuration
public class CustomFeignTenantConfiguration {
	@Bean
	public RequestInterceptor campusFeignTenantInterceptor() {
		return new CustomFeignTenantInterceptor();
	}
}
