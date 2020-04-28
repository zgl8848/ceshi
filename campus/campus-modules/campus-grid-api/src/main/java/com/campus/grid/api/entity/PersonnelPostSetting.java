package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("grid_personnel_post")
public class PersonnelPostSetting extends Model<PersonnelPostSetting> {
    private static final long serialVersionUID = 1L;

    //主键
    @TableId
    private int postId;
    //岗位名称
    private String postName;
    //岗位类型  1-校长 2-副校长 3-工会主席 4-保卫科主任
    private String postType;
    //岗位职责
    private String responsibility;
    //学校id
    private String schoolId;
    //上级岗位id
    private String higherPositionId;
    //上级岗位名称
    private String  higherPositionName;
    //人员姓名
    private String personnelName;
    //人员id
    private String supervisorId;
    //统计数量
    private String count;
    //更新时间
    private Date time;
    //备注
    private String remarks;
    //手机号码
    private String phone;
    //状态
    private String type;
}
