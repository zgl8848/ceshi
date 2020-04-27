package com.hcdz.hk.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
		import org.springframework.stereotype.Component;

		import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "campus")
public class SchoolSource {
	private Map<String, String> schoolCode;
}
