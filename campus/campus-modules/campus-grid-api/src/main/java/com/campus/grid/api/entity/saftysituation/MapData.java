package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String school_total;   //学校总数
	private String teacher_total;  //老师总数
	private String student_total; //学生总数
	private String area_code;     //地区编号

	public MapData(String school_total, String teacher_total, String student_total, String area_code) {
		super();
		this.school_total = school_total;
		this.teacher_total = teacher_total;
		this.student_total = student_total;
		this.area_code = area_code;
	}
}
