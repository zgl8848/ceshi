package com.campus.common.gateway.annotation;

import com.campus.common.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author campus
 * @date 2018/11/5
 * <p>
 * 开启campus 动态路由
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableCustomDynamicRoute {
}
