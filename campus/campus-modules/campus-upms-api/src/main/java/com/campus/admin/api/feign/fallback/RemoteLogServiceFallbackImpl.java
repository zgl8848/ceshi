package com.campus.admin.api.feign.fallback;

import com.campus.admin.api.entity.SysLog;
import com.campus.admin.api.feign.RemoteLogService;
import com.campus.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {
	@Setter
	private Throwable cause;

	/**
	 * 保存日志
	 *
	 * @param sysLog
	 * @param from
	 * @return R
	 */
	@Override
	public R<Boolean> saveLog(SysLog sysLog, String from) {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
