package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.dataEntity.FoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Autowired
	public FoodEntity entity;
	/**
	 * 地图数据接口
	 */
	@GetMapping("/map")
	public R map(String date) {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("records", entity.getDatalist());
		map3.put("province_code", entity.getMapprovince());
		map3.put("city_code", entity.getCitycode());
		return new R<>(map3);

	}


	/**
	 * 食品留样接口
	 */
	@RequestMapping("/sample")
	public R sample() {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map = entity.getSampledata();
		return new R<>(map);
	}

	/**
	 * 营养分布接口
	 */
	@RequestMapping("/nutrition")
	public R nutrition() {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map3 = new HashMap<>();
			Map<Object, Object> map = new HashMap<>();
			map3.put("records", entity.getRydata());
		    return new R<>(map3);
	}

	/**
	 * 从业人员接口
	 */
	@RequestMapping("/cert")
	public R cert() {
		String roleType = SecurityUtils.getUser().getRoleType();
		    Map<Object, Object> map = new HashMap<>();
			map.put("records", entity.getCertlists());
			return new R<>(map);
	}


	/**
	 * 食品留样接口
	 */
	@RequestMapping("/trend_chart")
	public R trendChart() {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<Object, Object> map3 = new HashMap<>();
			Map<Object, Object> map = new HashMap<>();
			map.put("sampleType", "1");
			map.put("sampleName", "预计留样");
			map.put("sampleData", entity.getTrendlist());

			Map<Object, Object> map2 = new HashMap<>();
			map2.put("sampleType", "1");
			map2.put("sampleName", "实际留样");
			map2.put("sampleData", entity.getTrendlist());

			List<Map> list = new ArrayList<>();
			list.add(map);
			list.add(map2);
			map3.put("records", list);
			return new R<>(map3);
		}
}
