package com.campus.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.entity.SysMenu;
import com.campus.admin.api.vo.MenuVO;
import com.campus.common.core.util.R;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
public interface SysMenuService extends IService<SysMenu> {
	/**
	 * 通过角色编号查询URL 权限
	 *
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<MenuVO> findMenuByRoleId(String roleId);

	/**
	 * 级联删除菜单
	 *
	 * @param id 菜单ID
	 * @return 成功、失败
	 */
	R removeMenuById(String id);

	/**
	 * 更新菜单信息
	 *
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenu sysMenu);
}
