package com.campus.message.feign.factory;

import com.campus.message.feign.RemoteSchoolService;
import com.campus.message.feign.fallback.RemoteSchoolServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 */
@Component
public class RemoteSchoolServiceFallbackFactory implements FallbackFactory<RemoteSchoolService> {

	@Override
	public RemoteSchoolService create(Throwable throwable) {
		RemoteSchoolServiceFallbackImpl remoteSchoolServiceFallback = new RemoteSchoolServiceFallbackImpl();
		remoteSchoolServiceFallback.setCause(throwable);
		return remoteSchoolServiceFallback;
	}
}
