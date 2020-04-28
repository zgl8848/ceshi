package com.campus.warning.api.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "campus")
public class SchoolSource {
	private Map<String, String> schoolCode;
}
