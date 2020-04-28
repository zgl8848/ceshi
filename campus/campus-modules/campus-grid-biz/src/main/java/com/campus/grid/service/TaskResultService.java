package com.campus.grid.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.dto.TaskResultDTO;
import com.campus.grid.api.entity.TaskResult;

/**
 * @Author hhskj on 2019/9/25 11:09
 */
public interface TaskResultService extends IService<TaskResult>{

    /**
     * 查询任务结果信息
     * @param taskId 任务id
     * @param schoolId 学校Id
     * @return
     */
    Map<String, Object> getResultBySchoolIdAndTaskId(@Param("taskId") String taskId, 
    		@Param("schoolId") String schoolId,@Param("type") String type);

    /**
     	* 修改检查任务结果状态
     * @param taskResult
     */
    void updateStatus(TaskResultDTO taskResult);
    
    /**
     * 	保存检查任务结果状态
     * @param taskResults
     *
     */
    boolean savetaskResults(List<TaskResultDTO> taskResults);

    /**
     * 判断该检查项目是否已经在任务中
     * @param projectId
     * @return
     */
    boolean getByProject(String projectId);
}
