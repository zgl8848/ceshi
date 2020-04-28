package com.campus.grid.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeadMasterInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String trueName;
	private String phone;

	public HeadMasterInfo(String userId, String trueName, String phone) {
		super();
		this.userId = userId;
		this.trueName = trueName;
		this.phone = phone;
	}

}
