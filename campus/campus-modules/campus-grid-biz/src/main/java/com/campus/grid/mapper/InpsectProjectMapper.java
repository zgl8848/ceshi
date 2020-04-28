package com.campus.grid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.InpsectProject;
import com.campus.grid.api.entity.ProjectStandard;
/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:46:45
 */
public interface InpsectProjectMapper extends BaseMapper<InpsectProject> {

	/**
	 * 添加检查项目
	 * @param inpsectProject
	 */
	void create(InpsectProject inpsectProject);

	/**
	 * 批量添加项目标准
	 * @param projectStandards
	 */
	void insertProjectStandard(List<ProjectStandard> projectStandards);

	/**
	 * 检查项目修改
	 * @param inpsectProject
	 */
	void updateInpsectProject(InpsectProject inpsectProject);

	/**
	 * 根据项目id删除对应的项目标准
	 * @param projectId
	 */
	void deleteProjectStandardOfProjectId(String projectId);

	/***
	 * 分页查询
	 * @param startSize
	 * @param endSize
	 * @return
	 */
	IPage<List<InpsectProject>> selectInpsectProjects(Page page,@Param("childDepts") List<String> childDepts);

	/**
	 * 根据id查询单个项目信息
	 * @param projectId
	 * @return
	 */
	InpsectProject findInpsectProjectOfProjectId(String projectId);

	/**
	 * 通过id修改检查项目名称
	 * @param projectId
	 * @param projectName
	 * @return
	 */
	boolean updateNameById(@Param("projectId")String projectId, @Param("projectName")String projectName);

	/**
	 * 获取检查项目信息
	 * @param childDepts
	 * @return
	 */
	List<InpsectProject> getProjects(@Param("childDepts") List<String> childDepts);
	
	/**
	 *     获取检查项目信息(导出)
	 * @param childDepts
	 * @return
	 */
	List<InpsectProject> getInsepectProjects(@Param("childDepts") List<String> childDepts);
	
	/**
	 * 根据id查询检查标准
	 * @param projectId
	 * @return
	 */
	List<ProjectStandard> getProjectStandardOfProjectId(String projectId);
}
