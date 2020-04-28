package com.campus.warning.api.entity.behavior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorAlarm {
	/**
	 * 	方法名，用于标识报文用途
	 */
    private String method;
    /**
     * 	事件参数信息
     */
    private BehaviorParams params;

}