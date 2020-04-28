package com.campus.common.swagger.annotation;

import com.campus.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author campus
 * @date 2018/7/21
 * 开启campus swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableCustomSwagger2 {
}
