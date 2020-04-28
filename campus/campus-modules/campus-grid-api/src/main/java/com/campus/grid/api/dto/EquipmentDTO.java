package com.campus.grid.api.dto;

import com.campus.grid.api.entity.equipmententity.Equipment;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class EquipmentDTO implements Serializable {

	private String id;
	private String equipmentId;
	private String groupId;
	private String groupName;
	private String equipmentName;
	private String equipmentCode;
	private String schoolId;
	private String equipmentIp;
	private String equipmentPort;
	private String username;
	private String password;
	private String status;
	private String remark;
	private String createTime;
	private String position;
	private String imgUrl;
	private String schoolName;

	public EquipmentDTO() {
	}

	public EquipmentDTO(Equipment e) {
		this.id = e.getId();
		this.equipmentId = e.getEquipmentId();
		this.groupId = e.getGroupId();
		this.groupName = e.getGroupName();
		this.equipmentName = e.getEquipmentName();
		this.equipmentCode = e.getEquipmentCode();
		this.schoolId = e.getSchoolId();
		this.equipmentIp = e.getEquipmentIp();
		this.equipmentPort = e.getEquipmentPort();
		this.username = e.getUsername();
		this.password = e.getPassword();
		this.status = e.getStatus() == "0" ? "停用" : "正常";
		this.remark = e.getRemark();
		this.position = e.getPosition();
		this.imgUrl = e.getImgUrl();
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getCreateTime());

	}

	@Override
	public String toString() {
		return "EquipmentDTO{" +
				"id='" + id + '\'' +
				", equipmentId='" + equipmentId + '\'' +
				", groupId='" + groupId + '\'' +
				", groupName='" + groupName + '\'' +
				", equipmentName='" + equipmentName + '\'' +
				", equipmentCode='" + equipmentCode + '\'' +
				", schoolId='" + schoolId + '\'' +
				", equipmentIp='" + equipmentIp + '\'' +
				", equipmentPort='" + equipmentPort + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", status='" + status + '\'' +
				", remark='" + remark + '\'' +
				", createTime='" + createTime + '\'' +
				", position='" + position + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", schoolName='" + schoolName + '\'' +
				'}';
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getEquipmentIp() {
		return equipmentIp;
	}

	public void setEquipmentIp(String equipmentIp) {
		this.equipmentIp = equipmentIp;
	}

	public String getEquipmentPort() {
		return equipmentPort;
	}

	public void setEquipmentPort(String equipmentPort) {
		this.equipmentPort = equipmentPort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
