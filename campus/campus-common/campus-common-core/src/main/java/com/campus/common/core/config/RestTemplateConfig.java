package com.campus.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author campus
 * @date 2018/8/16
 * RestTemplate
 */
@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
