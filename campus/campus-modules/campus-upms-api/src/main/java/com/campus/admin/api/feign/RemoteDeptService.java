package com.campus.admin.api.feign;

import com.campus.admin.api.dto.DeptDto;
import com.campus.admin.api.feign.factory.RemoteDeptServiceFallbackFactory;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author campus
 * @date 2018/6/22
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteDeptServiceFallbackFactory.class)
public interface RemoteDeptService {

	/**
	 * 获取本级和子集部门
	 *
	 * @return R
	 */
	@GetMapping("/dept/listChildDepts")
	R<List<String>> listChildDepts();

	/**
	 * 获取本部门信息
	 *
	 * @return R
	 */
	@GetMapping("/dept/{id}")
	R<DeptDto> getById(@PathVariable("id") String id);

	/**
	 * 根据部门id获取部门名称
	 * @param deptId	部门id
	 * @return	部门名称
	 */
	@GetMapping("/dept/getDeptNameById/{deptId}")
	String getDeptNameById(@PathVariable("deptId") String deptId);
}
