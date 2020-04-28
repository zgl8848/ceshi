package com.campus.admin.api.feign.factory;

import com.campus.admin.api.feign.RemoteTokenService;
import com.campus.admin.api.feign.fallback.RemoteTokenServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/9/4
 */
@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
		remoteTokenServiceFallback.setCause(throwable);
		return remoteTokenServiceFallback;
	}
}
