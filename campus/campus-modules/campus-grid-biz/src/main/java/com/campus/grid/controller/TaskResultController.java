package com.campus.grid.controller;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.campus.common.core.util.R;
import com.campus.grid.api.dto.TaskResultDTO;
import com.campus.grid.service.TaskAuditService;
import com.campus.grid.service.TaskResultService;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * @Author hhskj on 2019/10/14 18:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskResultController {

    private final TaskResultService taskResultService;
    
    private final TaskAuditService taskAuditService;
 

    /**
     * 获取任务检查结果
     * type:1全选。2:通过，3:不通过
     */
    @RequestMapping("/result/{taskId}/{schoolId}/{type}")
    public R findResult(@PathVariable String taskId, @PathVariable String schoolId,@PathVariable String type) {
        return new R<>(taskResultService.getResultBySchoolIdAndTaskId(taskId, schoolId,type));
    }

    /**
     * 更新检查结果状态
     */
    @RequestMapping("/result/update")
    public R updateStatus(@PathVariable TaskResultDTO taskResult) {
        try {
            taskResultService.updateStatus(taskResult);
            return new R<>("0");
        }catch (Exception e){
            return new R<>("1");
        }
    }
    
    /**
             * 批量保存检查结果状态以及审批结果
     */
    @RequestMapping("/result/save")
    public R updateStatus(@RequestBody List<TaskResultDTO> taskResults) {
        try {
        	boolean falg=taskResultService.savetaskResults(taskResults);
        	if(falg) {
        		 return new R<>("0");
        	}else {
        		 return new R<>("1");
        	}
           
        }catch (Exception e){
            return new R<>("1");
        }
    }
    
    /**
     * 获取任务检查结果
     */
    @RequestMapping("/audit/{taskId}/{schoolId}")
    public R findAuditResult(@PathVariable String taskId, @PathVariable String schoolId) {
        return new R<>(taskAuditService.getAuditBySchoolIdAndTaskId(taskId, schoolId));
    }
}
