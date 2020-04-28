package com.campus.warning.api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@PropertySource("classpath:properties/SchoolIscIP.properties")
@Component("SchoolAndPortConfig")
@ConfigurationProperties(prefix = "campus")
@Data
public class SchoolAndPortConfig {
	private Map<String, String> schoolCode = new HashMap<>();
}
