package com.campus.warning.api.entity.behavior;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorData {
	/**
	 * 数据模型标识
	 */
    private String dataType;
    /**
     * 数据接收时间
     */
    private String recvTime;
    /**
     * 数据发送时间
     */
    private String sendTime;
    /**
     * 数据触发时间(有设备产生)
     */
    private String dateTime;
    /**
     * 设备的IP地址
     */
    private String ipAddress;
    /**
     * 设备端口号
     */
    private Integer portNo;
    /**
     * 设备通道号(设备通道号，默认1开始)
     */
    private Integer channelID;
    /**
     * 事件类型(事件类型，与ISAPI不一致)
     */
    private String eventType;
    /**
     * 事件类型名称
     */
    private String eventDescription;
    /**
     * 分析结果
     */
    private List<Fielddetection> fielddetection;

}