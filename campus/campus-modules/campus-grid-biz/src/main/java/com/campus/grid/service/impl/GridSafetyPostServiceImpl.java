package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.GridSafetyPost;
import com.campus.grid.api.vo.PostVO;
import com.campus.grid.mapper.GridSafetyPostMapper;
import com.campus.grid.service.GridSafetyPostService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 安全岗位信息
 *
 * @author CR7
 * @date 2019-01-09 10:04:49
 */
@Service("gridSafetyPostService")
public class GridSafetyPostServiceImpl extends ServiceImpl<GridSafetyPostMapper, GridSafetyPost> implements GridSafetyPostService {

	@Override
	public Page<List<PostVO>> getPostUser(Page page, String schoolId) {
		List<PostVO> listVO = this.baseMapper.getPostUser(page, schoolId);
		for (int i = 0; i < listVO.size(); i++) {
			listVO.get(i).setUserList(this.baseMapper.getUserByPostId(listVO.get(i).getPostId()));
		}
		return page.setRecords(listVO);
	}

}
