package com.campus.grid.api.dto;

public class FunctionTypeManageDTO {
	private String value;
	private String parentId;
	private String parentValue;
	private String grandParentId;
	private String grandParentValue;

	public FunctionTypeManageDTO() {
	}

	public FunctionTypeManageDTO(String value, String parentId, String parentValue, String grandParentId, String grandParentValue) {
		this.value = value;
		this.parentId = parentId;
		this.parentValue = parentValue;
		this.grandParentId = grandParentId;
		this.grandParentValue = grandParentValue;
	}

	@Override
	public String toString() {
		return "FunctionTypeManageDTO{" +
				"value='" + value + '\'' +
				", parentId='" + parentId + '\'' +
				", parentValue='" + parentValue + '\'' +
				", grandParentId='" + grandParentId + '\'' +
				", grandParentValue='" + grandParentValue + '\'' +
				'}';
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentValue() {
		return parentValue;
	}

	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}

	public String getGrandParentId() {
		return grandParentId;
	}

	public void setGrandParentId(String grandParentId) {
		this.grandParentId = grandParentId;
	}

	public String getGrandParentValue() {
		return grandParentValue;
	}

	public void setGrandParentValue(String grandParentValue) {
		this.grandParentValue = grandParentValue;
	}
}
