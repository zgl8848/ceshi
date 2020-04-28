package com.campus.grid.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.EmergencyPlan;

public interface EmergencyPlanMapper extends BaseMapper<EmergencyPlan>{
	
	/**
	 * 保存应急预案信息
	 * @param emergencyPlan
	 * @return
	 */
	int saveEmergencyPlan(EmergencyPlan emergencyPlan);
	
	/**
	 * 编辑应急预案
	 * @param emergencyPlan
	 * @return
	 * @throws Exception
	 */
	int updateEmergencyPlan(EmergencyPlan emergencyPlan);
	
	/**
	 * 分页查询应急预案
	 * @param page
	 * @param emergencyPlan
	 * @return
	 * @throws Exception
	 */
	IPage getEmergencyPlanList(Page page,@Param("emergencyPlan")EmergencyPlan emergencyPlan);
	
	
	/**
	 * 获取应急预案详情
	 * @param emergencyPlanId
	 * @return
	 * @throws Exception
	 */
	EmergencyPlan getEmergencyPlanInfo(@Param("emergencyPlanId")String emergencyPlanId);
	
	/**
	 *  获取预案类型
	 * @return
	 */
	@Select("SELECT code typeCode,name FROM emergency_plan_type")
	List<Map<String,String>> getEmergencyType();
	
}
