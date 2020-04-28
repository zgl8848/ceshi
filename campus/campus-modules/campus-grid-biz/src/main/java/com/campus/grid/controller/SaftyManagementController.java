package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.service.SaftyManagementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 安全管理
 *
 * @author CR7
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mgt")
public class SaftyManagementController {
	@Autowired
	private SaftyManagementService saftyManagementService;

	/**
	 * 安全管理地图数据
	 */
	@SuppressWarnings("all")
	@RequestMapping("/map")
	public R getManageMap(String date) {
		return new R<>(saftyManagementService.getManageMap(date));
	}

	/**
	 * 整改情况数据
	 */
	@SuppressWarnings("all")
	@GetMapping("overall")
	public R geOverAll() {
		return new R<>(saftyManagementService.geOverAll());
	}

	/**
	 * 隐患分布数据获取
	 */
	@SuppressWarnings("all")
	@GetMapping("classify")
	public R getClassify() {
		return new R<>(saftyManagementService.getClassify());
	}

	/**
	 * 上报数据
	 */
	@GetMapping("source")
	public R getSource() {
		return new R<>(saftyManagementService.getSource());
	}

	/**
	 * 趋势数据获取
	 */
	@SuppressWarnings("all")
	@GetMapping("trend_chart")
	public R getTrendData() {
		return new R<>(saftyManagementService.getTrendData());
	}
}
