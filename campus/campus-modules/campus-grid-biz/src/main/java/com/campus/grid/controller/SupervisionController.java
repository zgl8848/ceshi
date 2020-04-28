package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.service.SupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/supervision")
public class SupervisionController {
	@Autowired
	private SupervisionService supervisionService;

	/**
	 * 学校安防信息接口
	 */
	@RequestMapping("/security")
	public R security() {
		Map<Object, Object> map = new HashMap<>();
		Map<Object, Object> map1 = new HashMap<>();
		map1.put("soleDuty", "12");
		map1.put("pluralism", "5");
		map.put("securityPersonnel", map1);
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("normal", "10");
		map3.put("abnormal", "10");
		map.put("rescueEquipment", map3);
		return new R<>(map);
	}

	/**
	 * 未处理的隐患台账接口
	 */
	@RequestMapping("/hidden_danger")
	public R hiddenDanger() {
		return new R<>(supervisionService.getHiddenDanger());
	}

	/**
	 * 未处理的巡查台账接口
	 */
	@RequestMapping("/inspect")
	public R inspect() {
		return new R<>(supervisionService.getInspect());
	}
}
