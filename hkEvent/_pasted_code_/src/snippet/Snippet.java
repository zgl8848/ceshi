package snippet;

public class Snippet {
	server:
	  port: 8030
	  servlet:
	    context-path: /
	spring:
	  application:
	    name: hk-publicOrderMonitoringService
	  datasource:
	        #引入druid数据源
	    type: com.alibaba.druid.pool.DruidDataSource
	    driver-class-name: com.mysql.jdbc.Driver
	    url: jdbc:mysql://127.0.0.1:3306/jtdb?hkdb=GMT%2B8&useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true
	    username: root
	    password: root
	  mvc:
	    view:
	      prefix: /WEB-INF/views/
	      suffix: .jsp
	#mybatis-plush配置
	mybatis-plus:
	  type-aliases-
}

