package com.campus.grid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.feign.RemoteDictService;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.service.SafetyPostSettingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/safetypostsetting")
public class SafetyPostSettingController {
	private final SafetyPostSettingService safetyPostSettingService;
	private RemoteDictService remoteDictService;

	/**
	 * 分页查询字典中的所有岗位类型
	 *
	 * @param page
	 * @return
	 */
	@SysLog("分页查询所有岗位信息")
	@GetMapping("/page")
	public R getPostByDict(Page page) {
		String type = "post_type";
		return new R<>(safetyPostSettingService.getSafety(page, type));
	}

	@SysLog("查询该学校所选岗位信息")
	@GetMapping("/getSelected")
	public R getSelectedPost() {
		String schoolId = getSchoolId();
		return new R<>(safetyPostSettingService.selectPostByShcoolId(schoolId));
	}

	@SysLog("删除岗位信息")
	@DeleteMapping("/{postType}")
	//@PreAuthorize("@pms.hasPermission('grid_postuser_modify')")
	public R removeByType(@PathVariable String postType) {
		String schoolId = getSchoolId();
		String status = "1";//1--删除标志,0--未删除
		return new R<>(safetyPostSettingService.delPost(postType, schoolId, status));
	}

	@SysLog("新增岗位信息")
	@PostMapping
	//@PreAuthorize("@pms.hasPermission('grid_postuser_modify')")
	public R save(@RequestBody GridSafetyPost gridSafetyPost) {
		//根据post_type查询remark信息
		DictInfo dictInfo = remoteDictService.info(gridSafetyPost.getPostType());
		gridSafetyPost.setRemark(dictInfo.getRemarks());
		String schoolId = getSchoolId();
		gridSafetyPost.setSchoolId(schoolId);
		gridSafetyPost.setStatus("0");
		return new R<>(safetyPostSettingService.addPost(gridSafetyPost));
	}

	//获取当前用户的学校id
	public String getSchoolId() {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		if (schoolId != null)
			return schoolId;
		else
			return null;
	}
}
