package com.campus.admin.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.SysRole;
import com.campus.admin.api.entity.SysRoleMenu;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.mapper.SysRoleMapper;
import com.campus.admin.mapper.SysRoleMenuMapper;
import com.campus.admin.mapper.SysUserMapper;
import com.campus.admin.service.SysRoleService;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;

import lombok.AllArgsConstructor;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	private SysRoleMenuMapper sysRoleMenuMapper;
	private SysRoleMapper sysRoleMapper;
	private SysUserMapper sysUserMapper;

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List findRolesByUserId(String userId) {
		return baseMapper.listRolesByUserId(userId);
	}

	/**
	 * 通过角色ID，删除角色,并清空角色菜单缓存
	 *
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public R<Boolean> removeRoleById(String id) {
		if (StringUtils.isNotBlank(id)) {
			// 查询该角色下有没有用户有的话提示用户
			List<SysUser> sysUserList = sysUserMapper.listUsersByRoleIds(id);
			if (sysUserList.size() > 0) {
				return new R<>(Boolean.FALSE, "请先删除当前角色下的用户");
			}
		}
		sysRoleMenuMapper.delete(Wrappers
				.<SysRoleMenu>update().lambda()
				.eq(SysRoleMenu::getRoleId, id));

		this.removeById(id);
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 通过角色ID，获取所角色的类型，再根据类型获取所有的角色详情
	 *
	 * @return
	 */
	@Override
	public List<SysRole> listRoles() {
		// 判断当前登录用户的角色类型
		String roleType = SecurityUtils.getUser().getRoleType();
		if (StringUtils.isNotBlank(roleType)) {
			// 如果当前角色类型不是admin，则需要传入角色类型的值
			if (!CommonConstants.ROLE_TYPE_ADMIN.equals(roleType)) {
				return sysRoleMapper.listRolesByRoleType(roleType);
			}
		}
		return sysRoleMapper.listRolesByRoleType(null);
	}
	
	@Override
	public boolean checkRoleCode(String roleCode) {
		List<SysRole> list=sysRoleMapper.checkRoleCode(roleCode);
		if(list!=null&&list.size()>0) {
			return false;
		}
		return true;
	}
}
