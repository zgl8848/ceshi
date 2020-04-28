package com.campus.warning.api.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetAttrs {
	/**
	 * 
	 */
    private String bkgUrl;
    /**
     * 	抓拍这张图片的监控点的唯一标识
     */
    private String cameraIndexCode;
    /**
     * 抓拍这张图片的监控点所属的设备的唯一标识
     */
    private String deviceIndexCode;
    /**
     * 抓拍这张图片时的时间
     */
    private String faceTime;

}