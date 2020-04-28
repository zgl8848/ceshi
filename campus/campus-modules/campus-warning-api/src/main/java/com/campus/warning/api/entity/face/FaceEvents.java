package com.campus.warning.api.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceEvents  {
	/**
	 * 	事件详情
	 */
	private FaceData  data ;
	/**
	 * 事件唯一标识
	 */
	private String eventId;
	/**
	 * 	事件源类型
	 */
	private Long eventType;
	/**
	 * 事件发生时间（设备时间）
	 */
	private String happenTime;
	/**
	 * 事件源编号，物理设备是资源编号
	 */
	private String srcIndex;
	/**
	 * 事件源类型
	 */
	private String srcType;
	/**
	 * 事件状态
	 * 0-瞬时
	 * 1-开始 
	 * 2-停止
	 * 3-事件脉冲
	 * 4-事件联动结果更新
	 */
	private Integer status;
	/**
	 * 脉冲超时时间	(单位：秒)
	 */
	private Integer timeout;

}