package com.campus.warning.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.warning.api.feign.factory.BehaviorServiceFallbackFactory;

@FeignClient(value = ServiceNameConstants.GRID_SERVICE,fallbackFactory = BehaviorServiceFallbackFactory.class)
public interface BehaviorService {
	
	@Async("myTaskAsyncPool")
	@PostMapping(value = "/alarm/saveAlarmInfo")
	R saveAlarmInfo(@RequestBody Alarm alarm, @RequestHeader(SecurityConstants.FROM) String from);
	
}
