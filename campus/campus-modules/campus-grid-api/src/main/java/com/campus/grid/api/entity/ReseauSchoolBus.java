package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("grid_reseau_bus")
public class ReseauSchoolBus{

    //校车网格id
    private String carReseauId;
    //网格类型
    private int topType;
    //学校id
    private String schoolId;
    //校车id
    private String carId;
    //校车名称
    private String carName;
    //检查类型 1-车辆设备检查 2-司机检查项 3-跟车员检查项
    private int type;
    //检查子项名称
    private String checkName;
    //状态 0-正常 1-删除
    private int delFlag;
    //0-未分配 1-已分配
    private int reseauAllot;
    //巡查方式
    private int inspectMode;
    //负责人
    private String userId;
    //备注
    private String remark;
    //网格功能id
    private String functionId;
    //创建人
    private String founder;
    //创建时间
    private Date creationTime;
    //更改人id
    private String changePeople;
    //更改时间
    private Date changeTime;
}
