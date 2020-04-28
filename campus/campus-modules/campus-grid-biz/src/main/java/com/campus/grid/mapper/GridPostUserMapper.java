package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.GridPostUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * 岗位用户关系
 *
 * @author CR7
 * @date 2019-01-09 10:05:18
 */
public interface GridPostUserMapper extends BaseMapper<GridPostUser> {
	public int delByUserIds(@Param("gridPostUser") List<GridPostUser> gridPostUser);

	List<HashMap<String, String>> getUserBySchooId(@Param("schooId") String schooId);
}
