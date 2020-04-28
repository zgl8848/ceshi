package com.campus.grid.api.entity.saftysupervise;

import lombok.Data;

import java.io.Serializable;

@Data
public class OverAll implements Serializable {

	private static final long serialVersionUID = 1L;

	private String inspect;  //检查次数
	private String question; //问题个数
	private String hidden_danger;   //隐患个数
	private String rate;            //合格率

	public OverAll(String inspect, String question, String hidden_danger, String rate) {
		this.inspect = inspect;
		this.question = question;
		this.hidden_danger = hidden_danger;
		this.rate = rate;
	}
}
