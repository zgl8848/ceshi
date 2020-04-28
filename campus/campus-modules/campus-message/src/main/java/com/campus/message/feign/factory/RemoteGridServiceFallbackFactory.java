package com.campus.message.feign.factory;

import com.campus.message.feign.RemoteGridService;
import com.campus.message.feign.fallback.RemoteGridServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 */
@Component
public class RemoteGridServiceFallbackFactory implements FallbackFactory<RemoteGridService> {

	@Override
	public RemoteGridService create(Throwable throwable) {
		RemoteGridServiceFallbackImpl remoteUserServiceFallback = new RemoteGridServiceFallbackImpl();
		remoteUserServiceFallback.setCause(throwable);
		return remoteUserServiceFallback;
	}
}
