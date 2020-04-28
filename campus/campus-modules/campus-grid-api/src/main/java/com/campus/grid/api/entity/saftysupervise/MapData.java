package com.campus.grid.api.entity.saftysupervise;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String school_id;//学校ID
	private String number;      //检测次数 
	private String area_code; //所属区域编号

	public MapData() {
	}

	public MapData(String school_id, String number, String area_code) {
		super();
		this.school_id = school_id;
		this.number = number;
		this.area_code = area_code;
	}
}
