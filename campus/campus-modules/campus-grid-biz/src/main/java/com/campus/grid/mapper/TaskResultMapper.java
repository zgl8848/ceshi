package com.campus.grid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysUser;
import com.campus.grid.api.dto.TaskResultDTO;
import com.campus.grid.api.entity.TaskResult;

/**
 * @Author hhskj on 2019/9/25 11:06
 */
public interface TaskResultMapper extends BaseMapper<TaskResult> {

    /**
     * 查询任务结果信息
     * @param taskId 任务id
     * @param schoolId 学校Id
     * @return
     */
    List<TaskResultDTO> getResultBySchoolIdAndTaskId(@Param("taskId") String taskId, 
    		@Param("schoolId") String schoolId,@Param("type") String type,
    		@Param("role") String role);

    void updateStatusByresult(TaskResultDTO result);
    
    List<SysUser> getUserByUsername(@Param("userName") String userName);

    /**
     * 根据检查项目ID查询任务信息
     * @param projectId
     * @return
     */
    List<String> getByProject(@Param("projectId") String projectId);
    
    /**
     *  更新学校审批状态
     * @param taskId
     * @param schoolId
     * @param status
     */
    void updateSchoolStatus(@Param("taskId") String taskId,
    		@Param("schoolId") String schoolId,@Param("status") String status);
    
    /**
     * 获取学校审批状态
     * @param taskId
     * @param schoolId
     */
    List<String> getSchoolStatus(@Param("taskId") String taskId,
    		@Param("schoolId") String schoolId);

}
