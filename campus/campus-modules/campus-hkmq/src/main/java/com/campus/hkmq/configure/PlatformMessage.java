package com.campus.hkmq.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @author eatheryu
 * 平台IP以及port配置类
 */
@Configuration
@ConfigurationProperties("platformmessage")
@Data
public class PlatformMessage {
	private List<IpAndPort> info;
}
