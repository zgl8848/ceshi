package com.campus.grid.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.dto.UserDTO;
import com.campus.grid.api.entity.InspectTask;

public interface InspectTaskService extends IService<InspectTask> {
	/**
	 * 检查任务创建
	 * @param taskName
	 * @param projects
	 * @param remark
	 * @param groups
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	boolean	save(String taskId,String taskName, String projects, String remark, String groups, Date startTime,Date endTime);

	/**
	 * 任务详情
	 * @param taskId
	 * @param deptId
	 * @param page
	 * @return
	 */
	IPage taskParticulars(Page page, String taskId, String deptId);

	/**
	 * 分页查询检查任务信息
	 *
	 * @param page      分页
	 * @param taskName      任务名称
	 * @param reportTime    检查时间
	 * @return  
	 */
	IPage getInspectTaskPage(Page page,String taskName,String reportTime);

	/**
	 * 统计任务检查的学校（学校类型）
	 *
	 * @param taskId       任务ID
	 * @return list
	 */
	Map<String, Integer> statSchoolByType(String taskId);

	/**
	 * 通过ID查询任务信息
	 *
	 * @param id 任务ID
	 * @return 任务信息
	 */
	InspectTask selectTaskById(String id);

	List<String> getSchoolIds(UserDTO userDTO);

	Map<String, List<String>> getUserRole(String roleType);

	/**
	 * 判断该检查项目是否已经在任务中
	 * @param projectId
	 * @return
	 */
	boolean getByProject(String projectId);
	
	/**
	 * 编辑获取任务信息
	 * @param taskId
	 * @return
	 */
	Map<String,Object> selectTaskDetailById(String taskId);
	
}
