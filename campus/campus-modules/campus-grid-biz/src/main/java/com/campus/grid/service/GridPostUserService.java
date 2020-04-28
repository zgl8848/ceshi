package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.GridPostUser;

import java.util.HashMap;
import java.util.List;

/**
 * 岗位-用户关系表
 *
 * @author CR7
 * @date 2019-01-09 10:05:18
 */
public interface GridPostUserService extends IService<GridPostUser> {
	public boolean removeByUserIds(List<GridPostUser> gridPostUser);

	List<HashMap<String, String>> getUserBySchooId(String schooId);
}
