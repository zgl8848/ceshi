package com.campus.common.security.annotation;

import com.campus.common.security.component.CustomResourceServerAutoConfiguration;
import com.campus.common.security.component.CustomSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author campus
 * @date 2018/11/10
 * <p>
 * 资源服务注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({CustomResourceServerAutoConfiguration.class, CustomSecurityBeanDefinitionRegistrar.class})
public @interface EnableCustomResourceServer {

	/**
	 * false：上下文获取用户名
	 * true： 上下文获取全部用户信息
	 *
	 * @return
	 */
	boolean details() default false;
}
