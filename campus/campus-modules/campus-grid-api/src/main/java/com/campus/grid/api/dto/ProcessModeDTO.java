package com.campus.grid.api.dto;

public class ProcessModeDTO {
	private String processModId;
	private String processModeValue;

	public ProcessModeDTO(String processModId, String processModeValue) {
		this.processModId = processModId;
		this.processModeValue = processModeValue;
	}

	public ProcessModeDTO() {
	}

	@Override
	public String toString() {
		return "ProcessModeDTO{" +
				"processModId='" + processModId + '\'' +
				", processModeValue='" + processModeValue + '\'' +
				'}';
	}

	public String getProcessModId() {
		return processModId;
	}

	public void setProcessModId(String processModId) {
		this.processModId = processModId;
	}

	public String getProcessModeValue() {
		return processModeValue;
	}

	public void setProcessModeValue(String processModeValue) {
		this.processModeValue = processModeValue;
	}
}
