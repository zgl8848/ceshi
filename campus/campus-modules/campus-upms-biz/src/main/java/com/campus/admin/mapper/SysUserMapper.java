package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.dto.InspectGroupInfo;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.admin.api.vo.UserVO;
import com.campus.common.data.datascope.DataScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 *
	 * @param username 用户名
	 * @return userVo
	 */
	UserVO getUserVoByUsername(String username);

	/**
	 * 分页查询用户信息（含角色）
	 *
	 * @param page      分页
	 * @param userDTO   查询参数
	 * @param dataScope
	 * @return list
	 */
	IPage<List<UserVO>> getUserVosPage(Page page, @Param("query") UserDTO userDTO, DataScope dataScope);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return userVo
	 */
	UserVO getUserVoById(String id);

	/**
	 * 通过部门ID查询用户
	 *
	 * @param deptIds 部门ID集合
	 * @return SysUser
	 */
	List<SysUser> listUsersByDeptIds(@Param("deptIds") List<String> deptIds);

	/**
	 * 通过角色ID查询用户
	 *
	 * @param roleId 角色ID
	 * @return SysUser
	 */
	List<SysUser> listUsersByRoleIds(@Param("roleId") String roleId);

	/**
	 * 根据学校编号修改所有用户的状态 锁定 正常
	 *
	 * @param schoolId
	 * @param lockFlag
	 * @return
	 */
	int updateUserLockFlagStatus(@Param("schoolId") String schoolId, @Param("lockFlag") String lockFlag);

	/**
	 * 根据学校编号查询用户列表
	 *
	 * @param schoolId
	 * @return
	 */
	List<UserVO> listUsersBySchoolId(@Param("schoolId") String schoolId);

	/**
	 * 根据deptId督查小组
	 * @param deptId
	 * @return
	 */
	List<InspectGroupInfo> selectInspectGroupInfos(@Param("deptId") String deptId);

	/**
	 * 查询当前部门下的所有的检查项目
	 * @param childDepts
	 * @return
	 */
	List<InspectGroupInfo> selectInspectProject(List<String> childDepts);


	/**
	 * 根据巡查人id和整改人id查询真实姓名
	 * @param userId	巡查人id
	 * @param rectification	整改人id
	 * @return
	 */
	TrueNameVO selectTrueName(@Param("userId") String userId, @Param("rectification") String rectification);
}
