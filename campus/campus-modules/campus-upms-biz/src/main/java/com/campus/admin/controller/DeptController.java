package com.campus.admin.controller;

import com.campus.admin.api.entity.SysDept;
import com.campus.admin.service.SysDeptService;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", description = "部门管理模块")
public class DeptController {
	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sysDeptService.getById(id));
	}


	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R getTree() {
		return new R<>(sysDeptService.selectTree());
	}

	/**
	 * 返回当前用户树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/user-tree/{isSchoolTree}")
	public R getUserTree(@PathVariable Boolean isSchoolTree) {
		return new R<>(sysDeptService.getUserTree(isSchoolTree));
	}

	/**
	 * 添加
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public R save(@Valid @RequestBody SysDept sysDept) {
		return new R<>(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public R removeById(@PathVariable String id) {
		return sysDeptService.removeDeptById(id);
	}

	/**
	 * 编辑
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public R update(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return new R<>(sysDeptService.updateDeptById(sysDept));
	}

	/**
	 * 获取本级和子集部门
	 *
	 * @return List<String> depts
	 */
	@GetMapping("/listChildDepts")
	public R listDepts() {
		return new R<>(sysDeptService.getChildDepts());
	}

	/**
	 * 获取当前部门下的所有的督查小组
	 * @return
	 */
	@RequestMapping("/getAllInspectGroup")
	public R getAllInspectGroup(){
		return new R<>(sysDeptService.getAllInspectGroup());
	}

	/**
	 * 根据部门id获取部门名称
	 * @param deptId	部门id
	 * @return	部门名称
	 */
	@GetMapping("/getDeptNameById/{deptId}")
	public String getDeptNameById(@PathVariable("deptId") String deptId){
		return sysDeptService.getDeptNameById(deptId);
	}
}
