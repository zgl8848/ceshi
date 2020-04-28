package com.campus.admin.controller;

import com.campus.admin.service.SysAreaService;
import com.campus.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 省市区
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/area")
@Api(value = "area", description = "省市区三级联动")
public class AreaController {

	private final SysAreaService sysAreaService;

	/**
	 * 获取省份的数据
	 *
	 * @return
	 */
	@GetMapping("/getProvince")
	public R getProvince() {
		return new R<>(sysAreaService.listProvince());
	}

	/**
	 * 获取城市的数据
	 *
	 * @param key
	 * @return
	 */
	@GetMapping("/getCity/{key}")
	public R getCity(@PathVariable String key) {
		return new R<>(sysAreaService.listCityByProvinceCode(key));
	}

	/**
	 * 获取地区的数据
	 *
	 * @param key
	 * @return
	 */
	@GetMapping("/getArea/{key}")
	public R getArea(@PathVariable String key) {
		return new R<>(sysAreaService.listAreaByCityCode(key));
	}
}
