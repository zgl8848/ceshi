package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.api.entity.TaskResult;
import com.campus.grid.mapper.InspectTaskMapper;
import com.campus.grid.mapper.ProjectStandardMapper;
import com.campus.grid.service.ProjectStandardService;
import com.campus.grid.service.TaskResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 
 *
 * @author wang_h
 * @date 2019-06-20 15:30:14
 */
@Service("projectStandardService")
@Transactional
public class ProjectStandardServiceImpl extends ServiceImpl<ProjectStandardMapper, ProjectStandard> implements ProjectStandardService {
	@Autowired
	private ProjectStandardMapper projectStandardMapper;
	@Autowired
	private InspectTaskMapper inspectTaskMapper;
	
	@Autowired
	private TaskResultService taskResultService;
	
	@Override
	public boolean save(String projectId, String content) {
		String taskId = UUID.randomUUID().toString().replaceAll("-", "");
		ProjectStandard projectStandard=new ProjectStandard(taskId,projectId,content);
		try {
			List<TaskResult> taskResults = projectStandardMapper.selectTaskResult(projectId);
			if (taskResults.size()!=0){
				List<TaskResult> taskResultList=new ArrayList<>();
				for (TaskResult taskResult : taskResults) {
					TaskResult taskResult1=new TaskResult(taskResult.getTaskId(),taskResult.getSchoolId(),projectId,taskId,"0","0","0");
					taskResultList.add(taskResult1);
				}
				inspectTaskMapper.insertTaskResult(taskResultList);
				projectStandardMapper.addNumbers(projectId);
			}
			this.save(projectStandard);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean removeById(String standardId,String projectId) {
		try {
			boolean flag = taskResultService.getByProject(projectId);
			if (flag) {// 存在已开启的任务
				return false;
			} else {
				this.removeById(standardId);
				projectStandardMapper.deleteaskResultOfStandardId(standardId);
				projectStandardMapper.depletionInNumbers(projectId);
				return true;
			}
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
