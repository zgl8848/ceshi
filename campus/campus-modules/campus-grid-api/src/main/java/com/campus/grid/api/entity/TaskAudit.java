package com.campus.grid.api.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @Author hhskj on 2019/9/25 11:56
 */
@Data
public class TaskAudit {

    /**
     *任务主键
     */
    private String taskId;
    /**
     * 	学校主键
     */
    private String schoolId;
    /**
     *任务主键
     */
    private String userId;
    /**
     * 	学校主键
     */
    private String remark;
    /**
     *任务主键
     */
    private LocalDateTime createTime;

    public TaskAudit() {
    }

    public TaskAudit(String taskId, String schoolId, String userId, String remark, LocalDateTime createTime) {
        this.taskId = taskId;
        this.schoolId = schoolId;
        this.userId = userId;
        this.remark = remark;
        this.createTime = createTime;
    }

}
