package com.campus.grid.api.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostUserVO implements Serializable{
    private static final long serialVersionUID = 1L;

    //用户id
    private String userId;
    //用户姓名
    private String trueName;
    //手机号码
    private String phone;
}
