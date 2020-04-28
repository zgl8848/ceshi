package com.campus.grid.api.entity.saftymanagement;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManageTrendData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String number;//数量
	private String month;//月份

	public ManageTrendData(String number, String month) {
		super();
		this.number = number;
		this.month = month;
	}
}
