package com.hcdz.hk.impl;

import com.hcdz.hk.entity.firecontrol.FireAlarm;
import com.hcdz.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class FireServiceImpl implements FireService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		FireAlarm fireAlarm = JsonUtil.from(alarmInformation, FireAlarm.class);
		System.out.println(fireAlarm);
	}
	
}
