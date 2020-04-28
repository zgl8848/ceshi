package com.campus.grid.api.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReseauVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String reseauId;
	/**
	 * 网格名称
	 */
	private String reseauName;
	/**
	 * 学校ID
	 */
	private String schoolId;
	/**
	 * 网格类型 1-楼房 2-平房 3-厅/馆 4-功能空地 5-绿地
	 */
	private Integer type;
	/**
	 * 平面布局名称
	 */
	private String planeName;
	/**
	 * 建筑结构名称
	 */
	private String buildingName;
	/**
	 * 空间结构名称
	 */
	private String spaceName;
	/**
	 * 网格功能ID
	 */
	private String functionId;
	/**
	 * 巡查方式
	 */
	private Integer inspectMode;
	/**
	 * 时间
	 */
	private Long createTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 数据字典表中标签名
	 */
	private String label;
	/**
	 * 数据字典表中值
	 */
	private String value;
	/**
	 * 房间类型:1-房间2-走廊3-楼梯4-公共区域
	 */
	private String spaceType;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 父类功能id
	 */
	private String parentFunctionId;
	/**
	 * 父类功能名称
	 */
	private String parentFunctionName;
	/**
	 * 1-已分配 0-未分配
	 */
	private String reseauAllot;

	/**
	 * 1-删除 0-正常
	 */
	private String planeDelFlag;

	/**
	 * 1-删除 0-正常
	 */
	private String buildingDelFlag;

	/**
	 * 1-删除 0-正常
	 */
	private String reseauDelFlag;
	/**
	 * 类型 1-主要责任人 2-次要责任人
	 */
	private Integer responsiblyType;
	/**
	 * 消防负责人id
	 */
	private String fireUserId;
	/**
	 * 普通负责人id
	 */
	private String UserId;
	/**
	 * 消防负责人名称
	 */
	private String fireUserName;
	/**
	 * 消防巡查方式
	 */
	private Integer fireInspectMode;
	/**
	 * 普通负责人名称
	 */
	private String UserName;
	/**
	 * 巡查开始时间
	 */
	private String strInspectStartDate;

	private LocalDate inspectStartDate;
	/**
	 * 消防巡查开始时间
	 */
	private String strFireInspectStartDate;

	private LocalDate fireInspectStartDate;

	private String qrCodeImg;


}
