package com.campus.warning.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.warning.api.entity.GridSchool;

@Mapper
public interface GridSchoolMapper extends BaseMapper<GridSchool>{
	GridSchool findGridSchool(String schoolCode);
}
