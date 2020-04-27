package com.hcdz.hk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@PropertySource("classpath:properties/SchoolEvent.properties")
@Component("SchoolAndEventConfig")
@ConfigurationProperties(prefix = "event")
@Data
public class SchoolAndEventConfig {
	private Map<String , String > schoolCodde ;
}
