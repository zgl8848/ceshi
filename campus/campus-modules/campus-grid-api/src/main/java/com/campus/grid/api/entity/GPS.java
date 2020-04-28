package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_gps")
public class GPS extends Model<GPS> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 商家id
	 */
	private String merchant_id;
	/**
	 * 设备id
	 */
	private String device_id;
	/**
	 * 行程id
	 */
	private String trip_id;
	/**
	 * 用户id
	 */
	private String user_id;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 经度（高德火星坐标）
	 */
	private String longtitude;
	/**
	 * 纬度（高德火星坐标）
	 */
	private String altitude;
	/**
	 * 速度
	 */
	private String speed;
	/**
	 * 当前状态，是在线还是离线，0表示离线，1表示在线
	 */
	private String state;
	/**
	 * 经纬度翻译后的地理位置信息
	 */
	private String location;
	/**
	 * 事件发生时间
	 */
	private Date time;
	/**
	 * heading
	 */
	private String heading;
	/**
	 * uuid
	 */
	private String uuid;
}
