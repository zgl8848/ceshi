package com.campus.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	/**
	 * 根据用户Id删除该用户的角色关系
	 *
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @date 2017年12月7日 16:31:38
	 */
	Boolean deleteByUserId(@Param("userId") String userId);

	/**
	 * 查询该学校是否已经有督查人员
	 * @param schoolId 学校id
	 * @return 返回人员个数
	 */
	Integer selectInspectionOfSchool(String schoolId);
}
