package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.Reseau;
import com.campus.grid.api.entity.ReseauVo;
import com.campus.grid.service.ReseauService;
import com.campus.grid.service.ReseauUserService;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * <p>
 * 网格划分
 * </p>
 *
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/reseau")
public class ReseauController {

	private final ReseauService reseauService;
	private final ReseauUserService reseauUserService;

	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {

		reseauService.export(request,response);

	}

	/**
	 * 修改用户名称时，也修改网格的责任人名称
	 */
	@RequestMapping("/updateUserNameByUserId/{userId}/{userName}")
	public void updateUserNameByUserId(@PathVariable String userId, @PathVariable String userName) {
		reseauService.updateUserNameByUserId(userId, userName);
	}

	/**
	 * 若删除用户，则将该用户分配的网格责任人设置空
	 */
	@RequestMapping("/updateUser/{userId}")
	public void updateUserIsNull(@PathVariable String userId) {
		reseauService.updateUserIsNull(userId);
	}

	/**
	 * 我的网格接口
	 */
	@GetMapping("/myReseau")
	public R getMyReseau(String responsiblyType) {
		String id = SecurityUtils.getUser().getId();
		return	new R(reseauService.selectReseauByUidAndType(id,responsiblyType));
	}

	/**
	 * 修改负责人
	 *
	 * @return
	 */
	@RequestMapping("/updatePrimaryPrincipal")
	public String updatePrimaryPrincipal(String reseauId, String userId,Integer responsiblyType) {
		reseauService.updatePrimaryPrincipal(reseauId, userId,responsiblyType);
		return "1";
	}

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	@RequestMapping("/selectPrimaryPrincipal")
	public R selectPrimaryPrincipal() {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		return new R<>(reseauService.selectPrimaryPrincipal(schoolId));
	}

	/**
	 * 修改网格
	 */
	@RequestMapping("/editReseau")
	public void editReseau(@RequestBody ReseauVo reseauVo) {
		reseauService.editReseau(reseauVo);
		reseauUserService.editReseauUser(reseauVo);
	}

	/**
	 * 分页查询
	 */
	@RequestMapping("/pageBean")
	public PageBean selectReseauPageBean(int currentPage, int pageSize, String planeName, int allot) {
		PageBean pageBean = reseauService.selectReseauPageBean(currentPage, pageSize, planeName, allot);
		return pageBean;

	}

	/**
	 * 根据用户id查询分配的网格
	 */
	@RequestMapping("/selectReseauByUid")
	public R selectReseauByUid(String userId) {
		return new R<>(reseauService.selectReseauByUid(userId));
	}

	/**
	 * 修改平面名称删除标识
	 * */
/*	@RequestMapping("/updatePlaneName")
	public void updatePlaneName(String planeName) {

		 reseauService.updatePlaneName(planeName);
	}*/


	/**
	 * 查询父级网格功能信息
	 */
	@RequestMapping("/selectParentGridFunction")
	public Map<String, String> selectParentGridFunction() {

		return reseauService.selectParentGridFunction();

	}

	/**
	 * 查询子级网格功能信息
	 */
	@RequestMapping("/selectChildrenGridFunction")
	public Map<String, String> selectChildrenGridFunction(String parentId) {
		return reseauService.selectChildrenGridFunction(parentId);

	}

	/**
	 * 通过区域类型getAll平面结构信息
	 */
	@GetMapping("/findPlaneNameList/{areaName}")
	public R findPlaneNameList(@PathVariable("areaName") String areaName) {
		return new R<>(reseauService.findBuildingList(areaName));
	}

	/**
	 * 通过平面结构名称getAll建筑结构信息
	 */
	@GetMapping("/findBuilddingNameList/{planeName}")
	public R findBuilddingNameList(@PathVariable("planeName") String planeName) {
		return new R<>(reseauService.findSpaceNameList(planeName));
	}

	/**
	 * 通过建筑结构名称getAll空间结构信息
	 */
	@GetMapping("/findReseauList/{spaceName}")
	public R findSpaceName(@PathVariable("spaceName") String spaceName) {
		return new R<>(reseauService.findReseauName(spaceName));
	}

	/**
	 * 通过网格reseauid删除已有网格
	 */
	@DeleteMapping("/delReseau/{reseauId}")
	@PreAuthorize("@pms.hasPermission('grid_reseau_del')")
	public R delReseau(@PathVariable("reseauId") String reseauId) {
		return new R<>(reseauService.delReseau(reseauId));
	}

	/**
	 * 通过plane_name,type删除已有平面建筑信息
	 */
	@DeleteMapping("/delPlaneName/{planeName}")
	@PreAuthorize("@pms.hasPermission('grid_reseau_del')")
	public R delPlaneName(@PathVariable("planeName") String planeName) {
		return new R<>(reseauService.delPlaneName(planeName));
	}

	/**
	 * 通过plane_name,builddingname,type删除已有网格
	 */
	@DeleteMapping("/delBuilddingName/{builddingName}")
	@PreAuthorize("@pms.hasPermission('grid_reseau_del')")
	public R delBuilddingName(@PathVariable("builddingName") String builddingName) {
		return new R<>(reseauService.delBuilddingName(builddingName));
	}

	/**
	 * 保存构建完成的网格
	 */
	@PostMapping("/creatReseau")
	@PreAuthorize("@pms.hasPermission('grid_reseau_add')")
	public R creatReseau(@RequestBody Reseau reseauVaule) {
		reseauService.creatReseau(reseauVaule);
		return new R<>(reseauService.getReseauId(reseauVaule));
	}

	/**
	 * 保证新增的平面结构信息唯一
	 */
	@GetMapping("/isHavePlaneName/{planeName}")
	public R isHavePlaneName(@PathVariable("planeName") String planeName) {
		return new R<>(reseauService.isHavePlaneName(planeName));
	}

	/**
	 * 保证新增的建筑结构信息唯一
	 */
	@GetMapping("/isHaveBuilddingName/{builddingName}")
	public R isHaveBuilddingName(@PathVariable("builddingName") String builddingName) {
		return new R<>(reseauService.isHaveBuilddingName(builddingName));
	}

	/**
	 * 根据reseauName查询数据是否已存在
	 */
	@PostMapping("/isHaveReseauName")
	public R isHaveReseauName(@RequestBody Reseau reseauVaule) {
		return new R<>(reseauService.isHaveReseauName(reseauVaule));
	}

	/**
	 * 修改网格职责
	 */
	@RequestMapping("/editDuty")
	public R updateReseauDuty(ReseauVo reseauVo) {
		try {
			reseauUserService.updateRemark(reseauVo);
			return new R<>("0");
		}catch (Exception e){
			return new R<>("1");
		}
	}

	/**
	 * 通过ID查询网格信息
	 *
	 * @param id ID
	 * @return 网格信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(reseauService.getById(id));
	}

	/**
	 * 获取网格信息
	 * @param schoolId
	 * @param reseauId
	 * @param userId
	 * @return
	 */
	@GetMapping("/info")
	public R getReseau(String schoolId, String reseauId, String userId){
		return new R<>(reseauService.getReseau(schoolId, reseauId, userId));
	}

}
