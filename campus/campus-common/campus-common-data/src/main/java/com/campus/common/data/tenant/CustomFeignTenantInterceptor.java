package com.campus.common.data.tenant;

import com.campus.common.core.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author campus
 * @date 2018/9/14
 */
@Slf4j
public class CustomFeignTenantInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (TenantContextHolder.getTenantId() == null) {
			log.error("TTL 中的 租户ID为空，feign拦截器 >> 增强失败");
			return;
		}
		requestTemplate.header(CommonConstants.TENANT_ID, TenantContextHolder.getTenantId().toString());
	}
}
