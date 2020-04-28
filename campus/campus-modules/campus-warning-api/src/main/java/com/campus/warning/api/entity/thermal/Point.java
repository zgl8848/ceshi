package com.campus.warning.api.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
	/**
	 * 测温坐标点，浮点数
	 */
    private String x;
    /**
     * 测温坐标点，浮点数
     */
    private String y;

}