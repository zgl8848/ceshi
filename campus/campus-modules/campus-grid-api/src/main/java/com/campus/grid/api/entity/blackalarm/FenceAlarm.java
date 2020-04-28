package com.campus.grid.api.entity.blackalarm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FenceAlarm implements Serializable {
	private String alarmMsg;
	private String alarmTitle;
	private String dataType;
	private String deviceId;
	private String deviceIp;
	private String eventId;
	private String loginId;
	private String schoolId;
	private String startTime;
}
