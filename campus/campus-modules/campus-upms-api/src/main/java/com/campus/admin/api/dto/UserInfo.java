package com.campus.admin.api.dto;

import com.campus.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author campus
 * @date 2017/11/11
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -3171233679005431775L;
	/**
	 * 用户基本信息R
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;
	/**
	 * 角色集合
	 */
	private String[] roles;
	/**
	 * 角色类型
	 */
	private String roleType;
}
