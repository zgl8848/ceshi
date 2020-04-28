package com.campus.warning.api.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermalAlarm {
	/**
	 * 方法名，用于标识报文用途
	 */
    private String method;
    /**
  	 * 	事件参数信息
  	 */
    private ThermalParams params;

}