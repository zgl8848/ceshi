package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.common.security.annotation.Inner;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 学校
 *
 * @author campus
 * @date 2018-12-14 10:27:48
 */
@AllArgsConstructor
@RequestMapping("/school")
@RestController
public class SchoolController {

	private final SchoolService schoolService;

	/**
	 * 分页查询
	 *
	 * @param page     分页对象
	 * @param schoolVO 学校
	 * @return
	 */
	@GetMapping("/page")
	public R getSchoolPage(Page page, SchoolVO schoolVO) {
		return new R<>(schoolService.listSchoolPage(page, schoolVO));
	}


	/**
	 * 通过id查询学校
	 *
	 * @param schoolId id
	 * @return R
	 */
	@GetMapping("/{schoolId}")
	public R getById(@PathVariable("schoolId") String schoolId) {
		return new R<>(schoolService.getById(schoolId));
	}

	/**
	 * 新增学校
	 *
	 * @param school 学校
	 * @return R
	 */
	@SysLog("新增学校")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('campus_school_add')")
	public R save(@Valid @RequestBody School school) {
		return new R<>(schoolService.save(school));
	}

	/**
	 * 教育局用户修改学校
	 *
	 * @param school 学校
	 * @return R
	 */
	@SysLog("修改学校")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('campus_school_edit')")
	public R updateById(@Valid @RequestBody School school) {
		return new R<>(schoolService.updateById(school));
	}

	/**
	 * 学校用户修改学校
	 *
	 * @param school 学校
	 * @return R
	 */
	@SysLog("修改学校")
	@PutMapping("/update")
	@PreAuthorize("@pms.hasPermission('campus_school_info_update')")
	public R updateByIdx(@Valid @RequestBody School school) {
		return new R<>(schoolService.updateById(school));
	}

	/**
	 * 修改学校状态 锁定 正常
	 *
	 * @param school 学校
	 * @return R
	 */
	@SysLog("修改学校")
	@PutMapping("/status")
	@PreAuthorize("@pms.hasPermission('campus_school_edit')")
	public R updateSchoolStatusById(@RequestBody School school) {
		return new R<>(schoolService.updateSchoolStatus(school));
	}

	/**
	 * 通过id删除学校
	 *
	 * @param schoolId id
	 * @return R
	 */
	@SysLog("删除学校")
	@DeleteMapping("/{schoolId}")
	@PreAuthorize("@pms.hasPermission('campus_school_del')")
	public R removeById(@PathVariable String schoolId) {
		return new R<>(schoolService.removeById(schoolId));
	}

	/**
	 * 通过部门ID查询学校（条件：角色类型）
	 *
	 * @return
	 */
	@GetMapping("/dept/{deptId}")
	public R listSchoolsByDeptId(@PathVariable String deptId) {
		return new R<>(schoolService.listSchoolsByDeptId(deptId));
	}

	/**
	 * 查询当前登录用户的学校信息
	 *
	 * @return
	 */
	@GetMapping
	public R school() {
		return new R<>(schoolService.findSchoolInfo());
	}

	@GetMapping("/getSchoolCodeByPlatformIp/{alarmPlatformIp}")
	@Inner
	public R<School> getSchoolCodeByPlatformIp(@PathVariable String alarmPlatformIp) {
		return new R<School>(schoolService.getOne(Wrappers.query(new School()).eq(School::getAlarmPlatformIp, alarmPlatformIp)));
	}
}
