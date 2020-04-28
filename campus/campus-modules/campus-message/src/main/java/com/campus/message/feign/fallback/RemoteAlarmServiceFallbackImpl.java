package com.campus.message.feign.fallback;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;
import com.campus.message.feign.RemoteAlarmService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author campu
 */
@Slf4j
@Component
public class RemoteAlarmServiceFallbackImpl implements RemoteAlarmService {

	@Setter
	private Throwable cause;

	@Override
	public R saveAlarmInfo(Alarm alarm, String from) {
		log.error("feign 存储告警消息失败:{}", alarm, cause);
		return null;
	}

	@Override
	public R saveBlackPerson(Person person, String from) {
		log.error("feign 存储黑名单人脸信息失败:{}", person, cause);
		return null;
	}

	@Override
	public R saveFaceInfo(MongoEmotionPerson mongoEmotionAlarm, String from) {
		log.error("feign 存储表情识别人脸信息失败:{}", mongoEmotionAlarm, cause);
		return null;
	}
}
