package com.campus.warning.api.entity.behavior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCoordinatesList {
	/**
	 * X轴坐标，取值范围[0.001,1]
	 */
    private Double positionX;
    /**
     * Y轴坐标，取值范围[0.001,1]
     */
    private Double positionY;

}