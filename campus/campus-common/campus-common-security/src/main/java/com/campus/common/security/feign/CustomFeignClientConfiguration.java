package com.campus.common.security.feign;

import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * @author campus
 * @date 2018/6/22
 * feign 拦截器传递 header 中oauth token，
 * 使用hystrix 的信号量模式
 */
@Configuration
@AllArgsConstructor
@ConditionalOnProperty("security.oauth2.client.client-id")
public class CustomFeignClientConfiguration {
	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
															OAuth2ProtectedResourceDetails resource,
															AccessTokenContextRelay accessTokenContextRelay) {
		return new CustomFeignClientInterceptor(oAuth2ClientContext, resource, accessTokenContextRelay);
	}
}
