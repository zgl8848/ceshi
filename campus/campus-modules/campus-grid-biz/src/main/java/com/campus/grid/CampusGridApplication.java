package com.campus.grid;

import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author campus
 * @date 2018/07/29
 * 网格分配
 */
@EnableScheduling
@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
public class CampusGridApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusGridApplication.class, args);
	}
}
