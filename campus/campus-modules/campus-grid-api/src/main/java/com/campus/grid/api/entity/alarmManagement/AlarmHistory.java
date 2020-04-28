package com.campus.grid.api.entity.alarmManagement;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_alarm_history")
public class AlarmHistory extends Model<AlarmHistory> {
	private static final long serialVersionUID = 1L;

	/**
	 * 实时告警id
	 */
	@TableId
	private String id;
	/**
	 * 告警标题
	 */
	private String alarmTitle;
	/**
	 * 告警类型
	 * 0:黑名单告警 1:陌生人告警	2:表情告警	3:巡更告警
	 * 4:巡检告警	5:食品告警	6:着装告警	7:危险驾驶告警
	 * 8:上下车告警	9:越界告警	10:签到告警	11:错误告警
	 */
	private Integer alarmType;
	/**
	 * 告警级别
	 * 0:提示告警	1:普通告警	2:严重告警	3:紧急告警
	 */
	private Integer alarmLevel;
	/**
	 * 告警状态
	 * 0:已处理	1:处理中
	 */
	private Integer alarmState;
	/**
	 * 告警来源
	 */
	private String alarmSource;
	/**
	 * 告警消息
	 */
	private String alarmMsg;
	/**
	 * 学校id
	 */
	private String schoolId;
	/**
	 * 设备ip
	 */
	private String deviceIp;
	/**
	 * 图片url
	 */
	private String picUrl;
	/**
	 * 视频url
	 */
	private String videoUrl;
	/**
	 * 告警描述
	 */
	private String alarmDesc;
	/**
	 * 告警结束时间
	 */
	private Date endTime;
	/**
	 * 告警开始时间
	 */
	private Date startTime;
	/**
	 * 告警类型
	 * 1-设备信息 2-告警信息 3-黑名单告警4-围栏告警
	 */
	private String dataType;
}