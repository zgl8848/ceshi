package com.campus.warning.api.entity.behavior;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fielddetection {
	/**
	 * 透传字段:部分字段由数据源扩展字段获取
	 */
    private TargetAttrs targetAttrs;
    /**
     * 背景图URL
     */
    private String imageUrl;
    /**
     * 行为事件触发时间阈值:判断有效报警时间
     * 
     */
    private Integer duration;
    /**
     * 灵敏度参数;取值范围：[1,100]
     */
    private Integer sensitivityLevel;
    /**
     * 占比：区域内所有未报警目标尺寸目标占区域面积的比重，归一化为1~100
     */
    private Integer rate;
    /**
     * 检测目标
     * 0-所有
     * 1-人
     * 2-车
     * 3-人和车
     */
    private Integer detectionTarget;
    /**
     * 	区域范围
     */
    private List<RegionCoordinatesList> regionCoordinatesList;
}