package com.campus.warning.service.impl;

import org.springframework.stereotype.Service;

import com.campus.warning.api.entity.firecontrol.FireAlarm;
import com.campus.warning.api.util.JsonUtil;

@Service
public class FireServiceImpl implements FireService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		FireAlarm fireAlarm = JsonUtil.from(alarmInformation, FireAlarm.class);
		System.out.println(fireAlarm);
	}
	
}
