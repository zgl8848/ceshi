package com.campus.grid.api.feign.factory;

import com.campus.grid.api.feign.RemoteReseauService;
import com.campus.grid.api.feign.fallback.RemoteReseauServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/9/1
 */
@Component
public class RemoteReseauServiceFallbackFactory implements FallbackFactory<RemoteReseauService> {

	@Override
	public RemoteReseauService create(Throwable throwable) {
		RemoteReseauServiceFallbackImpl remoteReseauServiceFallback = new RemoteReseauServiceFallbackImpl();
		remoteReseauServiceFallback.setCause(throwable);
		return remoteReseauServiceFallback;
	}
}
