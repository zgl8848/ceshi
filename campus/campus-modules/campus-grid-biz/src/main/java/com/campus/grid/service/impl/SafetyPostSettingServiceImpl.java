package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.entity.SafetyPostSetting;
import com.campus.grid.mapper.SafetyPostSettingMapper;
import com.campus.grid.service.SafetyPostSettingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("safetyPostSettingService")
public class SafetyPostSettingServiceImpl extends ServiceImpl<SafetyPostSettingMapper, SafetyPostSetting> implements SafetyPostSettingService {

	@Override
	@SuppressWarnings("all")
	public Page<List<SafetyPostSetting>> getSafety(Page page, String type) {
		type = "post_type";
		return page.setRecords(this.baseMapper.getSafety(page, type));
	}

	@Override
	public boolean addPost(GridSafetyPost gridSafetyPost) {
		int i = this.baseMapper.addPost(gridSafetyPost);
		if (i == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean delPost(String postType, String schoolId, String status) {
		int i = this.baseMapper.delPost(postType, schoolId, status);
		if (i == 0)
			return false;
		else
			return true;
	}

	@Override
	public List<SafetyPostSetting> selectPostByShcoolId(String schoolId) {
		return this.baseMapper.selectedPostBySchoolId(schoolId);
	}
}
