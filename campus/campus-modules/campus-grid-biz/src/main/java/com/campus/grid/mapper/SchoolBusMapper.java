package com.campus.grid.mapper;

import com.campus.grid.api.entity.SchoolBusEd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolBusMapper {

	void saveSchoolBusEd(SchoolBusEd schoolBusEd);

	List<SchoolBusEd> queryPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize,
								@Param("edtype") Integer edtype);

	//int getTotal(@Param("schoolId")String schoolId);
	int getTotal();
}
