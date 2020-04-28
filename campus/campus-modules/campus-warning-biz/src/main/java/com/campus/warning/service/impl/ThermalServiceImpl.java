package com.campus.warning.service.impl;

import org.springframework.stereotype.Service;

import com.campus.warning.api.entity.thermal.ThermalAlarm;
import com.campus.warning.api.util.JsonUtil;


@Service
public class ThermalServiceImpl implements ThermalService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		ThermalAlarm thermalAlarm = JsonUtil.from(alarmInformation, ThermalAlarm.class);
		System.out.println(thermalAlarm);
	}
	
}
