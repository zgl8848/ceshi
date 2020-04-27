package com.hcdz.hk.controller;

import com.alibaba.fastjson.JSONObject;
import com.hcdz.hk.impl.*;
import com.hcdz.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class AlarmController {
	
	private static Logger log = LoggerFactory.getLogger(AlarmController.class);
	
	/**
	 * 	行为告警接口
	 */
	@Autowired
	private BehaviorService behaviorService;
	
	/**
	 * 	消防告警接口
	 */
	@Autowired
	private FireService fireService;
	
	/**
	 * 厨房(热成像)告警接口
	 */
	@Autowired
	private ThermalService thermalService;
	
	/**
	 * 治安监控（人脸黑名单）报警
	 */
	@Autowired
	private FaceService faceService;
	
	/**
	 * 校车安全报警
	 */
	@Autowired
	private BusService busService;
	
	/**
	 * 危化品告警模块
	 */
	@Autowired
	private PerimeterService perimeterService;
	
	private Integer index = 0;

	/**
	 * 	统一回调接口处理
	 * @param alarmInformation 告警信息json串
	 * @return
	 */
	@RequestMapping("/AlarmInformation")
	public JsonResult getAlarmInformation(@RequestBody String alarmInformation) {
		log.info("接受请求时间----->>>>>   " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.info("接受请求次数----->>>>>   " + index++);
		log.info("事件报文："+alarmInformation);
		System.out.println("事件报文："+alarmInformation);
		
		//获取事件类别信息
		JSONObject jsonObject = JSONObject.parseObject(alarmInformation);
		JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("params"));
		String str = parseObject.getString("ability");
		Integer code = 200;
		//	1、行为模块业务接口
		if("event_rule".equals(str)||"event_vss".equals(str)) {
			code = behaviorService.getAlarmInformation(alarmInformation);
		}
		//	2、消防模块
		if("event_fire".equals(str)) {
			fireService.getAlarmInformation(alarmInformation);
		}
		//	3、厨房热成像模块
		if("event_heat".equals(str)) {
			//thermalService.getAlarmInformation(alarmInformation);
			code = behaviorService.getAlarmInformation(alarmInformation);
		}
		//	4、校车安全模块
		if("event_mpc".equals(str)) {
			busService.getAlarmInformation(alarmInformation);
		}
		//	5、治安模块
		if("event_face_match".equals(str)) {
			//faceService.getAlarmInformation(alarmInformation);
			code = behaviorService.getAlarmInformation(alarmInformation);
		}
		//	6、危化品告警模块
		if("event_ias".equals(str)) {
			perimeterService.getAlarmInformation(alarmInformation);
		}
		if(code == 500){
			log.info("入库失败");
			return JsonResult.err("500","无相关数据!");
		}
		log.info("入库成功");
		return JsonResult.ok();
	}
}
