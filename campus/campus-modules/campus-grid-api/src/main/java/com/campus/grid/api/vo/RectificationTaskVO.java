package com.campus.grid.api.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RectificationTaskVO {
	/**
	 * 主键ID
	 */
	private String reseauId;
	/**
	 * 网格名称
	 */
	private String reseauName;
	/**
	 * 网格类型 1-楼房 2-平房 3-厅/馆 4-功能空地 5-绿地
	 */
	private Integer type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 平面布局名称
	 */
	private String planeName;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
		 * 父类功能名称
	 */
	private String parentFunctionName;
	/**
	 * 负责人id
	 */
	private String userId;
	/**
	 * 类型 1-巡查 2-消防
	 */
	private Integer responsiblyType;
	/**
	 * 负责人名称
	 */
	private String userName;
	/**
	 * 消防巡查方式
	 */
	private Integer inspectMode;
	/**
	 * 巡查开始时间
	 */
	private LocalDateTime inspectStartDate;

	public RectificationTaskVO() {
	}

	public RectificationTaskVO(String reseauId, String reseauName, Integer type, String remark, String planeName, String functionName, String parentFunctionName, String userId, Integer responsiblyType, String userName, Integer inspectMode, LocalDateTime inspectStartDate) {
		this.reseauId = reseauId;
		this.reseauName = reseauName;
		this.type = type;
		this.remark = remark;
		this.planeName = planeName;
		this.functionName = functionName;
		this.parentFunctionName = parentFunctionName;
		this.userId = userId;
		this.responsiblyType = responsiblyType;
		this.userName = userName;
		this.inspectMode = inspectMode;
		this.inspectStartDate = inspectStartDate;
	}
}
