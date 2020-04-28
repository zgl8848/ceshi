package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolRank implements Serializable {

	private static final long serialVersionUID = 1L;
	private String school_id;
	private String school_name;
	private String integral;

	public SchoolRank(String school_id, String school_name, String integral) {
		super();
		this.school_id = school_id;
		this.school_name = school_name;
		this.integral = integral;
	}
}
