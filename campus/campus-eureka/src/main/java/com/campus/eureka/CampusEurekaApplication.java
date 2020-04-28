package com.campus.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author campus
 * @date 2018年06月21日
 * 服务中心
 */
@EnableEurekaServer
@SpringBootApplication
public class CampusEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusEurekaApplication.class, args);
	}
}
