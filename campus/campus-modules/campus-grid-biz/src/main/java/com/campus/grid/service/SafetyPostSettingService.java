package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.entity.SafetyPostSetting;

import java.util.List;

public interface SafetyPostSettingService extends IService<SafetyPostSetting> {
	public Page<List<SafetyPostSetting>> getSafety(Page page, String type);

	public boolean addPost(GridSafetyPost gridSafetyPost);

	public boolean delPost(String postType, String schoolId, String status);

	public List<SafetyPostSetting> selectPostByShcoolId(String schoolId);
}
