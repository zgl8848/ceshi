package com.campus.grid.api.entity.emotion;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("grid_alarm_person")
public class EmotionPerson implements Serializable {

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;        //人脸id
    private String age;      //年龄时期
    private String emotion; //情绪
    private String faceid;  //人脸id
    private String glasses; //是否有戴眼镜
    private String muffle;  //是否有戴口罩
    private String race;    //种族
    private String sex;     //性别
    private String alarmId; //告警信息id
}
