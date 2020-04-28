package com.campus.admin.api.feign.factory;

import com.campus.admin.api.feign.RemoteDictService;
import com.campus.admin.api.feign.fallback.RemoteDictServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/9/1
 */
@Component
public class RemoteDictServiceFallbackFactory implements FallbackFactory<RemoteDictService> {

	@Override
	public RemoteDictService create(Throwable arg0) {
		RemoteDictServiceFallbackImpl remoteDictServiceFallback = new RemoteDictServiceFallbackImpl();
		remoteDictServiceFallback.setCause(arg0);
		return remoteDictServiceFallback;
	}
}
