package com.campus.message.feign.fallback;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.School;
import com.campus.message.feign.RemoteSchoolService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author campu
 */
@Slf4j
@Component
public class RemoteSchoolServiceFallbackImpl implements RemoteSchoolService {
	@Setter
	private Throwable cause;

	@Override
	public R<School> getSchoolCodeByPlatformIp(String alarmPlatformIp, String from) {
		log.error("feign 插入设备信息数据失败:{}", alarmPlatformIp, cause);
		return null;
	}
}
