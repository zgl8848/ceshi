package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.TaskAudit;
import com.campus.grid.mapper.TaskAuditMapper;
import com.campus.grid.service.TaskAuditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author hhskj on 2019/9/25 16:40
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class TaskAuditServiceImpl extends ServiceImpl<TaskAuditMapper, TaskAudit> implements TaskAuditService {

    /**
     * 查询任务结果信息
     * @param taskId 任务id
     * @param schoolId 学校Id
     * @return
     */
    public  List<TaskAudit> getAuditBySchoolIdAndTaskId(@Param("taskId") String taskId, @Param("schoolId") String schoolId){
        return baseMapper.getAuditBySchoolIdAndTaskId(taskId, schoolId);
    }

    /**
     * 保存审核记录
     * @param taskAudit
     */
    public void insertAudit(TaskAudit taskAudit){
        baseMapper.insertTaskAudit(taskAudit);
    }

}
