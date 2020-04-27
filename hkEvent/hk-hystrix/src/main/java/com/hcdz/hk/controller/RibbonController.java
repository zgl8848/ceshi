package com.hcdz.hk.controller;

import com.hcdz.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {
	@Autowired
	private RestTemplate rt;

	@PostMapping("/hk/staffAggregation")
	@HystrixCommand(fallbackMethod = "getStaffAggregationFB")
	public JsonResult getStaffAggregation(@RequestBody String json) {
		//发送 post 请求
		return rt.postForObject("http://hk-warningservice/staffAggregation", json,JsonResult.class);
	}
	@GetMapping("/hk/eventIds")
	@HystrixCommand(fallbackMethod = "getEventIdsFB")
	public JsonResult getEventIds(Long[] eventIds,String ipPath) {
		//向指定微服务地址发送 get 请求，并获得该服务的返回结果
		return rt.getForObject("http://hk-eventsubscriptionservice/eventIds", JsonResult.class,eventIds,ipPath);
	}

	
	//添加降级方法
	public JsonResult getStaffAggregationFB(@RequestBody String json) {
		return JsonResult.err("告警信息发送失败");
	}
	public JsonResult getEventIdsFB(Long[] eventIds,String ipPath) {
		//向指定微服务地址发送 get 请求，并获得该服务的返回结果
		return JsonResult.err("时间订阅失败");
	}
	
}
