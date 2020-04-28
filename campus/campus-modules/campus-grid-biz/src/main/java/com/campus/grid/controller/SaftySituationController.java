package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.saftysituation.*;
import com.campus.grid.dataEntity.SaftyEntity;
import com.campus.grid.service.SaftySituationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 安全态势
 *
 * @author CR7
 */
@RestController
@AllArgsConstructor
@RequestMapping("sitn")
public class SaftySituationController {
	@Autowired
	public SaftyEntity entity;
	@Autowired
	private SaftySituationService saftySituationService;

	/**
	 * 安全考核积分排名
	 */
	@GetMapping("ranking")
	public R getRanking() {
		Map returnMap = new HashMap();
		Random rand = new Random();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		List<SchoolRank> list = entity.getRankinglists();
		returnMap.put("records", list);
		return new R<>(returnMap);
	}

	/**
	 * 地图数据
	 */
	@SuppressWarnings("all")
	@GetMapping("map")
	public R getMapdata() {
		Map returnMap = new HashMap();
		returnMap.put("province_code", entity.getMapprovince_code());
		returnMap.put("city_code", entity.getMapcity_code());
		List<MapData> list = entity.getMaplists();
		returnMap.put("records", list);
		return new R<>(returnMap);
	}

	/**
	 * 安全教育学习次数趋势图
	 */
	@SuppressWarnings("all")
	@GetMapping("edu")
	public R getLearnNum() {
		Map returnMap = new HashMap();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		List<EduLearnNum> list = entity.getEdulists();
		returnMap.put("records", list);
		return new R<>(returnMap);
	}

	/**
	 * 考勤信息
	 */
	@SuppressWarnings("all")
	@GetMapping("atnd")
	public R getAtend() {
		Map returnMap = new HashMap();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		List<CheckStand> lists=entity.getAtndlists();
		returnMap.put("teacher_attendance", lists.get(0));
		returnMap.put("student_attendance", lists.get(1));
		return new R<>(returnMap);
	}

	/**
	 * 访客信息
	 */
	@GetMapping("visitor")
	public R getVistor() {
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		VisitorInfo vi = entity.getVisitor();
		return new R<>(vi);
	}

	/**
	 * 隐患处理
	 */
	@GetMapping("hidden_danger")
	public R getHiddenDanger() {
		return new R<>(saftySituationService.getHiddenDanger());
	}

	/**
	 * 隐患排查
	 */
	@GetMapping("inspect")
	public R getInspect() {
		return new R<>(saftySituationService.getInspect());
	}

	/**
	 * 监控接入
	 */
	@GetMapping("/monitor")
	public R getMonitor() {
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		Map<String, String> dataMap =entity.getMonitordata();
		return new R<>(dataMap);
	}
}