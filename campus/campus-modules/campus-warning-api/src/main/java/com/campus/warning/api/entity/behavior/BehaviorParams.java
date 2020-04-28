package com.campus.warning.api.entity.behavior;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorParams {
	/**
	 * 	事件唯一标识
	 * 	注：同一事件若上报多次，则上报事件的eventId相同
	 */
    private String ability;
    /**
     *	 事件信息
     */
    private List<BehaviorEvents> events;
    /**
     * 	事件从接收者（程序处理后）发出的时间
     */
    private String sendTime;

}