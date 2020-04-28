package com.campus.warning.api.entity.firecontrol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireData {
	/**
	 * 检测值
	 * 注：报警事件无此值，监测事件有此值
	 */
    private String value;

}