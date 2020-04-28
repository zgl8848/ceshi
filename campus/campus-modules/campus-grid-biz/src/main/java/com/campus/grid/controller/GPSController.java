package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.GPS;
import com.campus.grid.service.GPSServer;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/gps")
@Api(value = "gps", description = "gps模块")
public class GPSController {

	private GPSServer gpsServer;

	/**
	 * 分页查询GPS信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, GPS gps) {
		return new R<>(gpsServer.page(page, Wrappers.query(gps)));
	}

	/**
	 * 新增gps信息
	 *
	 * @param gps
	 * @return
	 */
	@PostMapping
	public R save(@RequestBody GPS gps) {
		return new R<>(gpsServer.save(gps));
	}

	/**
	 * 删除gps信息
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		return new R<>(gpsServer.removeById(id));
	}

	/**
	 * 修改gps信息
	 *
	 * @param gps
	 * @return
	 */
	@PutMapping
	public R updateById(@RequestBody GPS gps) {
		return new R<>(gpsServer.updateById(gps));
	}

	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(gpsServer.getById(id));
	}
}