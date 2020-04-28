package com.campus.grid.api.entity.emotion;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Device implements Serializable{

    private String alarm_msg;   //告警消息
    private String alarm_source;    //告警来源
    private String alarm_desc;  //告警描述
    private String deviceIP;    //机器ip
}
