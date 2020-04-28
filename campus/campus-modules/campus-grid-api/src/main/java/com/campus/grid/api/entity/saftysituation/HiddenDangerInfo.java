package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

@Data
public class HiddenDangerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String total;          //隐患总数
	private String untreated;      //未处理的隐患数
	private String completion_rate;//完成率

	public HiddenDangerInfo(String total, String untreated, String completion_rate) {
		super();
		this.total = total;
		this.untreated = untreated;
		this.completion_rate = completion_rate;
	}
}
