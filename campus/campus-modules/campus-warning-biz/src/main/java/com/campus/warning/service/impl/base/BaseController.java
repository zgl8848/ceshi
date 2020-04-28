package com.campus.warning.service.impl.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.warning.api.entity.GridSchool;
import com.campus.warning.api.util.AlarmCode;
import com.campus.warning.api.util.InspectHiddenUtils;
import com.campus.warning.api.util.SchoolSource;
import com.campus.warning.mapper.GridSchoolMapper;

/**
 * 
 * @author lc
 * @Date  2019年12月2日
 * @Desc  基础控制器(处理控制器业务)
 *
 */
@Component
public class BaseController extends AlarmCode{
	
	@Autowired
	private SchoolSource maps;
	
	@Autowired
	private GridSchoolMapper  gridSchoolMapper;
	
	@Transactional
	public Alarm getAlarmObj(JSONObject params) {
		JSONArray events = params.getJSONArray("events");
		/**
		 * 	获取告警学校设备唯一标识：区分不同学校告警
		 */
		GridSchool gridSchool = new GridSchool();
		Alarm alarm = new Alarm();
		/**
		 * 1、获取告警发生时间属性
		 */
		String sendTime = params.getString("sendTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
		Date date1 = null;
		try {
			date1 = format.parse(sendTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		alarm.setStartTime(date1);
		for(int i = 0 ; i<events.size() ; i++) {
			JSONObject event = (JSONObject) events.get(i);
			String srcIndex = event.getString("srcIndex");
			String schoolCode = maps.getSchoolCode().get(srcIndex);
			schoolCode = String.format("%0" + 8 + "d", Integer.parseInt(schoolCode) + 1);
			//1.1、根据学校设备id查询学校来源信息
			gridSchool = gridSchoolMapper.findGridSchool(schoolCode);
			
			String eventType = event.getString("eventType");
			alarm.setAlarmType(eventType);
			alarm.setAlarmModule(moduleMap.get(eventType)); //告警模块
			/**
			 * 3、获取告警来源、告警信息、告警描述
			 */
			String srcType = event.getString("srcType");
			alarm.setAlarmSource(srcType);
			alarm.setAlarmMsg(srcType);
			alarm.setAlarmDesc(srcType);

			/**
			 * 	获取告警状态
			 */
			String status = event.getString("status");
			alarm.setAlarmState(status);
			/**
			 * 	获取告警级别
			 */
			Object eventLvl = event.get("eventLvl");
			if(eventLvl!=null) {
				alarm.setAlarmLevel(eventLvl.toString());
			}
			alarm.setAlarmLevel("1");
			/**
			 * 	获取事件id
			 */
			String eventId = UUID.randomUUID().toString().replaceAll("-", "");
			alarm.setId(eventId);

			JSONObject data = event.getJSONObject("data");

			/**
			 * 	获取设备ip地址
			 */
			String ipAddress = data.getString("ipAddress");
			alarm.setDeviceIp(ipAddress);
			/**
			 * 	获取告警标题
			 */
			String alarmTitle = data.getString("eventType");
			alarm.setAlarmTitle(alarmTitle);
			/**
			 * 	设置学校id
			 */
			alarm.setSchoolId(gridSchool.getSchoolId());
			/**
			 * 	设置图片URL
			 */
			alarm.setPicUrl("campus-b03453668128492e89355a5c6b936057.jpg");
			/**
			 * 	设置视频URL
			 */
			alarm.setVideoUrl(gridSchool.getPlatform_url());
			/**
			 * 	设置告警类型
			 */
			alarm.setDataType("2");
		}
		
		return alarm;
	}
	
}
