package com.campus.warning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.warning.service.impl.SubscribeService;

@Controller
public class SubscribeController {

	@Autowired
	private SubscribeService subscribeService;

	/**
	 * 按事件类型订阅事件
	 * @param eventTypes	获取事件类型的请求参数的
	 * @param eventDest		获取回调接口的ip+port
	 * @param subType		订阅类型，0-订阅原始事件，1-联动事件，2-原始事件和联动事件，不填使用默认值0
	 * @param eventLvl		事件等级，0-未配置，1-低，2-中，3-高 ;此参数无效，使用默认值0
	 * @return				
	 */
	@RequestMapping("/eventSubscriptions")
	@ResponseBody
	public String eventSubscriptions(String schoolCodeId) {
		return subscribeService.eventSubscriptions(schoolCodeId);
	}
	
	/**
	 * 按事件类型取消订阅
	 * @param eventTypes	事件类型
	 * @return
	 */
	@RequestMapping("/unsubscriptionEvent")
	@ResponseBody
	public String UnsubscriptionEvent(String schoolCodeId) {
		return subscribeService.UnsubscriptionEvent(schoolCodeId);
	}
	
	/**
	 * 查看此服务器订阅的事件
	 * @return
	 */
	@RequestMapping("/viewSubscriptionEvents")
	@ResponseBody
	public String viewSubscriptionEvents(String schoolCodeId) {
		return subscribeService.viewSubscriptionEvents(schoolCodeId);
	}
}
