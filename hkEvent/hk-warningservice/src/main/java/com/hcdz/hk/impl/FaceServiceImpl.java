package com.hcdz.hk.impl;

import com.hcdz.hk.entity.face.FaceAlarm;
import com.hcdz.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
public class FaceServiceImpl implements FaceService{

	@Override
	public void getAlarmInformation(String alarmInformation) {
		FaceAlarm faceAlarm = JsonUtil.from(alarmInformation, FaceAlarm.class);
		System.out.println(faceAlarm);
	}
	
}
