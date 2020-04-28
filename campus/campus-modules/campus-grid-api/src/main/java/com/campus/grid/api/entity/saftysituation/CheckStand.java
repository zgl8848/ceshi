package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

/*考勤信息*/
@Data
public class CheckStand implements Serializable {
	private static final long serialVersionUID = 1L;
	private String actual;//实际出勤数
	private String leave;//请假人数
	private String absence;//缺勤人数

	public CheckStand(String actual, String leave, String absence) {
		super();
		this.actual = actual;
		this.leave = leave;
		this.absence = absence;
	}
}
