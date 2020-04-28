package com.campus.warning.service.impl;

import org.springframework.stereotype.Service;

import com.campus.warning.api.entity.face.FaceAlarm;
import com.campus.warning.api.util.JsonUtil;


@Service
public class FaceServiceImpl implements FaceService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		FaceAlarm faceAlarm = JsonUtil.from(alarmInformation, FaceAlarm.class);
		System.out.println(faceAlarm);
	}
	
}
