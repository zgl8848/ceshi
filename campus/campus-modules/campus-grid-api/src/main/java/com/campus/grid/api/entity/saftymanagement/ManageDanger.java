package com.campus.grid.api.entity.saftymanagement;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManageDanger implements Serializable {
	private static final long serialVersionUID = 1L;
	private String number;//数量
	private String type; //类型
	private String name;//类型名称

	public ManageDanger(String number, String type, String name) {
		this.number = number;
		this.type = type;
		this.name = name;
	}
}
