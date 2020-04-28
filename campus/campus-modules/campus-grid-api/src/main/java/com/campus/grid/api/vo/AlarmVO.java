package com.campus.grid.api.vo;

import com.campus.grid.api.entity.alarmManagement.Alarm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author hhskj on 2019/11/1 13:51
 */


@Data
@EqualsAndHashCode(callSuper = true)
public class AlarmVO extends Alarm{
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 对应数量
     */
    private Integer alarmCount;
    /**
     * 对应的模块名称
     * (治安监控、校车安全、消防安全、厨房安全、危化品安全、学生行为)
     */
    private String moduleName;

    private String province;

    private String city;

    private String county;
}
