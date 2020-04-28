package com.campus.grid.api.entity.emotion.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "grid_alarm_person")
public class MongoEmotionPerson implements Serializable {

    @Id
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
