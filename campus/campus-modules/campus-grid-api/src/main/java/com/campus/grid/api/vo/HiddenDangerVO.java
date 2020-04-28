package com.campus.grid.api.vo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HiddenDangerVO {

    private Integer current;    //当前页
    private Integer size;   //每页条数
    private String date;    //时间
    private Integer level;  //严重级别
    private String functionId;  //隐患类型id
    private Integer mode;   //处理方式
    private String title;   //
    private Integer status; //状态
    private String ebSchoolId;  //所属学校id
    private String reseauName;  //网格名称
    private String type;    //隱患类型
    private String task = null;    //
}
