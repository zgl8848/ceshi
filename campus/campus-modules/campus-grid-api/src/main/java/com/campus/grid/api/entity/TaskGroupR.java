package com.campus.grid.api.entity;

import lombok.Data;

@Data
public class TaskGroupR {
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 小组id
	 */
	private String groupId;
	/**
	 * 教育局id
	 */
	private String deptId;

	public TaskGroupR() {
	}

	public TaskGroupR(String taskId, String groupId, String deptId) {
		this.taskId = taskId;
		this.groupId = groupId;
		this.deptId = deptId;
	}
}
