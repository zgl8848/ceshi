package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.saftysupervise.*;
import com.campus.grid.dataEntity.SupvEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.util.*;

/**
 * 安全督查
 *
 * @author CR7
 */

@RestController
@AllArgsConstructor
@RequestMapping("/supv")
public class SaftySuperviseController {
	@Autowired
	public SupvEntity entity;
	/**
	 * 地图数据
	 *
	 * @return
	 */
	@SuppressWarnings("all")
	@GetMapping("/map")
	public R getMapData(String date) {
		if (null == date || "".equals(date)) {
			return new R<>(new RuntimeException("date参数为空"));
		}
		Map returnMap = new HashMap();
		returnMap.put("province_code", entity.getProvince_code());
		returnMap.put("city_code", entity.getCity_code());
		List<MapData> list = entity.getSupvlists();
		returnMap.put("records", list);
		return new R<>(returnMap);
	}

	/**
	 * 校园总体情况
	 */
	@SuppressWarnings("all")
	@GetMapping("overall")
	public R getOverAll() {
		Random rand = new Random();
		NumberFormat nt = NumberFormat.getPercentInstance();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		OverAll oa = entity.getSupvoa();
		return new R<>(oa);
	}

	/**
	 * 检查分类
	 */
	@SuppressWarnings("all")
	@GetMapping("classify")
	public R getClassify() {
		Map returnMap = new HashMap();
		Random rand = new Random();
		List<Classify> list = entity.getSupvclassifylist();
		returnMap.put("records", list);
		return new R<>(returnMap);
	}

	/**
	 * 曲线图数据
	 */

	@GetMapping("trend_chart")
	public R getTrendChart() {
		Map returnMap = new HashMap();
		Random rand = new Random();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		List<TrendChart> list = entity.getChartlists();
		List<EveryData> every = entity.getCharteverydata();
		for(int j=0;j<list.size();j++){
			list.get(j).setInspectData(every);
		}
		returnMap.put("records", list);
		return new R<>(returnMap);
	}
}	
