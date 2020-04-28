package com.campus.warning.api.feign.factory;

import org.springframework.stereotype.Component;

import com.campus.warning.api.feign.BehaviorService;
import com.campus.warning.api.feign.fallback.BehaviorServiceImpl;

import feign.hystrix.FallbackFactory;
/**
 * 
 * @author lc
 * @date 2019年11月27日 下午3:48:29
 * @desc 告警入库feign异常处理类
 */
@Component
public class BehaviorServiceFallbackFactory implements FallbackFactory<BehaviorService>{

	@Override
	public BehaviorService create(Throwable cause) {
		BehaviorServiceImpl bs = new BehaviorServiceImpl();
		bs.setCause(cause);
		return bs;
	}

}
