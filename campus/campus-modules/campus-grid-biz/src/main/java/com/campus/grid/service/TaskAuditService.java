package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.TaskAudit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author hhskj on 2019/9/25 16:13
 */
public interface TaskAuditService extends IService<TaskAudit> {

    /**
     * 查询任务结果信息
     * @param taskId 任务id
     * @param schoolId 学校Id
     * @return
     */
    List<TaskAudit> getAuditBySchoolIdAndTaskId(@Param("taskId") String taskId, @Param("schoolId") String schoolId);

    void insertAudit(TaskAudit taskAudit);
}
