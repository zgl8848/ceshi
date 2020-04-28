package com.campus.grid.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HiddenDanger implements Serializable {
	/**
	 * id
	 */
	private String inspectId;
	/**
	 * 学校id
	 */
	private String schoolId;
	/**
	 * 学校姓名
	 */
	private String schoolName;
	/**
	 * 网格id
	 */
	private String reseauId;
	/**
	 * 网格名称
	 */
	private String reseauName;
	/**
	 * 用户姓名或id
	 */
	private String userId;
	/**
	 * 问题类型名称或id
	 */
	private String functionId;
	/**
	 * 严重级别
	 */
	private Integer level;
	/**
	 * 记录状态
	 */
	private Integer status;
	/**
	 * 整改人id或姓名
	 */
	private String rectification;
	/**
	 * 处理方式id
	 */
	private Integer modeId;
	/**
	 * 处理方式名称
	 */
	private String modeName;
	/**
	 * 记录类型
	 */
	private Integer type;
	/**
	 * 记录描述
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 完成时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 完成描述
	 */
	private String finishDesc;

	/**
	 * 部门id
	 */
	private String deptId;

	public HiddenDanger() {
	}

	public HiddenDanger(String inspectId, String schoolId, String schoolName, String reseauId, String reseauName, String userId, String functionId, Integer level, Integer status, String rectification, Integer modeId, String modeName, Integer type, String remark, LocalDateTime createTime, LocalDateTime updateTime, String finishDesc) {
		this.inspectId = inspectId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.reseauId = reseauId;
		this.reseauName = reseauName;
		this.userId = userId;
		this.functionId = functionId;
		this.level = level;
		this.status = status;
		this.rectification = rectification;
		this.modeId = modeId;
		this.modeName = modeName;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.finishDesc = finishDesc;
	}
	public HiddenDanger(String inspectId, String schoolId, String schoolName, String reseauId, String reseauName, String userId, String functionId, Integer level, Integer status, String rectification, Integer modeId, String modeName, Integer type, String remark, LocalDateTime createTime, LocalDateTime updateTime, String finishDesc, String deptId) {
		this.inspectId = inspectId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.reseauId = reseauId;
		this.reseauName = reseauName;
		this.userId = userId;
		this.functionId = functionId;
		this.level = level;
		this.status = status;
		this.rectification = rectification;
		this.modeId = modeId;
		this.modeName = modeName;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.finishDesc = finishDesc;
		this.deptId=deptId;
	}
}
