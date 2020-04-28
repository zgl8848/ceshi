package com.campus.grid.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.service.InspectReportService;
import com.campus.grid.service.InspectTaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/inspectTask")
@AllArgsConstructor
public class InspectTaskController {

	private final InspectTaskService inspectTaskService;
	
	private final InspectReportService inspectReportService;

	/**
	 * 任务添加
	 * @param taskName
	 * @param projects
	 * @param remark
	 * @param groups
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/save")
	public R save(String taskId,String taskName, String projects, String remark, String groups, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd")Date endTime){
		return new R<>(inspectTaskService.save(taskId,taskName, projects, remark, groups, startTime, endTime));
	}

	/**
	 * 详情
	 * @param taskId
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/taskParticulars")
	public R taskParticulars(Page page, @RequestParam() String taskId, @RequestParam(required = false) String deptId){
		return new R<>(inspectTaskService.taskParticulars(page, taskId, deptId));
	}

	/**
	 * 分页查询检查任务信息
	 *
	 * @param page    参数集
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getInspectTaskPage(Page page,@RequestParam(required = false) String taskName,@RequestParam(required = false) String reportTime) {
		return new R<>(inspectTaskService.getInspectTaskPage(page,taskName,reportTime));
	}

	/**
	 * 通过ID查询任务信息
	 *
	 * @param id 任务ID
	 * @return 任务信息
	 */
	@GetMapping("/{id}")
	public R taskById(@PathVariable String id) {
		return new R<>(inspectTaskService.selectTaskById(id));
	}

	/**
	 * 统计任务检查的学校（学校类型）
	 *
	 * @param id 任务ID
	 */
	@GetMapping("/stat/{id}")
	public R statSchoolByType(@PathVariable String id) {
		return new R<>(inspectTaskService.statSchoolByType(id));
	}
	
	/**
	 * 检查报告导出
	 */
	@RequestMapping("/exportReport")
	public void exportReport(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
		inspectReportService.exportReport(request,response);
	}
	
	/**
	 * 编辑获取任务信息
	 */
	@GetMapping("/taskDetail/{id}")
	public R taskDetailById(@PathVariable String id) {
		return new R<>(inspectTaskService.selectTaskDetailById(id));
	}
	

}
