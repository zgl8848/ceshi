package com.campus.grid.api.dto;

import lombok.Data;

@Data
public class TaskSchoolRDTO {
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 学校id
	 */
	private String schoolId;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 状态 检查状态（0未检查、1自查待审核、2小组已审核、3待审核、4督查完成）
	 */
	private String status;

	public TaskSchoolRDTO() {
	}

	public TaskSchoolRDTO(String taskId, String schoolId, String schoolName, String status) {
		this.taskId = taskId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.status = status;
	}
}
