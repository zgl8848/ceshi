package com.campus.grid.api.entity.alarmManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_alarm")
public class Alarm extends Model<Alarm> {
	private static final long serialVersionUID = 1L;

	/**
	 * 实时告警id
	 */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 告警标题
	 */
	private String alarmTitle;
	/**
	 * 告警类型
	 */
	private String alarmType;
	/**
	 * 告警级别
	 */
	private String alarmLevel;
	/**
	 * 告警状态
	 * 0:已处理	1:处理中
	 */
	private String alarmState;
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
	 * 告警模块
	 */
	private String alarmModule;

	/**
	 * 告警类型
	 * 1-设备信息 2-告警信息 3-黑名单告警4-围栏告警 5-表情告警
	 */
	private String dataType;

	public Alarm(String alarmTitle, String alarmType, String alarmLevel, String alarmState, String alarmSource, String alarmMsg, String schoolId, String deviceIp, String picUrl, String videoUrl, String alarmDesc, Date endTime, Date startTime, String dataType) {
		this.dataType = dataType;
		this.alarmTitle = alarmTitle;
		this.alarmType = alarmType;
		this.alarmLevel = alarmLevel;
		this.alarmState = alarmState;
		this.alarmSource = alarmSource;
		this.alarmMsg = alarmMsg;
		this.schoolId = schoolId;
		this.deviceIp = deviceIp;
		this.picUrl = picUrl;
		this.videoUrl = videoUrl;
		this.alarmDesc = alarmDesc;
		this.endTime = endTime;
		this.startTime = startTime;
	}

	public Alarm(String id, String alarmTitle, String alarmType, String alarmLevel, String alarmState, String alarmSource, String alarmMsg, String schoolId, String deviceIp, String picUrl, String videoUrl, String alarmDesc, Date endTime, Date startTime, String dataType) {
		this.id = id;
		this.alarmTitle = alarmTitle;
		this.alarmType = alarmType;
		this.alarmLevel = alarmLevel;
		this.alarmState = alarmState;
		this.alarmSource = alarmSource;
		this.alarmMsg = alarmMsg;
		this.schoolId = schoolId;
		this.deviceIp = deviceIp;
		this.picUrl = picUrl;
		this.videoUrl = videoUrl;
		this.alarmDesc = alarmDesc;
		this.endTime = endTime;
		this.startTime = startTime;
		this.dataType = dataType;
	}
}






