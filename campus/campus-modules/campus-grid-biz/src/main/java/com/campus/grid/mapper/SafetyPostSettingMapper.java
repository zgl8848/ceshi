package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.entity.SafetyPostSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SafetyPostSettingMapper extends BaseMapper<SafetyPostSetting> {
	/**
	 * 查询基本组合信息
	 */
	List<SafetyPostSetting> getSafety(Page page, @Param("type") String type);

	/**
	 * grid_safety_post表中添加一条记录
	 */
	int addPost(GridSafetyPost gridSafetyPost);

	/**
	 * 更新grid_safety_post表某条记录的status字段
	 */
	int delPost(@Param("posttype") String posttype, @Param("schoolId") String schoolId, @Param("status") String status);

	/**
	 * 根据学校id查询该学校选中的岗位
	 */
	List<SafetyPostSetting> selectedPostBySchoolId(@Param("schoolId") String schoolId);
}
