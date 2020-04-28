package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysDeptRelation;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2018-02-12
 */
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
	/**
	 * 删除部门关系表数据
	 *
	 * @param id 部门ID
	 */
	void deleteDeptRelationsById(String id);

	/**
	 * 更改部分关系表数据
	 *
	 * @param deptRelation
	 */
	void updateDeptRelations(SysDeptRelation deptRelation);

}
