package com.campus.admin.api.feign.fallback;

import com.campus.admin.api.dto.DeptDto;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author campus
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteDeptServiceFallbackImpl implements RemoteDeptService {
	@Setter
	private Throwable cause;

	/**
	 * 获取本级和子集部门
	 *
	 * @return R
	 */
	@Override
	public R<List<String>> listChildDepts() {
		log.error("feign 查询本级及子集部门列表失败:{}", cause);
		return null;
	}

	/**
	 * 查询部门信息
	 *
	 * @return R
	 */
	@Override
	public R<DeptDto> getById(String id) {
		log.error("feign 查询本部门信息失败:{}", cause);
		return null;
	}

	/**
	 * 根据部门id获取部门名称
	 * @param deptId	部门id
	 * @return	部门名称
	 */
	@Override
	public String getDeptNameById(String deptId) {
		log.error("feign 查询部门名称失败:{}", cause);
		return null;
	}
}
