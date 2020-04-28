package com.campus.hkmq;

import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
public class CampusHKmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusHKmqApplication.class, args);
	}

}
