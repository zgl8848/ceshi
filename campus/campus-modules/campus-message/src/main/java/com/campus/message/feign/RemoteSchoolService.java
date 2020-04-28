package com.campus.message.feign;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.School;
import com.campus.message.feign.factory.RemoteSchoolServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @author campus
 */
@FeignClient(value = ServiceNameConstants.GRID_SERVICE, fallbackFactory = RemoteSchoolServiceFallbackFactory.class)
@Service
public interface RemoteSchoolService {

	/**
	 * 根据告警平台ip获得所属学校编码
	 */
	@GetMapping(value = "/school/getSchoolCodeByPlatformIp/{alarmPlatformIp}")
	R<School> getSchoolCodeByPlatformIp(@PathVariable("alarmPlatformIp") String alarmPlatformIp, @RequestHeader(SecurityConstants.FROM) String from);
}

