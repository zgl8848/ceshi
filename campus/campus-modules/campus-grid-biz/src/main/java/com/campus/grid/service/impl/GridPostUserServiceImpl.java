package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.GridPostUser;
import com.campus.grid.mapper.GridPostUserMapper;
import com.campus.grid.service.GridPostUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 岗位-用户关系表
 *
 * @author CR7
 * @date 2019-01-09 10:05:18
 */
@Service("gridPostUserService")
@AllArgsConstructor
public class GridPostUserServiceImpl extends ServiceImpl<GridPostUserMapper, GridPostUser> implements GridPostUserService {
    private final GridPostUserMapper gridPostUserMapper;
	@Override
	public boolean removeByUserIds(List<GridPostUser> gridPostUser) {
		if (gridPostUser.size() == 0) {
			return false;
		}
		if (this.baseMapper.delByUserIds(gridPostUser) == gridPostUser.size())
			return true;
		return false;
	}

	@Override
	public List<HashMap<String, String>> getUserBySchooId(String schooId) {
		return gridPostUserMapper.getUserBySchooId(schooId);
	}

}
