package com.hcdz.hk.service;

import com.hcdz.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//接口指定降级类
@FeignClient(name = "hk-eventsubscriptionservice" , fallback = EventSubscriptionFeignServiceFB.class)
public interface EventSubscriptionFeignService {
	@GetMapping("/eventIds")
	public JsonResult getEventIds(@RequestParam Long[] eventIds, String ipPath) ;

	
	
	
}
