package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.GridPostUser;
import com.campus.grid.service.GridPostUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位-用户关系
 *
 * @author CR7
 * @date 2019-01-09 10:05:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gridpostuser")
public class GridPostUserController {

	private final GridPostUserService gridPostUserService;

	/**
	 * 新增岗位-用户关系
	 *
	 * @param gridPostUser 岗位-用户关系
	 * @return R
	 */
	@SysLog("新增岗位-用户关系")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('grid_postuser_modify')")
	public R save(@RequestBody List<GridPostUser> gridPostUser) {
		return new R<>(gridPostUserService.saveBatch(gridPostUser));
	}

	/**
	 * 删除岗位-用户关系
	 *
	 * @param gridPostUser
	 * @return R
	 */
	@SysLog("删除岗位-用户关系")
	@DeleteMapping("/postId")
	@PreAuthorize("@pms.hasPermission('grid_postuser_modify')")
	public R removeById(@RequestBody List<GridPostUser> gridPostUser) {
		return new R<>(gridPostUserService.removeByUserIds(gridPostUser));
	}


}
