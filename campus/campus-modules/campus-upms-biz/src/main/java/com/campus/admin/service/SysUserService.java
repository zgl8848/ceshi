package com.campus.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.dto.UserInfo;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.admin.api.vo.UserVO;
import com.campus.common.core.util.R;

import java.util.List;

/**
 * @author campus
 * @date 2017/10/31
 */
public interface SysUserService extends IService<SysUser> {
	/**
	 * 查询用户信息
	 *
	 * @param sysUser 用户
	 * @return userInfo
	 */
	UserInfo findUserInfo(SysUser sysUser);

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	IPage getUsersWithRolePage(Page page, UserDTO userDTO);

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean deleteUserById(SysUser sysUser);

	/**
	 * 更新当前用户基本信息
	 *
	 * @param userDto 用户信息
	 * @return Boolean
	 */
	R<Boolean> updateUserInfo(UserDTO userDto);

	/**
	 * 更新指定用户信息
	 *
	 * @param userDto 用户信息
	 * @return
	 */
	Boolean updateUser(UserDTO userDto);

	/**
	 * 更新指定用户密码
	 *
	 * @param userDto 用户信息
	 * @return
	 */
	R<Boolean> updateUserPasswordById(UserDTO userDto);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserVO selectUserVoById(String id);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	List<SysUser> listAncestorUsers(String username);

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	R saveUser(UserDTO userDto);

	/**
	 * 修改所有用户的状态信息
	 *
	 * @param schoolId
	 * @param lockFlag
	 * @return
	 */
	R<Boolean> updateUserLockFlagStatus(String schoolId, String lockFlag);

	/**
	 * 根据巡查人id和整改人id查询真实姓名
	 * @param userId
	 * @param rectification
	 * @return
	 */
    TrueNameVO selectTrueName(String userId, String rectification);
}
