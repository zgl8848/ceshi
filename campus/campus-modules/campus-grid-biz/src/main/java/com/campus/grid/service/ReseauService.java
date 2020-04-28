package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.entity.SysUser;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.Reseau;
import com.campus.grid.api.entity.ReseauVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
public interface ReseauService extends IService<Reseau> {

	void updateUserIsNull(String userId);

	void updatePrimaryPrincipal(String reseauId, String userId,Integer responsiblyType);

	List<SysUser> selectPrimaryPrincipal(String schoolId);

	List<String> findBuildingList(String planename);

	List<String> findSpaceNameList(String spacename);

	List<Reseau> findReseauName(String spacename);

	boolean delReseau(String reseauid);

	String creatReseau(Reseau reseauVaule);

	boolean delPlaneName(String planename);

	boolean delBuilddingName(String builddingname);

	boolean isHavePlaneName(String planeName);

	boolean isHaveBuilddingName(String builddingName);

	PageBean selectReseauPageBean(int currentPage, int pageSize, String planeName, int allot);

	Map<String, String> selectParentGridFunction();

	Map<String, String> selectChildrenGridFunction(String parentId);

	String isHaveReseauName(Reseau reseauVaule);

	String getSchoolId();

	void editReseau(ReseauVo reseauVo);

//	void updatePlaneName(String planeName);

	List<Reseau> selectReseauByUid(String userId);

	void updateUserNameByUserId(String userId, String userName);

	String getReseauId(Reseau reseauVaule);

	List<Reseau> selectReseauExportInfoBySchoolId(String schoolId);

	void export(HttpServletRequest request, HttpServletResponse response);

	List<Reseau> selectReseauByUidAndType(String id, String responsiblyType);

	Reseau getReseau(String schoolId, String reseauId, String userId);
}
