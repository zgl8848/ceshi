package com.campus.common.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @author campus
 * @date 2018/10/4
 * 租户工具类
 */
@UtilityClass
public class TenantContextHolder {

	private final ThreadLocal<Integer> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

	/**
	 * 获取TTL中的租户ID
	 *
	 * @return
	 */
	public Integer getTenantId() {
		return THREAD_LOCAL_TENANT.get();
	}

	/**
	 * TTL 设置租户ID
	 *
	 * @param tenantId
	 */
	public void setTenantId(Integer tenantId) {
		THREAD_LOCAL_TENANT.set(tenantId);
	}

	public void clear() {
		THREAD_LOCAL_TENANT.remove();
	}
}
