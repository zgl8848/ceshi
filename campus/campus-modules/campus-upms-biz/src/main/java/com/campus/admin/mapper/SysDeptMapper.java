package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

	/**
	 * 关联dept——relation
	 *
	 * @return 数据列表
	 */
	List<SysDept> listDepts();

	/**
	 * 根据部门id获取部门名称
	 * @param deptId	部门id
	 * @return	部门名称
	 */
    String selectDeptNameById(String deptId);
}
