package com.campus.codegen;

import com.campus.common.security.annotation.EnableCustomFeignClients;
import com.campus.common.security.annotation.EnableCustomResourceServer;
import com.campus.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hhsep
 * @date 2018/07/29
 * 代码生成模块
 */
@EnableScheduling
@EnableCustomSwagger2
@SpringCloudApplication
@EnableCustomFeignClients
@EnableCustomResourceServer(details = true)
public class CampusCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusCodeGenApplication.class, args);
	}
}
