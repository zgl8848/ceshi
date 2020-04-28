package com.campus.grid.api.dto;

import com.campus.grid.api.entity.TaskResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author lium
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskResultDTO extends TaskResult{
	
	/**
	 * 用户角色类型
	 */
	private String role;
	
	/*
	 * 检查标准编号
	 */
	private int num;

}
