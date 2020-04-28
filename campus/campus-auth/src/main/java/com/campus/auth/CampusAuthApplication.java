package com.campus.auth;


import com.campus.common.security.annotation.EnableCustomFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author campus
 * @date 2018年06月21日
 * 认证授权中心
 */
@SpringCloudApplication
@EnableCustomFeignClients
public class CampusAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusAuthApplication.class, args);
	}
}
