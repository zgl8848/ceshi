package com.campus.grid.api.entity;

public class TaskProjectR {
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 检查项目id
	 */
	private String project_id;

	public TaskProjectR() {
	}

	public TaskProjectR(String taskId, String project_id) {
		this.taskId = taskId;
		this.project_id = project_id;
	}
}
