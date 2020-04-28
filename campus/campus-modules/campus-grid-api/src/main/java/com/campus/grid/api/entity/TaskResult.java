package com.campus.grid.api.entity;

import lombok.Data;

@Data
public class TaskResult {
	/**
	 *任务主键
	 */
	private String taskId;
	/**
	 * 	学校主键
	 */
	private String schoolId;
	/**
	 * 检查项目主键
	 */
	private String projectId;
	/**
	 * 检查标准主键
	 */
	private String standardId;
	/**
	 * 学校检查状态
	 */
	private String schoolStatus;
	/**
	 * 小组检查状态
	 */
	private String groupStatus;
	/**
	 * 教育局检查状态
	 */
	private String deptStatus;

	private String content;

	private String projectName;

	public TaskResult() {
	}

	public TaskResult(String taskId, String schoolId, String projectId, String standardId, String schoolStatus, String groupStatus, String deptStatus) {
		this.taskId = taskId;
		this.schoolId = schoolId;
		this.projectId = projectId;
		this.standardId = standardId;
		this.schoolStatus = schoolStatus;
		this.groupStatus = groupStatus;
		this.deptStatus = deptStatus;
	}
}
