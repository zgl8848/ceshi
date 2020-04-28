package com.campus.warning.api.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermometryDiff {
	/**
	 * 透传字段，部分字段由数据源扩展字段获取
	 */
    private TargetAttrs targetAttrs;
    /**
     * 预置点号
     */
    private Integer presetNo;
    /**
     * 0-预警，1-报警
     */
    private Integer alarmLevel;
    /**
     * 报警类型
     * 0-最高温度
     * 1-最低温度
     * 2-平均温度
     */
    private Integer alarmType;
    /**
     * 规则
     * 0-大于
     * 1-小于
     */
    private Integer alarmRule;
    /**
     * 规则标定类型
     * 0-点
     * 1-框
     * 2-线
     */
    private Integer ruleCalibType;
    /**
     * 配置规则温差
     * 精确到小数点后一位(-40-1000),（浮点数+100）
     */
    private String ruleTemperatureDiff;
    /**
     * 当前温差，精确到小数点后一位(-40-1000),（浮点数+100）
     */
    private String curTemperatureDiff;
    /**
     * ptz坐标信息
     */
    private PtzInfo ptzInfo;
    /**
     * 坐标参数pan，浮点数
     */
    private String imageUrl;
    /**
     * 可见光图片URL
     */
    private String visiblePicUrl;
    /**
     * 	热成像附加信息
     */
    private String termalInfo;
    /**
     * 测温单位
     * 0-摄氏度℃
     * 1-华氏度℉
     * 3-开尔文K
     */
    private Integer thermometryUnit;
    /**
     * 容差温度，精确到小数点后一位（-40-1000）（浮点数+100）
     */
    private String toleranceTemperature;
    /**
     * 温度报警等待时间，单位秒，范围0-200秒，默认0秒
     */
    private Integer alarmFilterTime;
    /**
     * 	测温规则信息
     */
    private RuleInfo ruleInfo;

}