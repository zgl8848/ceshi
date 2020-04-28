package com.campus.warning.api.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@PropertySource("classpath:properties/SchoolEvent.properties")
@Component("SchoolAndEventConfig")
@ConfigurationProperties(prefix = "event")
@Data
public class SchoolAndEventConfig {
	private Map<String , String > schoolCodde ;
}
