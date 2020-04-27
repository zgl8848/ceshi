package com.hcdz.hk.impl;

import com.hcdz.hk.entity.GridAlarm;
import com.hcdz.hk.entity.GridSchool;
import com.hcdz.hk.enums.SchoolTypeEnums;
import com.hcdz.hk.util.AlarmCode;
import com.hcdz.hk.util.InspectHiddenUtils;
import com.hcdz.hk.util.SchoolSource;
import com.hcdz.util.JsonResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class BehaviorServiceImpl extends AlarmCode implements BehaviorService{

/*

	@Autowired
	private GridAlarmMapper gridAlarmMapper;

	@Autowired
	private GridSchoolMapper gridSchoolMapper;
*/

	@Autowired
	private SchoolSource maps;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	@Transactional
	public Integer getAlarmInformation(String alarmInformation) {
		JSONObject jsonObj = JSONObject.fromObject(alarmInformation);
		JSONObject params = jsonObj.getJSONObject("params");
		JSONArray events = params.getJSONArray("events");
		//获取告警学校设备唯一标识：区分不同学校告警
		GridSchool gridSchool = new GridSchool();
		
		//获取告警发生时间属性
		String sendTime = params.getString("sendTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
		Date date1 = null;
		try {
			date1 = format.parse(sendTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDateTime startTime = InspectHiddenUtils.date2LocalDateTime(date1);
		GridAlarm gridAlarm = new GridAlarm();
		gridAlarm.setStartTime(startTime);
		for(int i = 0 ; i<events.size() ; i++) {
			JSONObject event = (JSONObject) events.get(i);
			String schoolCode = SchoolTypeEnums.getSchoolCode(event.getString("srcIndex"));
			//1.1、根据学校设备id查询学校来源信息
			//gridSchool = gridSchoolMapper.findGridSchool(schoolCode);

			//使用MongoTemplate的对象来查询信息
			Query query = new Query(Criteria.where("school_code").is(schoolCode));

			//测试出现空指针后,返回的信息
			//Query query = new Query(Criteria.where("school_code").is("00000099"));
			gridSchool = mongoTemplate.findOne(query,GridSchool.class);

			if(gridSchool!=null){
				String eventType = event.getString("eventType");
				gridAlarm.setAlarmType(eventType);
				gridAlarm.setAlarmModule(moduleMap.get(eventType)); //告警模块

				//获取告警来源、告警信息、告警描述职责
				String srcType = event.getString("srcType");
				gridAlarm.setAlarmSource(srcType);
				gridAlarm.setAlarmMsg(srcType);
				gridAlarm.setAlarmDesc(srcType);

				Integer status = event.getInt("status");
				gridAlarm.setAlarmState(status); //告警状态
				Object eventLvl = event.get("eventLvl");
				if(eventLvl!=null) { //告警级别
					gridAlarm.setAlarmLevel(Integer.parseInt((String) eventLvl));
				}
				gridAlarm.setAlarmLevel(1);

				String eventId = UUID.randomUUID().toString().replaceAll("-", "");
				gridAlarm.setId(eventId); //告警事件ID
				JSONObject data = event.getJSONObject("data");
				if(!data.isNullObject()) {
					String ipAddress = data.getString("ipAddress");
					gridAlarm.setDeviceIp(ipAddress); //获取IP地址

					String alarmTitle = data.getString("eventType");
					gridAlarm.setAlarmTitle(alarmTitle); //获取告警标题
				}else {
					gridAlarm.setAlarmTitle("==="); //获取告警标题
				}


				gridAlarm.setSchoolId(gridSchool.getSchoolId()); //获取学校ID
				gridAlarm.setPicUrl(data.getJSONArray("alarmResult").getJSONObject(0).getString("image")); //设置图片URL

				gridAlarm.setVedioUrl(gridSchool.getPlatform_url()); //设置视频URL

				gridAlarm.setDataType("2"); //设置告警类型

				//gridAlarmMapper.insert(gridAlarm); //数据入库
				//数据入库 --> MongoDB
				mongoTemplate.insert(gridAlarm);

			}else{
				return 500;
			}

		}
		return 200;
	}
	
}
