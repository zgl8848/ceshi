package com.campus.admin.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictInfo implements Serializable {
	private static final long serialVersionUID = 4291354471316527208L;
	/**
	 * 字典类型
	 */
	private String type;
	/**
	 * 字典数值
	 */
	private String value;
	/**
	 * 职责
	 */
	private String remarks;
}
