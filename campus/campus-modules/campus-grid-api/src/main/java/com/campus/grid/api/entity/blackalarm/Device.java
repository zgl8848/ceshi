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
public class Device implements Serializable {
	private String deviceIP;
	private String deviceLocation;
	private String deviceType;
	private String latitude;
	private String longitude;
}
