package com.campus.grid.api.entity.saftysupervise;

import lombok.Data;

import java.io.Serializable;

@Data
public class Classify implements Serializable {

	private static final long serialVersionUID = 1L;
	private String number;  //检查次数  
	private String type;    //检查类型
	private String name;    //类型名称

	public Classify(String number, String type, String name) {
		this.number = number;
		this.type = type;
		this.name = name;
	}
}
