package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

@Data
public class InspectInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String total;// 巡查点总数
	private String question_spot;//问题点
	private String completion_rate;//完成率

	public InspectInfo(String total, String question_spot, String completion_rate) {
		super();
		this.total = total;
		this.question_spot = question_spot;
		this.completion_rate = completion_rate;
	}
}
