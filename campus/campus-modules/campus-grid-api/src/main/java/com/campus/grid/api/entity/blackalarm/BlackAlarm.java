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
public class BlackAlarm implements Serializable {
	private String dataType;
	private Device device;
	private String eventID;
	private String eventLevel;
	private String eventTime;
	private String eventType;
	private Person person;
	private String pic_data;
	private String platformIp;
	private String platformPort;
	private String schoolId;
	private String state;
}
