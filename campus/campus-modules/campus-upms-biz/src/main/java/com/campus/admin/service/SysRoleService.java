package com.campus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.entity.SysRole;
import com.campus.common.core.util.R;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(String userId);

	/**
	 * 通过角色ID，删除角色
	 *
	 * @param id
	 * @return
	 */
	R<Boolean> removeRoleById(String id);

	/**
	 * 获取该用户当前拥有的所有角色
	 *
	 * @return
	 */
	List<SysRole> listRoles();
	
	/**
	 * 校验role_code不重复
	 * @param code
	 * @return
	 */
	boolean checkRoleCode(String code);
}
