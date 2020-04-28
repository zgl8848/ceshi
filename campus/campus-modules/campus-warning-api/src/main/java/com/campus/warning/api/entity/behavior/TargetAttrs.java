package com.campus.warning.api.entity.behavior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetAttrs {
	/**
	 * 图片服务编号
	 */
    private String imageServerCode;
    /**
     * 设备编号，平台关联的编码
     */
    private String deviceIndexCode;
    /**
     * 	监控点编码，平台关联的编码
     */
    private String cameraIndexCode;
    /**
     * 通道名称
     */
    private String channelName;
    /**
     * 监控点安装地址
     */
    private String cameraAddress;
    /**
     * 	监控点所在经度
     */
    private Float longitude;
    /**
     * 	监控点所在纬度
     */
    private Float latitude;

}