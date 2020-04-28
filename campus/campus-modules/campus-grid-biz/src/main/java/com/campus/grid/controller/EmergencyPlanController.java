package com.campus.grid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.EmergencyPlan;
import com.campus.grid.service.EmergencyPlanService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/emergency")
@Api(value = "emergency", description = "应急预案模块")
@Slf4j
public class EmergencyPlanController {
	
	private EmergencyPlanService emergencyPlanService;
	
	/**
	 * 保存应急预案信息
	 * @param emergencyPlan
	 * @return
	 */
	@PostMapping(path="/saveEmergencyPlan")
	public R saveEmergencyPlan(@RequestBody EmergencyPlan emergencyPlan) {
		try {
			return new R<>(emergencyPlanService.saveEmergencyPlan(emergencyPlan));
		} catch (Exception e) {
			log.error("保存应急预案信息出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("保存应急预案信息出错!");
		}
	}
	
	/**
	 * 更改应急预案信息
	 * @param emergencyPlan
	 * @return
	 */
	@PostMapping(path="/updateEmergencyPlan")
	public R updateEmergencyPlan(@RequestBody EmergencyPlan emergencyPlan) {
		try {
			return new R<>(emergencyPlanService.updateEmergencyPlan(emergencyPlan));
		} catch (Exception e) {
			log.error("更改应急预案信息出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("更改应急预案信息出错!");
		}
	}
	
	/**
	 * 分页查询应急预案
	 * @param page
	 * @param emergencyPlan
	 * @return
	 */
	@GetMapping("/page")
	public R getMessagePage(Page page,EmergencyPlan emergencyPlan) {
		try {
			return new R<>(emergencyPlanService.getEmergencyPlanList(page, emergencyPlan));
		} catch (Exception e) {
			log.error("获取应急预案列表出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取应急预案列表出错!");
		}
	}
	
	/**
	 * 获取应急预案详情
	 * @param page
	 * @param emergencyPlan
	 * @return
	 */
	@GetMapping("/getEmergencyPlanInfo")
	public R getEmergencyPlanInfo(String emergencyPlanId) {
		try {
			return new R<>(emergencyPlanService.getEmergencyPlanInfo(emergencyPlanId));
		} catch (Exception e) {
			log.error("获取应急预案详情出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取应急预案详情出错!");
		}
	}
	
	/**
	 *  获取预案类型列表
	 * @return
	 */
	@GetMapping("/getEmergencyType")
	public R getEmergencyType() {
		try {
			return new R<>(emergencyPlanService.getEmergencyType());
		} catch (Exception e) {
			log.error("获取预案类型列表出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取预案类型列表出错!");
		}
	}
	
}
