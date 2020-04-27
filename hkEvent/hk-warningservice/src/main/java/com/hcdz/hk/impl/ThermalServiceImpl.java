package com.hcdz.hk.impl;

import com.hcdz.hk.entity.thermal.ThermalAlarm;
import com.hcdz.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class ThermalServiceImpl implements ThermalService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		ThermalAlarm thermalAlarm = JsonUtil.from(alarmInformation, ThermalAlarm.class);
		System.out.println(thermalAlarm);
	}
	
}
