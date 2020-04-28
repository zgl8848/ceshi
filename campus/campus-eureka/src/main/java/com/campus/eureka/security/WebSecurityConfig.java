package com.campus.eureka.security;

/**
 * @author campus
 * @date 2018/7/12
 */

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				.anyRequest()
				.authenticated().and().httpBasic();
	}
}
