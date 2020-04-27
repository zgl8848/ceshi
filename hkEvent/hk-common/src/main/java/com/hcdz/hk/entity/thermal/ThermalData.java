package com.hcdz.hk.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermalData {
	/**
	 * 数据模型标识
	 */
    private String dataType;
    /**
     * 数据接收时间
     */
    private String recvTime;
    /**
     * 	数据发送时间
     */
    private String sendTime;
    /**
     * 	数据触发时间
     */
    private String dateTime;
    /**
     * 设备的IP地址
     */
    private String ipAddress;
    /**
     *设备端口号
     */
    private Integer portNo;
    /**
     * 设备通道号
     */
    private Integer channelID;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件类型名称
     */
    private String eventDescription;
    /**
     * 分析结果	
     */
    private List<ThermometryDiff> thermometryDiff;

}