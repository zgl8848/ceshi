package com.campus.gateway;


import com.campus.common.gateway.annotation.EnableCustomDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author campus
 * @date 2018年06月21日
 * 网关应用
 */
@EnableCustomDynamicRoute
@SpringCloudApplication
public class CampusGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusGatewayApplication.class, args);
	}
}
