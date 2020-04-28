package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.Sendee;
import com.campus.grid.service.SendeeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/sendee")
@Api(value = "sendee", description = "告警接收人模块")
public class SendeeController {

	private final SendeeService sendeeService;

	/**
	 * 分页查询告警接收人信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, Sendee sendee) {
		return new R<>(sendeeService.page(page, Wrappers.query(sendee)));
	}

	/**
	 * 新增告警接收人
	 *
	 * @param sendee
	 * @return
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('alarmManagement_sendee_add')")
	public R save(@Valid @RequestBody Sendee sendee) {
		return new R<>(sendeeService.save(sendee));
	}

	/**
	 * 删除告警接收人
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('alarmManagement_sendee_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(sendeeService.removeById(id));
	}

	/**
	 * 修改告警接收人
	 *
	 * @param sendee
	 * @return
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('alarmManagement_sendee_update')")
	public R updateById(@Valid @RequestBody Sendee sendee) {
		return new R<>(sendeeService.updateById(sendee));
	}

	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sendeeService.getById(id));
	}
}
