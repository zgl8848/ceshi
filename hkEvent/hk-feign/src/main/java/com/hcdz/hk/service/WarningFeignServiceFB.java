package com.hcdz.hk.service;

import com.hcdz.util.JsonResult;
import org.springframework.stereotype.Component;

/**
 * 告警指定降级类
 * @author Administrator
 *
 */
@Component
public class WarningFeignServiceFB implements WarningFeignService{

	@Override
	public JsonResult getStaffAggregation(String json) {
		return JsonResult.err("获取告警信息失败");
	}

}
