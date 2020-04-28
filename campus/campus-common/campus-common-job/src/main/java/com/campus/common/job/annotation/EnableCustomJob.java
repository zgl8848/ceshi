package com.campus.common.job.annotation;

import com.campus.common.job.ElasticJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author campus
 * @date 2018/7/24
 * 开启campus job
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ElasticJobAutoConfiguration.class})
public @interface EnableCustomJob {
}
