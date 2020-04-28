package com.campus.grid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.service.GridSafetyPostService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 安全岗位信息
 *
 * @author CR7
 * @date 2019-01-09 10:04:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gridsafetypost")
public class GridSafetyPostController {

	private final GridSafetyPostService gridSafetyPostService;

	/**
	 * 分页查询总体信息
	 *
	 * @param page           分页对象
	 * @param gridSafetyPost 安全岗位信息
	 * @return
	 */
	@GetMapping("/page")
	public R getGridSafetyPostPage(Page page, GridSafetyPost gridSafetyPost) {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		return new R<>(gridSafetyPostService.getPostUser(page, schoolId));
	}


	/**
	 * 通过id查询安全岗位信息
	 *
	 * @param postId id
	 * @return R
	 */
	@GetMapping("/{postId}")
	public R getById(@PathVariable("postId") String postId) {
		return new R<>(gridSafetyPostService.getById(postId));
	}

	/**
	 * 新增安全岗位信息
	 *
	 * @param gridSafetyPost 安全岗位信息
	 * @return R
	 */
	@SysLog("新增安全岗位信息")
	@PostMapping
	public R save(@RequestBody GridSafetyPost gridSafetyPost) {
		return new R<>(gridSafetyPostService.save(gridSafetyPost));
	}

	/**
	 * 修改安全岗位信息
	 *
	 * @param gridSafetyPost 安全岗位信息
	 * @return R
	 */
	@SysLog("修改安全岗位信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('grid_post_modify')")
	public R updateById(@RequestBody GridSafetyPost gridSafetyPost) {
		return new R<>(gridSafetyPostService.updateById(gridSafetyPost));
	}

	/**
	 * 通过id删除安全岗位信息
	 *
	 * @param postId id
	 * @return R
	 */
	@SysLog("删除安全岗位信息")
	@DeleteMapping("/{postId}")
	public R removeById(@PathVariable String postId) {
		return new R<>(gridSafetyPostService.removeById(postId));
	}

}
