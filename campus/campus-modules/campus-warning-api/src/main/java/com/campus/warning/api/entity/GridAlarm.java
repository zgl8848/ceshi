package com.campus.warning.api.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("grid_alarm")
public class GridAlarm {
	/**
	 * 实时告警id
	 */
	private String id;
	/**
	 * 告警标题
	 */
	private String alarmTitle;
	/**
	 * 告警模块
	 */
	private String alarmModule;
	/**
	 * 告警类型
	 */
	private String alarmType;
	
	/**
	 * 告警级别
	 */
	private Integer alarmLevel;
	/**
	 * 告警状态
	 */
	private Integer alarmState;
	/**
	 * 告警来源(暂无，固定一个来源)
	 */
	private String alarmSource;
	/**
	 * 告警消息(根据告警模块命名)
	 */
	private String alarmMsg;
	/**
	 * 学校ID(暂无，设置一个固定id)
	 */
	private String schoolId;
	/**
	 * 设备IP
	 */
	private String deviceIp;
	/**
	 * 图片URL
	 */
	private String picUrl;
	/**
	 * 视频URL(暂无，固定一个地址)
	 */
	private String vedioUrl;
	/**
	 * 告警描述
	 */
	private String alarmDesc;
	/**
	 * 结束时间
	 */
	private LocalDateTime endTime;
	/**
	 * 开始时间
	 */
	private LocalDateTime startTime;
	/**
	 * 告警类型(1-设备信息 2-告警信息 3-黑名单告警4-围栏告警)
	 */
	private String dataType;
	
}
