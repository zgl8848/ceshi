package com.campus.grid.api.entity.saftymanagement;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManageMap implements Serializable {

	private static final long serialVersionUID = 1L;

	private String total;//隐患总数
	private String serious;//严重隐患
	private String area_code;//地区编号

	public ManageMap(String total, String serious, String area_code) {
		this.total = total;
		this.serious = serious;
		this.area_code = area_code;
	}
}
