package com.campus.grid.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.EmergencyPlan;
import com.campus.grid.mapper.EmergencyPlanMapper;
import com.campus.grid.service.EmergencyPlanService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmergencyPlanServiceImpl extends ServiceImpl<EmergencyPlanMapper, EmergencyPlan> implements IService<EmergencyPlan>, EmergencyPlanService{
	
	private EmergencyPlanMapper emergencyPlanMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveEmergencyPlan(EmergencyPlan emergencyPlan) throws Exception {
		String id = UUID.randomUUID().toString().replace("-", "");
		emergencyPlan.setEmergencyPlanId(id);
		String userId = SecurityUtils.getUser().getId();
		emergencyPlan.setUserId(userId);
		int result = emergencyPlanMapper.saveEmergencyPlan(emergencyPlan);
		return result;
	}

	@Override
	public IPage getEmergencyPlanList(Page page, EmergencyPlan emergencyPlan) throws Exception {
		return emergencyPlanMapper.getEmergencyPlanList(page, emergencyPlan);
	}

	@Override
	public int updateEmergencyPlan(EmergencyPlan emergencyPlan) throws Exception {
		return emergencyPlanMapper.updateEmergencyPlan(emergencyPlan);
	}

	@Override
	public EmergencyPlan getEmergencyPlanInfo(String emergencyPlanId) throws Exception {
		EmergencyPlan ep = emergencyPlanMapper.getEmergencyPlanInfo(emergencyPlanId);
		ep.setSchoolId(ep.getReceivingSchoolId());
		ep.setTypeCode(ep.getEmergencyType());
		return ep;
	}

	@Override
	public List<Map<String, String>> getEmergencyType() throws Exception {
		return emergencyPlanMapper.getEmergencyType();
	}
	

}
