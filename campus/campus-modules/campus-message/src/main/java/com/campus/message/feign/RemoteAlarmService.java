package com.campus.message.feign;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;
import com.campus.message.feign.factory.RemoteAlarmServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @author campus
 */
@FeignClient(value = ServiceNameConstants.GRID_SERVICE, fallbackFactory = RemoteAlarmServiceFallbackFactory.class)
@Service
public interface RemoteAlarmService {
	/**
	 * 存储告警事件
	 */
	@PostMapping(value = "/alarm/saveAlarmInfo")
	R saveAlarmInfo(@RequestBody Alarm alarm, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 存储黑名单人脸信息
	 */
	@PostMapping(value = "/alarm/saveBlackPerson")
	R saveBlackPerson(@RequestBody Person person, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 存储表情识别人脸信息
	 * @param mongoEmotionPerson
	 */
	@PostMapping(value = "/alarm/saveFaceInfo")
	R saveFaceInfo(@RequestBody MongoEmotionPerson mongoEmotionPerson, @RequestHeader(SecurityConstants.FROM) String from);

}

