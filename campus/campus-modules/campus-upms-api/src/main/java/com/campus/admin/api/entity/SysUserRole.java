package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 角色ID
	 */
	private String roleId;

}
