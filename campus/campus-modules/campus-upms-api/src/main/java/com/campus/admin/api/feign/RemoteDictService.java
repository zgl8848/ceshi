package com.campus.admin.api.feign;

import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;
import com.campus.common.core.util.R;
import com.campus.admin.api.feign.factory.RemoteDictServiceFallbackFactory;
import com.campus.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteDictServiceFallbackFactory.class)
public interface RemoteDictService {
	/**
	 * 查询安全岗位（post_type）的字典信息
	 *
	 * @param value 用户名
	 * @return R
	 */
	@GetMapping("/dict/value/{value}")
	DictInfo info(@PathVariable("value") String value);

	/**
	 * 通过字典类型查询字典信息
	 *
	 * @param type 	 	用户名
	 * @param value     调用标志
	 * @return R
	 */
	@GetMapping("/dict/info/{type}/{value}")
	DictInfo info(@PathVariable("type") String type, @PathVariable("value") String value);

	/**
	 * 根据字典类型获取字典信息
	 *
	 * @param type 用户名
	 * @return list
	 */
	@GetMapping("/dict/type/{type}")
	R<List<SysDict>> list(@PathVariable("type") String type);

}
