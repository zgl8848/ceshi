package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_reseau")
public class Reseau extends Model<Reseau> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "reseauId", type = IdType.ID_WORKER_STR)
	private String reseauId;
	/**
	 * 网格名称
	 */
	private String reseauName;
	/**
	 * 学校ID
	 */
	private String schoolId;
	/**
	 * 网格类型 1-楼房 2-平房 3-厅/馆 4-功能空地 5-绿地
	 */
	private Integer type;
	/**
	 * 平面布局名称
	 */
	private String planeName;
	/**
	 * 建筑结构名称
	 */
	private String buildingName;
	/**
	 * 空间结构名称
	 */
	private String spaceName;
	/**
	 * 网格功能ID
	 */
	private String functionId;
	/**
	 * 网格父类功能id
	 */
	private String parentFunctionId;
	/**
	 * 巡查方式
	 */
	private Integer inspectMode;
	/**
	 * 时间
	 */
	private Long createTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 房间类型:1-房间2-走廊3-楼梯4-公共区域
	 */
	private String spaceType;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 父类功能名称
	 */
	private String parentFunctionName;
	/**
	 * 用户id
	 */
	private String UserId;

	/**
	 * 用户名称
	 */
	private String UserName;

	/**
	 * 1-已分配 0-未分配
	 */
	private String reseauAllot;

	/**
	 * 1-删除 0-正常
	 */
	private String planeDelFlag;

	/**
	 * 1-删除 0-正常
	 */
	private String buildingDelFlag;

	/**
	 * 1-删除 0-正常
	 */
	private String reseauDelFlag;

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 类型 1-巡查责任人 2-消防责任人
	 * */
	private String responsiblyType;
	/**
	 * 网格职责
	 */
	private String duty;

	private List<ReseauUser> reseauUserList;
}
