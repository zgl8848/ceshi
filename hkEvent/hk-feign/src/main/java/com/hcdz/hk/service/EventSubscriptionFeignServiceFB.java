package com.hcdz.hk.service;

import com.hcdz.util.JsonResult;
import org.springframework.stereotype.Component;

/**
 * 告警指定降级类
 * @author Administrator
 *
 */
@Component
public class EventSubscriptionFeignServiceFB implements EventSubscriptionFeignService{

	@Override
	public JsonResult getEventIds(Long[] eventIds, String ipPath) {
		return JsonResult.err("订阅事件失败");
	}

}
