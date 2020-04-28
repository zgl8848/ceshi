package com.campus.grid.api.entity.saftysupervise;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TrendChart implements Serializable {
	private static final long serialVersionUID = 1L;
	private String inspectType; //检查类型
	private String inspectName; //类型名称
	private List<EveryData> inspectData; //数据结果

	public TrendChart(String inspectType, String inspectName, List inspectData) {
		this.inspectType = inspectType;
		this.inspectName = inspectName;
		this.inspectData = inspectData;
	}
}
