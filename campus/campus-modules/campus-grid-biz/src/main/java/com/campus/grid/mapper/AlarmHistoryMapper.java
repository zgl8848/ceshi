package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.alarmManagement.AlarmHistory;

public interface AlarmHistoryMapper extends BaseMapper<AlarmHistory> {

	void insertAlarmHistoryInfo(Alarm alarm);
}