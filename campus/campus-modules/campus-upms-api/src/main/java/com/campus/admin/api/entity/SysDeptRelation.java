package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author campus
 * @since 2018-01-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept_relation")
public class SysDeptRelation extends Model<SysDeptRelation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	private String ancestor;
	/**
	 * 后代节点
	 */
	private String descendant;


}
