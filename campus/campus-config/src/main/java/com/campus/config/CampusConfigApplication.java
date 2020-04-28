package com.campus.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author campus
 * @date 2018年06月22日
 * 配置中心
 */
@EnableConfigServer
@SpringCloudApplication
public class CampusConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusConfigApplication.class, args);
	}
}
