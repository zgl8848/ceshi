package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.TaskAudit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author hhskj on 2019/9/25 15:52
 */
public interface TaskAuditMapper extends BaseMapper<TaskAudit> {

    /**
     * 查询任务结果信息
     * @param taskId 任务id
     * @param schoolId 学校Id
     * @return
     */
    List<TaskAudit> getAuditBySchoolIdAndTaskId(@Param("taskId") String taskId, @Param("schoolId") String schoolId);

    void insertTaskAudit(TaskAudit taskAudit);
}
