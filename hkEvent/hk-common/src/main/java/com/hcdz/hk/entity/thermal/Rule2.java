package com.hcdz.hk.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule2 {
	/**
	 * 	规则id
	 */
    private Integer id;
    /**
     * 测温坐标，当规则标定类型为框时，最多十个坐标点
     */
    private List<Point> point;

}