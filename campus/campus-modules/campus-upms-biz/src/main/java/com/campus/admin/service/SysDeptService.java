package com.campus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.dto.DeptTree;
import com.campus.admin.api.entity.SysDept;
import com.campus.common.core.util.R;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
public interface SysDeptService extends IService<SysDept> {

	/**
	 * 查询部门树菜单
	 *
	 * @return 树
	 */
	List<DeptTree> selectTree();

	/**
	 * 查询用户部门树
	 *
	 * @return
	 */
	List<DeptTree> getUserTree(Boolean isSchoolTree);

	/**
	 * 添加信息部门
	 *
	 * @param sysDept
	 * @return
	 */
	Boolean saveDept(SysDept sysDept);

	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	R<Boolean> removeDeptById(String id);

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	Boolean updateDeptById(SysDept sysDept);

	/**
	 * 获取当前用户的子部门信息
	 *
	 * @return 子部门列表
	 */
	List<String> getChildDepts();

	/**
	 * 获取当前部门所有的督查小组
	 * @return 所有的督查小组
	 */
	Map<String,Object> getAllInspectGroup();

	/**
	 * 根据部门id获取部门名称
	 * @param deptId	部门id
	 * @return	部门名称
	 */
    String getDeptNameById(String deptId);
}
