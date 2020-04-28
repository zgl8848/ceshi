package com.campus.grid.api.entity;

import lombok.Data;

@Data
public class PersonnelInfo {

    //岗位id
    private String schoolDivisionId;
    //人员id
    private String supervisorId;
    //备注
    private String remarks;
    //是否为主管1-是 0-否
    private String type;
    //人员姓名
    private String userName;
    //手机号码
    private String phone;

}
