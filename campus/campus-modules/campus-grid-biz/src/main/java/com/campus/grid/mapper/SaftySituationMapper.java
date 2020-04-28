package com.campus.grid.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaftySituationMapper {
	//查询隐患活巡查总数
	Integer getAllCount(@Param("schoolId") String schoolId, @Param("type") Integer type, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts);

}
