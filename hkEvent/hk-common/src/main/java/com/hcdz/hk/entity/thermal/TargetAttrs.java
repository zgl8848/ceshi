package com.hcdz.hk.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetAttrs {
	/**
	 * 测温规则信息
	 */
	private String imageServerCode;
	/**
	 * 设备编号，平台关联的编码
	 */
	private String deviceIndexCode;
	/**
	 * 监控点编码，平台关联的编码
	 */
	private String cameraIndexCode;
	/**
	 * 监控点安装地址
	 */
	private String cameraAddress;
	/**
	 * 监控点所在经度
	 */
	private Double longitude;
	/**
	 * 监控点所在纬度
	 */
	private Double latitude;

}