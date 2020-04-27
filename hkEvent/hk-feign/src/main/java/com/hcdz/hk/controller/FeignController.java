package com.hcdz.hk.controller;

import com.hcdz.hk.service.EventSubscriptionFeignService;
import com.hcdz.hk.service.WarningFeignService;
import com.hcdz.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
	@Autowired
	private EventSubscriptionFeignService eventSubscriptionFeignService;
	@Autowired
	private WarningFeignService warningFeignService;
	
	@PostMapping("/hk/staffAggregation")
	public JsonResult getStaffAggregation(@RequestBody String json) {
		return warningFeignService.getStaffAggregation(json);
	}
	
	@GetMapping("/hk/eventIds")
	public JsonResult getEventIds(Long[] eventIds,String ipPath) {
		return eventSubscriptionFeignService.getEventIds(eventIds, ipPath);
	}

}
