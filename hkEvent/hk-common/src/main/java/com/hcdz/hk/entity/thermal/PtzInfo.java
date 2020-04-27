package com.hcdz.hk.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PtzInfo {
	/**
	 * 坐标参数pan，浮点数
	 */
    private String pan;
    /**
     * ptz坐标参数tilt，浮点数
     */
    private String tilt;
    /**
     * ptz坐标参数zoom，浮点数
     */
    private String zoom;
    /**
     * ptz坐标聚焦参数，归一化0-100000
     */
    private String focus;

}