package com.campus.message;

import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author eatheryu
 * kafka启动类
 */
@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
@ComponentScan(basePackages = {"com.campus.message"})
public class CampusKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusKafkaApplication.class, args);
	}
}
