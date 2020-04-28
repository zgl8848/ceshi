package com.campus.grid.api.entity.saftysituation;

import lombok.Data;

import java.io.Serializable;

/*学习次数返回数据*/
@Data
public class EduLearnNum implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year_month; //年月
	private String total;      //安全教育学习总次数

	public EduLearnNum(String year_month, String total) {
		super();
		this.year_month = year_month;
		this.total = total;
	}
}
