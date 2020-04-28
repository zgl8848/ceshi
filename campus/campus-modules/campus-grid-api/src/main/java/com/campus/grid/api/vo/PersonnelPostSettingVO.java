package com.campus.grid.api.vo;

import lombok.Data;

@Data
public class PersonnelPostSettingVO{

    //学校id
    private String schoolId;
    //学校名称
    private String schoolName;
    //学校岗位id
    private int postId;
    //学校岗位名称
    private String postName;
}
