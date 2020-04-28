package com.campus.grid.mapper;

import com.campus.admin.api.dto.UserDTO;
import com.campus.grid.api.dto.GridFunctionDTO;
import com.campus.grid.api.dto.HiddenDangerDTO;
import com.campus.grid.api.dto.ProcessModeDTO;
import com.campus.grid.api.entity.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HiddenDangerMapper {
	//根据网格id查找对应负责人
	String selectTrueName(String reseauId);

	//找到所有的处理方式
	List<ProcessModeDTO> selectProcessMode();

	//根据网格名称查找网格功能
	GridFunctionDTO findGridFunction(String reseauId);

	//插入隐患信息
	void insertHiddenDanger(HiddenDanger danger);

	//查询隐患信息
	List<HiddenDanger> selectHiddenDanger(@Param("startSize") Integer startSize, @Param("endSize") Integer endSize, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("level") Integer level, @Param("functionId") String functionId, @Param("mode") Integer mode, @Param("title") String title,@Param("userId") String userId,@Param("roleType")String roleType,@Param("status")Integer status,@Param("childDepts")List<String> childDepts, @Param("roleCode")String roleCode, @Param("schoolId")String schoolId,@Param("ebSchoolId")String ebSchoolId,@Param("task")String task,@Param("reseauName")String reseauName,@Param("type")String type);

	//获得隐患总条数
	Integer findTotal(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("level") Integer level, @Param("functionId") String functionId, @Param("mode") Integer mode, @Param("title") String title,@Param("userId") String userId,@Param("roleType")String roleType,@Param("status")Integer status,@Param("childDepts")List<String> childDepts, @Param("roleCode")String roleCode, @Param("schoolId")String schoolId,@Param("ebSchoolId")String ebSchoolId,@Param("reseauName")String reseauName,@Param("type")String type);

	//根据用户查询所属学校的所有用户
	List<UserDTO> selectUserName(String schoolId);

	/**
	 * 统计隐患分布情况（学校类型）
	 * @param startTime
	 * @param endTime
	 * @param childDepts 部门集合
	 * @param roleType   角色类型
	 * @param schoolIds   学校ID
	 * @param taskId     检查任务ID
	 * @return
	 */
	List<HiddenDangerDTO> statBySchoolType(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("roleType")String roleType, @Param("schoolIds")List<String> schoolIds, @Param("childDepts")List<String> childDepts,  @Param("taskId")String taskId);

	/**
	 * 统计隐患分布情况（严重级别）
	 * @param startTime
	 * @param endTime
	 * @param childDepts 部门集合
	 * @param roleType   角色类型
	 * @param schoolIds   学校ID
	 * @param taskId     检查任务ID
	 * @return
	 */
	List<HiddenDangerDTO> statByLevel(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("roleType")String roleType, @Param("schoolIds")List<String> schoolIds, @Param("childDepts")List<String> childDepts,  @Param("taskId")String taskId);

	/**
	 * 统计隐患TOP（学校）
	 * @param startTime
	 * @param endTime
	 * @param childDepts 部门集合
	 * @param roleType   角色类型
	 * @param schoolIds  学校ID
	 * @param taskId     检查任务ID
	 * @return
	 */
	List<HiddenDangerDTO> statBySchool(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("roleType")String roleType, @Param("schoolIds")List<String> schoolIds, @Param("childDepts")List<String> childDepts, @Param("taskId")String taskId);

	/**
	 * 统计隐患整改情况（整改完成）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer statRectification(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("roleType")String roleType, @Param("schoolIds")List<String> schoolIds, @Param("childDepts")List<String> childDepts, @Param("taskId")String taskId);


	//查询隐患信息
    List<HiddenDanger> selectHiddenDangerData(@Param("startSize") Integer startSize, @Param("endSize") Integer endSize, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("level") Integer level, @Param("functionId") String functionId, @Param("mode") Integer mode, @Param("title") String title,@Param("userId") String userId,@Param("roleType")String roleType,@Param("status")Integer status,@Param("childDepts")List<String> childDepts, @Param("roleCode")String roleCode, @Param("schoolId")String schoolId,@Param("ebSchoolId")String ebSchoolId,@Param("task")String task,@Param("reseauName")String reseauName);

    //根据网格id查找对应负责人
	String selectTrueNameByReseauId(String reseauId);
}
