package com.campus.grid.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class FireEquipment implements Serializable {
	/**
	 * id
	 */
	private String id;
	/**
	 * 网格
	 */
	private String reseauId;
	/**
	 * 设备名称
	 */
	private String name;
	/**
	 * 录入人id
	 */
	private String userId;
	/**
	 *学校id
	 */
	private String schoolId;
	/**
	 * 器材类型名称
	 */
	private String model;
	/**
	 * 规格型号
	 */
	private String typeName;
	/**
	 * 设备code
	 */
	private String code;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 *开始时间
	 */
	private LocalDateTime startTime;
	/**
	 * 结束时间
	 */
	private LocalDateTime endTime;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	@Override
	public String toString() {
		return "FireEquipment{" +
				"id='" + id + '\'' +
				", reseauId='" + reseauId + '\'' +
				", name='" + name + '\'' +
				", userId='" + userId + '\'' +
				", schoolId='" + schoolId + '\'' +
				", model='" + model + '\'' +
				", typeName='" + typeName + '\'' +
				", code='" + code + '\'' +
				", status=" + status +
				", remark='" + remark + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", createTime=" + createTime +
				'}';
	}

	public FireEquipment() {
	}

	public FireEquipment(String id, String reseauId, String name, String userId, String schoolId, String model, String typeName, String code, Integer status, String remark, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime createTime) {
		this.id = id;
		this.reseauId = reseauId;
		this.name = name;
		this.userId = userId;
		this.schoolId = schoolId;
		this.model = model;
		this.typeName = typeName;
		this.code = code;
		this.status = status;
		this.remark = remark;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}
}
