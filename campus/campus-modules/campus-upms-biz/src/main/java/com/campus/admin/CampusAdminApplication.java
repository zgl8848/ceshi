package com.campus.admin;


import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author campus
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
public class CampusAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(CampusAdminApplication.class, args);
	}

}
