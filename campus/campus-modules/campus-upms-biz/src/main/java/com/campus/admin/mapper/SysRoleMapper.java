package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> listRolesByUserId(String userId);

	/**
	 * 根据当前登录用户来获取角色类型，从而来得到当前类型的所有角色
	 * 如何登录用户得角色类型是 0 那么他可以查看所有角色列表，否则只能查看当前用户角色类型得角色列表
	 *
	 * @param roleType
	 * @return
	 */
	List<SysRole> listRolesByRoleType(@Param("roleType") String roleType);
	
	List<SysRole> checkRoleCode(@Param("roleCode") String roleCode);
	
}
