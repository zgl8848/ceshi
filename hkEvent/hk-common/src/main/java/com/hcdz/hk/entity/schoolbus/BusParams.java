package com.hcdz.hk.entity.schoolbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusParams {
	/**
	 * 事件类别
	 */
    private String ability;
    /**
     * 事件信息
     */
    private List<BusEvents> events;
    /**
     * 事件从接收者（程序处理后）发出的时间
     */
    private String  sendTime;

}