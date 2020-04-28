package com.campus.grid.api.entity.emotion;

import com.campus.grid.api.entity.blackalarm.Device;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmotionAlarm implements Serializable{
    private String dataType;    //标识类型
    private String eventID;     //告警id
    private String eventLevel;  //告警级别
    private String eventTime;   //开始的时间
    private String eventType;   //告警标题
    private String pic_data;     //照片路径
    private String platformIp;  //本机ip
    private String schoolId;    //学校编号(根据编号得到学校id)
    private String state;       //告警状态
    private Device device;
    private EmotionPerson person;
}
