package com.campus.admin.api.feign.factory;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.admin.api.feign.fallback.RemoteDeptServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/9/1
 */
@Component
public class RemoteDeptServiceFallbackFactory implements FallbackFactory<RemoteDeptService> {

	@Override
	public RemoteDeptService create(Throwable throwable) {
		RemoteDeptServiceFallbackImpl remoteDetpServiceFallback = new RemoteDeptServiceFallbackImpl();
		remoteDetpServiceFallback.setCause(throwable);
		return remoteDetpServiceFallback;
	}
}
