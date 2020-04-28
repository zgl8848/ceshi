package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.dataEntity.EmrgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emrg")
public class EmrgController {
	@Autowired
	public EmrgEntity entity;
	/**
	 * 地图数据接口
	 */
	@SuppressWarnings("all")
	@RequestMapping("/map")
	public R map(@RequestParam("date") String date) {
		if (null == date || "".equals(date)) {
			return new R<>(new RuntimeException("date参数为空"));
		}
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		Map<Object, Object> returnmap = new HashMap<>();
		returnmap.put("province_code", entity.getProvince_code());
		returnmap.put("city_code", entity.getCity_code());
		List<Map> list = entity.getListdata();
		returnmap.put("records", list);
		return new R<>(returnmap);
	}

	/**
	 * 应急模块中总体情况数据获取接口
	 */
	@RequestMapping("/overall")
	public R overall() {
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		Map<Object, Object> map = entity.getOverall();
		return new R<>(map);
	}

	/**
	 * 演练类型数据获取接口
	 */
	@RequestMapping("/classify")
	public R classify() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<Map> list = entity.getClasslists();
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("records", list);
		return new R<>(map3);
	}

	/**
	 * 演练次数曲线图数据接口
	 */
	@RequestMapping("/number")
	public R number() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<Map> list = entity.getNumberlists();
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("records", list);
		return new R<>(map3);
	}

	/**
	 * 演练效果曲线图数据接口
	 */
	@RequestMapping("/effect")
	public R effect() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<Map> list = entity.getEffectlists();
		Map<Object, Object> map3 = new HashMap<>();
		map3.put("records", list);
		return new R<>(map3);
	}

	/**
	 * 区域排名
	 */
	@RequestMapping("ranking")
	public R getRanking() {
//		String roleType = SecurityUtils.getUser().getRoleType();
		List<Map> list = entity.getRankinglists();
		Map<Object, Object> map = new HashMap<>();
		map.put("records", list);
		return new R<>(map);
	}
}
