package com.campus.warning.api.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceRect {
	/**
	 * 宽度
	 */
    private Double height;
    /**
     * 高度
     */
    private Double width;
    /**
     * X坐标
     */
    private Double x;
    /**
     * Y坐标
     */
    private Double y;

}