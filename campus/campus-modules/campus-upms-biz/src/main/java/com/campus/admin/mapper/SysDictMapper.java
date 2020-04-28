package com.campus.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;


/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2017-11-19
 */
public interface SysDictMapper extends BaseMapper<SysDict> {
	DictInfo getInfo(String value);

	DictInfo getDict(@Param("type") String type, @Param("value") String value);
}
