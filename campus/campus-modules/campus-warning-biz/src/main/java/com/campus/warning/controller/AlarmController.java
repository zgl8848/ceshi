package com.campus.warning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.warning.api.feign.BehaviorService;
import com.campus.warning.api.util.JsonResult;
import com.campus.warning.service.impl.BusService;
import com.campus.warning.service.impl.FaceService;
import com.campus.warning.service.impl.FireService;
import com.campus.warning.service.impl.PerimeterService;
import com.campus.warning.service.impl.ThermalService;
import com.campus.warning.service.impl.base.BaseController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@Log4j2
@AllArgsConstructor
public class AlarmController extends BaseController{
	
	/**
	 * 	行为告警接口
	 */
	private final BehaviorService behaviorService;
	
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
	
	/**
	 * 	统一回调接口处理
	 * @param alarmInformation 告警信息json串
	 * @return
	 */
	@RequestMapping("/AlarmInformation")
	public JsonResult getAlarmInformation(@RequestBody String alarmInformation) {
		System.out.println("事件报文："+alarmInformation);
		
		//获取事件类别信息
		JSONObject jsonObject = JSONObject.parseObject(alarmInformation);
		JSONObject parseObject = JSONObject.parseObject(jsonObject.getString("params"));
		String str = parseObject.getString("ability");

		//	1、行为模块业务接口
		if("event_rule".equals(str)||"event_vss".equals(str)) {
			Alarm alarm = getAlarmObj(parseObject);
			behaviorService.saveAlarmInfo(alarm,SecurityConstants.FROM_IN);
		}
		//	2、消防模块
		if("event_fire".equals(str)) {
			fireService.getAlarmInformation(alarmInformation);
		}
		//	3、厨房热成像模块
		if("event_heat".equals(str)) {
			thermalService.getAlarmInformation(alarmInformation);
		}
		//	4、校车安全模块
		if("event_mpc".equals(str)) {
			busService.getAlarmInformation(alarmInformation);
		}
		//	5、治安模块
		if("event_face_match ".equals(str)) {
			faceService.getAlarmInformation(alarmInformation);
		}
		//	6、危化品告警模块
		if("event_ias".equals(str)) {
			perimeterService.getAlarmInformation(alarmInformation);
		}
		log.info("入库成功");
		return JsonResult.ok();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
