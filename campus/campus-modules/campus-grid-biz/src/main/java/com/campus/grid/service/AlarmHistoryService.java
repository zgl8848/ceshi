package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.alarmManagement.AlarmHistory;

public interface AlarmHistoryService extends IService<AlarmHistory> {

	void insertAlarmHistoryInfo(Alarm alarm);
}
