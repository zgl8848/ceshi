package com.hcdz.hk.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve_field {
	/**
	 * 出生日期，格式形如20000101
	 */
    private String bornTime;
    /**
     * 证件号码	
     */
    private String certificateNumber;
    /**
     * 证件类型
     */
    private String certificateType;
    /**
     * 性别类型
     */
    private String gender;
    /**
     * 人脸姓名
     */
    private String name;

}