package com.campus.grid.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SafetyInspect implements Serializable {
	//id
	private String inspectId;
	//学校id
	private String schoolId;
	//学校名称
	private String schoolName;
	//网格id
	private String reseauId;
	//网格名称
	private String reseauName;
	//巡查人名称
	private String userName;
	//问题类型名称
	private String functionName;
	//级别
	private Integer level;
	private Integer status;
	//整改人id
	private String rectification;
	//描述
	private String remark;
	//创建时间
	private LocalDateTime createTime;
	//完成时间
	private LocalDateTime updateTime;
	//完成描述
	private String finishDesc;
	//类型
	private Integer type;

	public SafetyInspect() {
	}

	public SafetyInspect(String inspectId, String schoolId, String schoolName, String reseauId, String reseauName, String userName, String functionName, Integer level, Integer status, String rectification, String remark, LocalDateTime createTime, LocalDateTime updateTime, String finishDesc, Integer type) {
		this.inspectId = inspectId;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.reseauId = reseauId;
		this.reseauName = reseauName;
		this.userName = userName;
		this.functionName = functionName;
		this.level = level;
		this.status = status;
		this.rectification = rectification;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.finishDesc = finishDesc;
		this.type = type;
	}
}
