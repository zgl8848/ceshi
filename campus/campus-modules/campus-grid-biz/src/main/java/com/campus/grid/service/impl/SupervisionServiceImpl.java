package com.campus.grid.service.impl;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.mapper.SupervisionMapper;
import com.campus.grid.service.SupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SupervisionServiceImpl implements SupervisionService {
	@Autowired
	private SupervisionMapper supervisionMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;

	@Override
	public Map<Object, Object> getHiddenDanger() {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map = new HashMap<>();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0) childDepts = null;

		String schoolId = SecurityUtils.getUser().getSchoolId();
		Integer count = supervisionMapper.getCount(schoolId, 2, roleType, childDepts);
		map.put("hiddenDanger", count);

		return map;
	}

	@Override
	public Map<Object, Object> getInspect() {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map = new HashMap<>();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0) childDepts = null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		Integer count = supervisionMapper.getCount(schoolId, 1, roleType, childDepts);
		map.put("inspect", count);
		return map;
	}
}
