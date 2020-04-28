package com.campus.grid.mapper;

import com.campus.grid.api.entity.saftymanagement.ManageMap;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaftyManagementMapper {
	//根据类型查找对应的未处理的巡查或者隐患条数
	Integer getCount(@Param("schoolId") String schoolId, @Param("functionId") String functionId, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts);

	//学校的严重隐患
	ManageMap findSchoolHiddenDanger(@Param("schoolId") String schoolId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	//查询教育局用户下的所有县级编码
	List<String> selectCounty(@Param("childDepts") List<String> childDepts);

	//根据县级编码查询隐患总数
	Integer findAllHiddenDangerOfAreaCode(@Param("county") String county, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	//根据县级编码查询严重隐患总数
	Integer findHiddenDangerOfAreaCode(@Param("county") String county, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	//查询所有的县级编码
	List<String> selecAlltCounty();

	//查询根据月份查询数量隐患数据
	Integer getHiddenDangerCountOfMonth(@Param("schoolId") String schoolId, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	//根据学校id查询学校地区编码
	String findSchoolAreCode(String schoolId);

	//根据country查询城市编码
	String findCityCodeOfAreaCode(String AreaCode);

	//根据country查询省级编码
	String findProvinceCodeOfAreaCode(String AreaCode);
}
