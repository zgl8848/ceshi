package com.campus.grid.service.impl;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.saftysituation.HiddenDangerInfo;
import com.campus.grid.api.entity.saftysituation.InspectInfo;
import com.campus.grid.mapper.SaftySituationMapper;
import com.campus.grid.mapper.SupervisionMapper;
import com.campus.grid.service.SaftySituationService;
import com.campus.grid.util.InspectHiddenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaftySituationServiceImpl implements SaftySituationService {
	@Autowired
	private SaftySituationMapper saftySituationMapper;
	@Autowired
	private SupervisionMapper supervisionMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;

	@Override
	public HiddenDangerInfo getHiddenDanger() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0) childDepts = null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		Integer total = saftySituationMapper.getAllCount(schoolId, 2, roleType, childDepts);
		Integer untreated = supervisionMapper.getCount(schoolId, 2, roleType, childDepts);
		String completion_rate = InspectHiddenUtils.percentage(total, total - untreated);
		return new HiddenDangerInfo(total + "", untreated + "", completion_rate);
	}

	@Override
	public InspectInfo getInspect() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0) childDepts = null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		Integer total = saftySituationMapper.getAllCount(schoolId, 1, roleType, childDepts);
		Integer question_spot = supervisionMapper.getCount(schoolId, 1, roleType, childDepts);
		String completion_rate = InspectHiddenUtils.percentage(total, total - question_spot);
		return new InspectInfo(total + "", question_spot + "", completion_rate);
	}
}
