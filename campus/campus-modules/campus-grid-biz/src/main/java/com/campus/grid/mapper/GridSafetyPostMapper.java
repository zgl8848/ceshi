package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.entity.SysUser;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.vo.PostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 安全岗位信息表
 *
 * @author CR7
 * @date 2019-01-09 10:04:49
 */
public interface GridSafetyPostMapper extends BaseMapper<GridSafetyPost> {
	/**
	 * 查询基本组合信息
	 */
	List<PostVO> getPostUser(Page page, @Param("schoolId") String schoolId);

	/**
	 * 根据postId查询该职位上的所有人
	 */
	List<SysUser> getUserByPostId(String postId);
}
