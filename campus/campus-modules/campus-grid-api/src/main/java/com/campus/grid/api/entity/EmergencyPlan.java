package com.campus.grid.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author lc
 * @Date  2020年3月2日
 * @Desc  应急预案实体
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("emergency_plan")
public class EmergencyPlan extends Model<EmergencyPlan> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5807009965035948860L;

	private String emergencyPlanId;
	
	private String emergencyType;
	
	private String receivingSchoolId;
	
	private String receivingDepartmentId;
	
	private String emergencyContent;
	
	private String chiefCommander;
	
	private String viceCommander;
	
	private String phone;
	
	private String emergencyStatus;
	
	private String lastModifiedTime;
	
	private String createTime;
	
	private String userId;
	
	//前端参数需要
	private String schoolId;
	private String typeCode;
}
