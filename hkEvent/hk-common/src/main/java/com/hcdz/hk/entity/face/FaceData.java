package com.hcdz.hk.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceData  {

	/**
	 * 	比对结果
	 */
    private List<AlarmResult> alarmResult ;
    /**
     * 抓拍这张图片的监控点的通道号
     */
    private Integer channelID;
    /**
     * 事件类别，人脸比对的事件类别为faceMatch
     */
    private String dataType;
    /**
     * 人脸比对的事件来源为识别资源的地址。
     */
    private String ipAddress;
    /**
     * 事件来源的端口
     */
    private Integer portNo;

}