package com.campus.grid.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.dto.InspectGroupInfo;
import com.campus.grid.api.dto.SchoolDTO;
import com.campus.grid.api.dto.TaskSchoolRDTO;
import com.campus.grid.api.entity.InspectGroup;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.api.entity.TaskGroupR;
import com.campus.grid.api.entity.TaskProjectR;
import com.campus.grid.api.entity.TaskResult;

public interface InspectTaskMapper extends BaseMapper<InspectTask> {
	/**
	 * 插入检查任务
	 * @param inspectTask
	 */
	void insertInspectTask(InspectTask inspectTask);

	/**
	 * 批量插入re任务与小组关系
	 * @param taskGroupR
	 */
	void insertTaskGroupR(List<TaskGroupR> taskGroupR);

	/**
	 * 批量插入任务与项目关系表
	 * @param taskProjectRS
	 */
	void insertTaskProjecR(List<TaskProjectR> taskProjectRS);

	/**
	 * 批量插入检查任务与学校关系表
	 * @param taskSchoolR
	 */
	void insertTaskSchoolR(List<TaskGroupR> taskSchoolR);

	/**
	 * 批量插入检查任务结果
	 * @param taskResults
	 */
	void insertTaskResult(List<TaskResult> taskResults);

	/**
	 * 根据项目id查询项目标准
	 * @param projectList
	 * @return
	 */
	List<ProjectStandard> selectProjectStandards(List<String> projectList);

	/**
	 * 根据小组id查询小组的所属部门
	 * @param groupId
	 * @return
	 */
	String findDeptId(String groupId);

	/**
	 * 根据小组id查询所有学校
	 * @param groupId
	 * @return
	 */
	List<String> selectSchoolOfGroupId(List<String> groupId);

	/**
	 * 查询任务和学校关系表格
	 * @param taskId 任务id
	 * @param childDepts 部门Id
	 * @param page 分页
	 * @return
	 */
	IPage<List<TaskSchoolRDTO>> selectTaskSchoolR(Page page, @Param("taskId") String taskId, @Param("schoolIds") List<String> schoolIds,
			@Param("childDepts") List<String> childDepts,@Param("roleType") String roleType );

	/**
	 * 查询总条数
	 * @param taskId
	 * @param childDepts
	 * @return
	 */
	Integer findTotalOfTaskSchoolR(@Param("taskId") String taskId,@Param("childDepts") List<String> childDepts);

	/**
	 * 分页查询检查任务信息
	 *
	 * @param page      分页
	 * @param schoolIds   学校ID
	 * @param childDepts   部门ID集合
	 * @return list
	 */
	IPage<List<InspectTask>> getInspectTaskPage(Page page, @Param("roleType") String roleType, 
			@Param("schoolIds") List<String> schoolIds, @Param("childDepts") List<String> childDepts,
			@Param("taskName") String taskName, @Param("reportTime") Date reportTime);

	/**
	 * 统计任务检查的学校（学校类型）List<String>
	 *
	 * @param roleType   角色类型
	 * @param schoolIds   学校ID集合
	 * @param childDepts   部门ID集合
	 * @param taskId       任务ID
	 * @return list
	 */
	List<SchoolDTO> statSchoolByType(@Param("roleType") String roleType, @Param("schoolIds") List<String> schoolIds, @Param("childDepts") List<String> childDepts, @Param("taskId") String taskId);

	/**
	 * 通过ID查询任务信息
	 *
	 * @param id 用户ID
	 * @return inspectTask
	 */
	InspectTask getTaskById(String id);

	/**
	 * 根据检查项目ID查询任务信息
	 * @param projectId
	 * @return
	 */
	String getByProject(@Param("projectId") String projectId);
	
	/**
	 * 根据任务id查询检查项目
	 * @param projectId
	 * @return
	 */
	List<InspectGroupInfo> getProjectByTaskId(@Param("taskId") String taskId);
	
	/**
	 * 根据任务id查询督察小组
	 * @param projectId
	 * @return
	 */
	List<InspectGroup> getGroupByTaskId(@Param("taskId") String taskId);
	
	/**
	 * 根据 任务id,小组id 查询督察小组
	 * @param projectId
	 * @return
	 */
	List<String> getGroupRByGroupId(@Param("taskId") String taskId,@Param("groupId") String groupId);
	
	/**
	 * 根据任务id删除 检查项目关联表
	 * @param taskId
	 * @return
	 */
	boolean deleteProjectRBytaskId(String taskId);
	
	/**
	 * 根据任务id删除 督察小组关联表
	 * @param taskId
	 * @return
	 */
	boolean deleteGroupRBytaskId(String taskId);
	
	/**
	 * 根据任务id删除 学校关联表
	 * @param taskId
	 * @return
	 */
	boolean deleteSchoolRBytaskId(String taskId);
	
	/**
	 * 根据任务id删除 任务结果关联表
	 * @param taskId
	 * @return
	 */
	boolean deleteTaskResultBytaskId(String taskId);
	
	/**
	 * 通过taskId更新任务表
	 * @param inspectTask
	 * @return
	 */
	boolean updateInspectTask(InspectTask inspectTask);
	
	
}
