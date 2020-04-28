//package com.campus.grid.config;
//
//import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//@Component
//public class YamlConfig {
//	@Bean
//	public PropertySourcesPlaceholderConfigurer yaml() {
//		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//		yaml.setResources(new ClassPathResource("dynamicData.yaml"));
//		configurer.setProperties(yaml.getObject());
//		return configurer;
//	}
//}