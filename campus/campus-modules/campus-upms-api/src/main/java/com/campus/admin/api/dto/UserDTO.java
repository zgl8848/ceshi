package com.campus.admin.api.dto;

import com.campus.admin.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author campus
 * @date 2017/11/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	/**
	 * 角色ID
	 */
	private List<String> role;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 新密码
	 */
	private String newpassword1;
	/**
	 * 用户角色类型
	 */
	private String roleType;
	/**
	 * 是否督查小组组长
	 */
	private String isLeader;
	/**
	 * 小组ID
	 */
	private String groupId;
}
