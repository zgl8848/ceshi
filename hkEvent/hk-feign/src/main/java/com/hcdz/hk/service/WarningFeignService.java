package com.hcdz.hk.service;

import com.hcdz.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//接口指定降级类
@FeignClient(name ="hk-warningservice" , fallback = WarningFeignServiceFB.class )
public interface WarningFeignService {
	
	@PostMapping("/staffAggregation")
	public JsonResult getStaffAggregation(@RequestBody String json) ;
}
