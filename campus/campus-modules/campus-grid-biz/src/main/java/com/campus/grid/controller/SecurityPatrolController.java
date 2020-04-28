package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.service.SecurityPatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/securityPatrol")
public class SecurityPatrolController {
	@Autowired
	SecurityPatrolService securityPatrolService;

	/**
	 * 今日有情况有对应的页面信息查询
	 */
	@RequestMapping("/queryPageData")
	public R queryPageData(String type) {
		return new R<>(securityPatrolService.queryPageData(type));
	}

	/**
	 * 根据网格化名称查找网格化功能
	 */
	@RequestMapping("/queryGridFunction")
	public R queryGridFunction(String reseauId) {
		return new R<>(securityPatrolService.queryGridFunction(reseauId));
	}

	/**
	 * 根据物品类型查找对应的物品标签
	 */
	@RequestMapping("/queryFunctionType")
	public R queryFunctionType(String functionId) {
		return new R<>(securityPatrolService.queryFunctionType(functionId));
	}

	/**
	 * 插入一条安全巡查信息
	 */
	@RequestMapping("/addSecurityInformation")
	public R addSecurityInformation(String reseauId, String functionId, Integer level, String remark, String itemLabel, String eventLabels, String picNames, String mendMan,Integer type) {
		return securityPatrolService.addSecurityInformation(reseauId, functionId, level, remark, itemLabel, eventLabels, picNames, mendMan,type);
	}

	/**
	 * 查询巡查信息
	 */
	@RequestMapping("/querySafetyInspect")
	public R querySafetyInspect(Integer current, Integer size, String date, String isNotarize, String startDate, String endDate, String functionId, Integer level, Integer status, String ebSchoolId, String reseauName,Integer type) {
		return new R<>(securityPatrolService.querySafetyInspect(current, size, date, isNotarize, startDate, endDate, functionId, level, status, ebSchoolId, null, reseauName,type));
	}

	/**
	 * 修改状态
	 */
	@RequestMapping("/alterSafetyInspectStatus")
	public R alterSafetyInspectStatus(String inspectId, String message, String picNames) {
		return new R<>(securityPatrolService.alterSafetyInspectStatus(inspectId, message, picNames));
	}

	/**
	 * app用户登录接受用户的设备标识
	 */
	@RequestMapping("/getRegistrationId")
	public R getRegistrationId(String registrationId) {
		return new R<>(securityPatrolService.getRegistrationId(registrationId));
	}

	/**
	 * app用户退出时删除设备标识
	 */
	@RequestMapping("/deleteRegistrationId")
	public R deleteRegistrationId() {
		return new R<>(securityPatrolService.deleteRegistrationId());
	}

	/**
	 * app整改任务接口
	 */
 	@RequestMapping("/rectificationTask")
	public R rectificationTask(Integer current, Integer size, Integer type){
		return new R<>(securityPatrolService.rectificationTask(current, size, type));
	}
}
