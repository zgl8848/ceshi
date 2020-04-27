package com.hcdz.hk.entity.schoolbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusData {
	/**
	 * 事件唯一标识
	 */
	private String eventIndex;
	/**
	 * 车牌号
	 */
	private String plateNo;
	/**
	 * 车牌类型key
	 */
	private String plateType;
	/**
	 * 车牌类型名称
	 */
	private String plateTypeName;
	/**
	 * 车辆类型key
	 */
	private String vehicleType;
	/**
	 * 车辆类型名称
	 */
	private String vehicleTypeName;
	/**
	 * 过车时间
	 */
	private String crossTime;
	/**
	 * 整型，速度值
	 */
	private Integer speed;
	/**
	 * 布控类型
	 * 	1-被盗车
	 * 2-被抢车
	 * 3-嫌疑车
	 * 4-交通违法车
	 * 5-紧急查控车
	 * 6-违章车
	 */
	private String alarmType;
	/**
	 * 布控类型名称
	 */
	private String alarmTypeName;
	/**
	 * 卡口点主键
	 */
	private String monitorId;
	/**
	 * 卡口点名称
	 */
	private String monitorName;
	/**
	 * 整型，违法类型
	 * 	0–正常过车
	 * 1–超速
	 * 2–逆行
	 * 3–黑名单
	 * 5-违停
	 */
	private Integer illegalType;
	/**
	 * 点位或是区间名称
	 */
	private String mixedName;
	/**
	 * 整型，点位测速或是区间测速类型
	 * 1–点位测速
	 * 2–区间测速
	 */
	private Integer mixedType;
	/**
	 * 点位或是区间id
	 */
	private String mixedId;
	/**
	 * 	卡口点编号
	 */
	private String monitorIndexCode;
	/**
	 * 包含车牌和车辆url
	 */
	private PicUrl picUrl;
	/**
	 * 	图片服务器编号
	 */
	private String imageIndexCode;


}