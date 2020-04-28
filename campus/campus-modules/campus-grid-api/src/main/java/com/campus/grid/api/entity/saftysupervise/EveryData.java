package com.campus.grid.api.entity.saftysupervise;

import lombok.Data;

import java.io.Serializable;

@Data
public class EveryData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String month; //月份
	private String number;//检查次数

	public EveryData(String month, String number) {
		this.month = month;
		this.number = number;
	}
}
