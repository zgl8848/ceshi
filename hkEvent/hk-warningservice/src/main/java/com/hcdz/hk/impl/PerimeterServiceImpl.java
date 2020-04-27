package com.hcdz.hk.impl;

import com.hcdz.hk.entity.perimeter.PerimeterAlarm;
import com.hcdz.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class PerimeterServiceImpl implements PerimeterService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		PerimeterAlarm perimeterAlarm = JsonUtil.from(alarmInformation, PerimeterAlarm.class);
		System.out.println(perimeterAlarm);
	}

}
