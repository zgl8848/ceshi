package com.campus.grid.api.entity.alarmManagement;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_sendee")
public class Sendee extends Model<Sendee> {
	private static final long serialVersionUID = 1L;

	/**
	 * 告警接收人id
	 */
//	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	@TableId
	private String id;
	/**
	 * 告警接收人电话
	 */
	private String phoneNumber;
	/**
	 * 告警接收人姓名
	 */
	private String userName;
	/**
	 * 学校id
	 */
	private String schoolId;
	/**
	 * 告警类型
	 * 0:黑名单告警 1:陌生人告警	2:表情告警	3:巡更告警
	 * 4:巡检告警	5:食品告警	6:着装告警	7:危险驾驶告警
	 * 8:上下车告警	9:越界告警	10:签到告警	11:错误告警
	 */
	private Integer alarmType;
	/**
	 * 告警级别
	 * 0:提示告警	1:普通告警	2:严重告警	3:紧急告警
	 */
	private Integer alarmLevel;
}