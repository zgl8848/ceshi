package com.campus.common.log.event;

import com.campus.admin.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author campus
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}
}
