package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("inspect_task")
public class InspectTask implements Serializable {
	/**
	 * 主键
	 */
	private String taskId;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 任务创建人
	 */
	private String userId;
	/**
	 * 检查项目数
	 */
	private Integer projectNumber;
	/**
	 * 检查标准数
	 */
	private Integer standardNumber;
	/**
	 * 任务开始日期
	 */
	private Date startDate;
	/**
	 * 任务结束日期
	 */
 	private Date endDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 任务创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 检查涉及到的学校数
	 */
	private Integer schoolNumber;

	public InspectTask() {
	}

	public InspectTask(String taskId, String taskName, String userId, Integer projectNumber, Integer standardNumber, Date startDate, Date endDate, String remark, LocalDateTime createTime) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.userId = userId;
		this.projectNumber = projectNumber;
		this.standardNumber = standardNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.createTime = createTime;
	}
}
