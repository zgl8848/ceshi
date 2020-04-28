package com.campus.grid.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupervisionMapper {
	//根据部门获取子部门的信息
	List<String> getChildDepts(String deptId);

	//根据类型查找对应的未处理的巡查或者隐患条数
	Integer getCount(@Param("schoolId") String schoolId, @Param("type") Integer type, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts);
}
