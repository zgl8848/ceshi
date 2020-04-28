package com.campus.warning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;

@EnableAsync
@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
public class HkWarningserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HkWarningserviceApplication.class, args);
	}

}
