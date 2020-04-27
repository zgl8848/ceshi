package com.hcdz.hk.entity.behavior;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorEvents {
	/**
	 * 事件其它扩展信息
	 */
    private BehaviorData data;
    /**
     * 事件唯一标识
     * 注：同一事件若上报多次，则上报事件的eventId相同
     */
    private String eventId;
    /**
     * 事件类型
     */
    private Long eventType;
    /**
     * 事件发生时间（设备时间）
     */
    private String happenTime;
    /**
     * 事件源类型
     */
    private String srcIndex;
    /**
     * 事件源名称
     */
    private String srcName;
    /**
     * 事件源类型
     */
    private String srcType;
    /**
     * 事件状态
     * 0-瞬时
     * 1-开始
     * 2-停止
     * 4-事件联动结果更新
     * 5-事件图片异步上传
     */
    private Integer status;
    /**
     * 脉冲超时时间
     * 单位：秒
     */
    private Integer timeout;
    
    /**
     * 事件等级
     * 0-未配置
     * 1-低
     * 2-中
     * 3-高
     * 注意，此处事件等级是指在事件联动中配置的等级
     */
    private Integer eventLvl;

}