package com.campus.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;
import com.campus.admin.service.SysDictService;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author campus
 * @since 2017-11-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", description = "字典管理模块")
public class DictController {
	private final SysDictService sysDictService;

	/**
	 * 通过ID查询字典信息
	 *
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sysDictService.getById(id));
	}

	/**
	 * 分页查询字典信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, SysDict sysDict) {
		return new R<>(sysDictService.page(page, Wrappers.query(sysDict)));
	}

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = "dict_details", key = "#type")
	public R getDictByType(@PathVariable String type) {
		return new R<>(sysDictService.list(Wrappers
				.<SysDict>query().lambda()
				.eq(SysDict::getType, type)));
	}

	/**
	 * 添加字典
	 *
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@CacheEvict(value = "dict_details", key = "#sysDict.type")
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public R save(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.save(sysDict));
	}

	/**
	 * 删除字典，并且清除字典缓存
	 *
	 * @param id   ID
	 * @param type 类型
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}/{type}")
	@CacheEvict(value = "dict_details", key = "#type")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public R removeById(@PathVariable String id, @PathVariable String type) {
		return new R<>(sysDictService.removeById(id));
	}

	/**
	 * 修改字典
	 *
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@CacheEvict(value = "dict_details", key = "#sysDict.type")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R updateById(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.updateById(sysDict));
	}

	/**
	 * 查询type为post_type value为岗位编号的岗位信息
	 */
	@GetMapping("/value/{value}")
	public DictInfo getDictByTypeAndValue(@PathVariable String value) {
		return sysDictService.getInfo(value);
	}

	/**
	 * 根据type,  value查询字典信息
	 */
	@GetMapping("/info/{type}/{value}")
	public R getDict(@PathVariable String type, @PathVariable String value) {
		return new R<>(sysDictService.getDict(type, value));
	}
}
