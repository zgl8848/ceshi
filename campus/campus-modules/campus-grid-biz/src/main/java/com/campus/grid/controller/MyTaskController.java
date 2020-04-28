package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.HiddenDanger;
import com.campus.grid.mapper.HiddenDangerMapper;
import com.campus.grid.service.HiddenDangerService;
import com.campus.grid.service.SecurityPatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MyTaskController {
	@Autowired
	private SecurityPatrolService securityPatrolService;

	/**+
	 * 整改任务接口
	 * @param type
	 * @return
	 */
	@RequestMapping("/rectification_task")
	public R myTask(Integer current, Integer size, Integer type){
		return new R<>(securityPatrolService.recordDetails(current, size, type,null,SecurityUtils.getUser().getId(),null));
	}

	/**
	 *任务条数展示接口
	 */
	@RequestMapping("/recordNumber")
	public R recordNumber(){
		return new R<>(securityPatrolService.recordNumber());
	}

	/**
	 * 任务记录详情接口
	 */
	@RequestMapping("/my_inspect")
	public R recordDetails(Integer current, Integer size, Integer type,Integer status){
		return new R<>(securityPatrolService.recordDetails(current, size, type,SecurityUtils.getUser().getId(),null,status));
	}
}
