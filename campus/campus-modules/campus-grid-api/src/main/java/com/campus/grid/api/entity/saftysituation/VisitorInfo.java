package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisitorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String visitor_total; //访客总数
	private String receive_total; //接待总数

	public VisitorInfo(String visitor_total, String receive_total) {
		super();
		this.visitor_total = visitor_total;
		this.receive_total = receive_total;
	}
}
