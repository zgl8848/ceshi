package com.campus.grid.mapper;

import com.campus.grid.api.entity.FireEquipment;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.*;

public interface FireEquipmentMapper {
	List<FireEquipment> selectFireEquipments(@Param("startSize") Integer startSize, @Param("endSize") Integer endSize, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,@Param("name") String name, @Param("schoolId")String schoolId,@Param("ebSchoolId")String ebSchoolId,@Param("roleType")String roleType, @Param("childDepts")List<String> childDepts);
	Integer total(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,@Param("name") String name, @Param("schoolId")String schoolId,@Param("ebSchoolId")String ebSchoolId,@Param("roleType")String roleType, @Param("childDepts")List<String> childDepts);
	boolean updateFireEquipment(FireEquipment fireEquipment);
	boolean addFireEquipment(FireEquipment fireEquipment);
	String findReseauId(@Param("reseauame")String reseauame,@Param("schoolId")String schoolId);
	boolean insertFireEquipments(List<FireEquipment> fireEquipments);
	boolean updateStatus(LocalDateTime nowTime);

}
