package com.campus.grid.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.EmergencyPlan;

public interface EmergencyPlanService extends IService<EmergencyPlan> {

	/**
	 * 保存应急预案信息
	 * @param emergencyPlan
	 * @return
	 */
	int saveEmergencyPlan(EmergencyPlan emergencyPlan) throws Exception;
	
	/**
	 * 编辑应急预案
	 * @param emergencyPlan
	 * @return
	 * @throws Exception
	 */
	int updateEmergencyPlan(EmergencyPlan emergencyPlan) throws Exception;
	
	/**
	 * 分页查询应急预案
	 * @param page
	 * @param emergencyPlan
	 * @return
	 * @throws Exception
	 */
	IPage getEmergencyPlanList(Page page,EmergencyPlan emergencyPlan)throws Exception;
	
	/**
	 * 获取应急预案详情
	 * @param emergencyPlanId
	 * @return
	 * @throws Exception
	 */
	EmergencyPlan getEmergencyPlanInfo(String emergencyPlanId)throws Exception;
	
	/**
	 *  获取预案类型
	 * @return
	 * @throws Exception
	 */
	List<Map<String,String>> getEmergencyType()throws Exception;
}
