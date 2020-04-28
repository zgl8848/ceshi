package com.campus.grid.api.entity.saftymanagement;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManageOa implements Serializable {

	private static final long serialVersionUID = 1L;
	private String total;// 总隐患
	private String processing;//处理中的隐患
	private String solved; //处理中的隐患
	private String rate;//整改率

	public ManageOa(String total, String processing, String solved, String rate) {
		this.total = total;
		this.processing = processing;
		this.solved = solved;
		this.rate = rate;
	}
}
