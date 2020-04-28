package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.vo.PostVO;

import java.util.List;


/**
 * 安全岗位信息表
 *
 * @author CR7
 * @date 2019-01-09 10:04:49
 */
public interface GridSafetyPostService extends IService<GridSafetyPost> {
	Page<List<PostVO>> getPostUser(Page page, String schoolId);
}
