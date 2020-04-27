package com.hcdz.hk.entity.schoolbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicUrl {
	/**
	 * 	车牌url
	 */
    private String platePicUrl;
    /**
     * 车辆url
     */
    private String vehiclePicUrl;

}