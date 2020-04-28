package com.campus.warning.api.entity.schoolbus;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusAlarm {
	/**
	 * 方法名，用于标识报文用途
	 */
    private String method;
    /**
  	 * 	事件参数信息
  	 */
    private BusParams params;

}