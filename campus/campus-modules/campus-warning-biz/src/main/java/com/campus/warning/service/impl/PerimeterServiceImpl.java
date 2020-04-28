package com.campus.warning.service.impl;

import org.springframework.stereotype.Service;

import com.campus.warning.api.entity.perimeter.PerimeterAlarm;
import com.campus.warning.api.util.JsonUtil;


@Service
public class PerimeterServiceImpl implements PerimeterService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		PerimeterAlarm perimeterAlarm = JsonUtil.from(alarmInformation, PerimeterAlarm.class);
		System.out.println(perimeterAlarm);
	}

}
