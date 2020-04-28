package com.campus.admin.api.feign.factory;

import com.campus.admin.api.feign.RemoteUserService;
import com.campus.admin.api.feign.fallback.RemoteUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/9/1
 */
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

	@Override
	public RemoteUserService create(Throwable throwable) {
		RemoteUserServiceFallbackImpl remoteUserServiceFallback = new RemoteUserServiceFallbackImpl();
		remoteUserServiceFallback.setCause(throwable);
		return remoteUserServiceFallback;
	}
}
