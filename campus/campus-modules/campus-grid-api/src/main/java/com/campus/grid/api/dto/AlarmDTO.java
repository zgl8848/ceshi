package com.campus.grid.api.dto;

import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class AlarmDTO implements Serializable {

	private String id;
	private String alarmTitle;
	private String alarmType;
	private String alarmLevel;
	private String alarmState;
	private String alarmSource;
	private String alarmMsg;
	private String schoolName;
	private String deviceIp;
	private String picUrl;
	private String videoUrl;
	private String alarmDesc;
	private String endTime;
	private String startTime;
	private Person person;

	public AlarmDTO(Alarm alarm) {
		this.id = alarm.getId();
		this.alarmTitle = alarm.getAlarmTitle();
		Integer level = Integer.valueOf(alarm.getAlarmLevel());
		if (level == 0){
			this.alarmLevel = "提示告警";
		}
		if (level == 1){
			this.alarmLevel = "普通告警";
		}
		if (level == 2){
			this.alarmLevel = "严重告警";
		}
		if (level == 3){
			this.alarmLevel = "紧急告警";
		}
		Integer state = Integer.valueOf(alarm.getAlarmState());
		if (state == 0){
			this.alarmState = "已处理";
		}
		if (state == 1){
			this.alarmState = "处理中";
		}
		this.alarmSource = alarm.getAlarmSource();
		this.alarmMsg = alarm.getAlarmMsg();
		this.deviceIp = alarm.getDeviceIp();
		this.picUrl = alarm.getPicUrl();
		this.videoUrl = alarm.getVideoUrl();
		this.alarmDesc = alarm.getAlarmDesc();
		this.startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarm.getStartTime());
	}

	public AlarmDTO() {
	}

	@Override
	public String toString() {
		return "AlarmDTO{" +
				"id='" + id + '\'' +
				", alarmTitle='" + alarmTitle + '\'' +
				", alarmType='" + alarmType + '\'' +
				", alarmLevel='" + alarmLevel + '\'' +
				", alarmState='" + alarmState + '\'' +
				", alarmSource='" + alarmSource + '\'' +
				", alarmMsg='" + alarmMsg + '\'' +
				", schoolName='" + schoolName + '\'' +
				", deviceIp='" + deviceIp + '\'' +
				", picUrl='" + picUrl + '\'' +
				", videoUrl='" + videoUrl + '\'' +
				", alarmDesc='" + alarmDesc + '\'' +
				", endTime='" + endTime + '\'' +
				", startTime='" + startTime + '\'' +
				", person=" + person +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlarmTitle() {
		return alarmTitle;
	}

	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	public String getAlarmSource() {
		return alarmSource;
	}

	public void setAlarmSource(String alarmSource) {
		this.alarmSource = alarmSource;
	}

	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}