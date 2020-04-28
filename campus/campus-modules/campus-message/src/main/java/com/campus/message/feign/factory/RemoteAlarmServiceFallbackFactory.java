package com.campus.message.feign.factory;

import com.campus.message.feign.RemoteAlarmService;
import com.campus.message.feign.fallback.RemoteAlarmServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 */
@Component
public class RemoteAlarmServiceFallbackFactory implements FallbackFactory<RemoteAlarmService> {

	@Override
	public RemoteAlarmService create(Throwable throwable) {
		RemoteAlarmServiceFallbackImpl remoteAlarmtServiceFallback = new RemoteAlarmServiceFallbackImpl();
		remoteAlarmtServiceFallback.setCause(throwable);
		return remoteAlarmtServiceFallback;
	}
}
