package com.campus.warning.api.feign.fallback;

import org.springframework.stereotype.Component;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.warning.api.feign.BehaviorService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//@Service
@Component
@Log4j2
public class BehaviorServiceImpl implements BehaviorService{
	
	@Setter
	private Throwable cause;

	@Override
	public R saveAlarmInfo(Alarm alarm, String from) {
		log.error("feign 查询指定字典类型失败:{}", cause);
		return null;
	}
	

}
