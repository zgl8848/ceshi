package com.campus.grid.mapper;

import com.campus.grid.api.dto.GridFunctionDTO;
import com.campus.grid.api.dto.PictureDTO;
import com.campus.grid.api.entity.FunctionType;
import com.campus.grid.api.entity.Reseau;
import com.campus.grid.api.entity.SafetyInspect;
import com.campus.grid.api.vo.RectificationTaskVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SecurityPatrolMapper {
	//查找所有网格化名称
	List<Reseau> selectReseauName(@Param("id") String id, @Param("roleCode") String roleCode, @Param("schoolId") String schoolId, @Param("type") String type);

	//根据网格名称查找网格功能
	GridFunctionDTO findGridFunction(String reseauId);

	//查找所有的安全问题类型
	List<FunctionType> selectFunctionName(@Param("type")String type);

	//根据对应的问题类型找到物品标签
	List<FunctionType> selectFunctionType(String functionName);

	//查找所有的事件标签
	List<String> selectEventLabel();

	//插入一条巡查信息
	void insertSafetyInspect(SafetyInspect safetyInspect);

	//今日无状况时添加信息
	void insertNoCondition(SafetyInspect safetyInspect);

	//查询隐患信息
	List<SafetyInspect> selectSafetyInspect(@Param("startSize") Integer startSize, @Param("endSize") Integer endSize, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("userId") String userId, @Param("isNotarize") String isNotarize, @Param("functionId") String functionId, @Param("level") Integer level, @Param("status") Integer status, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts, @Param("roleCode") String roleCode, @Param("schoolId") String schoolId, @Param("ebSchoolId") String ebSchoolId, @Param("task") String task, @Param("reseauName") String reseauName,@Param("type") Integer type);

	//查询总条数
	Integer total(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("userId") String userId, @Param("isNotarize") String isNotarize, @Param("functionId") String functionId, @Param("level") Integer level, @Param("status") Integer status, @Param("roleType") String roleType, @Param("childDepts") List<String> childDepts, @Param("roleCode") String roleCode, @Param("schoolId") String schoolId, @Param("ebSchoolId") String ebSchoolId, @Param("reseauName") String reseauName,@Param("type") Integer type);

	//修改状态
	void updateSafetyInspectStatus(@Param("inspectId") String inspectId, @Param("message") String message, @Param("nowTime") LocalDateTime nowTime);

	//添加照片
	void insertPic(@Param("id") String id, @Param("inspectId") String inspectId, @Param("picNames") String picNames, @Param("status") Integer status, @Param("time") LocalDateTime time);

	//根据id查询图片
	String selectPics(@Param("inspectId") String inspectId, @Param("status") Integer status);

	//根据用户id查询用户的权限标识
	List<String> findRoleCode(String userId);

	//查询记录的状 态
	Integer findInspectStatus(String inspectId);

	//根据学校id查询学校所属部门

	String findDeptName(String shcoolId);

	//消防未完成条数
	Integer findFireFighting(String userId);
	//巡查未完成条数
	Integer findInspect(String userId);
	//隐患未完成条数
	Integer findHiddenDanger(String userId);

	int getTotal(@Param("type") Integer type,@Param("userId") String userId ,@Param("rectification") String rectification,@Param("status")Integer status);
	//消防，隐患，巡查详情展示
	List<SafetyInspect> recordDetails(@Param("startIndex") Integer startIndex, @Param("size") Integer size, @Param("type") Integer type,@Param("userId") String userId ,@Param("rectification") String rectification,@Param("status")Integer status);

	//整改任务接口
	List<RectificationTaskVO> selectReseauOfUser(@Param("startIndex") Integer startIndex, @Param("size") Integer size, @Param("userId") String userId,@Param("type") Integer type);

	int reseauOfUserTotal(@Param("userId") String userId,@Param("type") Integer type);

	//查询该网格今日是否巡查
	Integer findRectificationCount(@Param("reseauId") String reseauId,@Param("newTime") LocalDateTime newTime,@Param("type") Integer type);
	//
	String findMode(String inspectId);

	//巡查图片
	List<PictureDTO> selectAllPics(String inspectId);
}
