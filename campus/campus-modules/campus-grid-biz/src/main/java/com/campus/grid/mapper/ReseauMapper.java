package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysUser;
import com.campus.grid.api.entity.FunctionType;
import com.campus.grid.api.entity.Reseau;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.api.entity.ReseauVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
public interface ReseauMapper extends BaseMapper<Reseau> {

	void updateUserIsNull(@Param("userId") String userId);

	String findReaseauByReaseauId(@Param("reseauId") String reseauId);

	void updatePrimaryPrincipal(@Param("reseauId") String reseauId,@Param("allot")Integer allot);

	List<SysUser> selectPrimaryPrincipal(@Param("schoolId") String schoolId);

	List<String> findBuildingList(@Param("planename") String planename, @Param("schoolId") String schoolId);

	List<String> findSpaceNameList(@Param("spacename") String spacename, @Param("type") String type, @Param("schoolId") String schoolId);

	List<Reseau> findReseauName(@Param("planename") String planename, @Param("buildingname") String buildingname, @Param("type") String type, @Param("schoolId") String schoolId);

	boolean delReseau(String reseauid);

	boolean creatReseau(Reseau reseauVaule);

	int getTotalRecord(@Param("planeName") String planeName, @Param("allot") int allot, @Param("schoolId") String schoolId);

	List<Reseau> selectReseauPageBean(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("planeName") String planeName, @Param("allot") int allot, @Param("schoolId") String schoolId);

	boolean delPlaneName(@Param("plane_name") String plane_name, @Param("type") String type, @Param("schoolId") String schoolId);

	boolean delBuilddingName(@Param("building_name") String building_name, @Param("plane_name") String plane_name, @Param("type") String type, @Param("schoolId") String schoolId);

	String isHavePlaneName(@Param("planename") String planeName, @Param("type") String type, @Param("schoolId") String schoolId);

	String addReseauFlag(Reseau reseauVaule);

	String isHaveBuilddingName(@Param("type") String type, @Param("planeName") String planeName, @Param("buildingName") String buildingName, @Param("schoolId") String schoolId);

	String getFunctionNameByFid(@Param("functionId") String functionId);

	String getParentFunctionNameByFid(@Param("functionId") String functionId);

	List<FunctionType> selectParentGridFunction();

	List<FunctionType> selectChildrenGridFunction(@Param("parentId") String parentId);

	void editReseau(@Param("reseauVo") ReseauVo reseauVo);

	String findUserName(@Param("userId") String userId);

	void updateUserNameByReseauId(@Param("reseauId") String reseauId, @Param("userName") String userName);

//	void updatePlaneName(@Param("planeName")String planeName);

	List<Reseau> selectReseauByUid(@Param("userId") String userId);

	void updateUserNameByUserId(@Param("userId") String userId,@Param("userName") String userName);

	List<Reseau> selectReseauExportInfoBySchoolId(@Param("schoolId") String  schoolId);

	List<Reseau> selectReseauByUidAndType(@Param("userId") String userId,@Param("responsiblyType") String responsiblyType);

	Reseau getReseau(@Param("schoolId") String schoolId, @Param("reseauId") String reseauId, @Param("userId") String userId);

}
