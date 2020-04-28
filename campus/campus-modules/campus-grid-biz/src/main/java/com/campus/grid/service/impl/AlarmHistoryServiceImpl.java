package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.alarmManagement.AlarmHistory;
import com.campus.grid.mapper.AlarmHistoryMapper;
import com.campus.grid.service.AlarmHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlarmHistoryServiceImpl extends ServiceImpl<AlarmHistoryMapper, AlarmHistory> implements AlarmHistoryService {

	private AlarmHistoryMapper alarmHistoryMapper;

	@Override
	public void insertAlarmHistoryInfo(Alarm alarm) {
		alarmHistoryMapper.insertAlarmHistoryInfo(alarm);
	}
}