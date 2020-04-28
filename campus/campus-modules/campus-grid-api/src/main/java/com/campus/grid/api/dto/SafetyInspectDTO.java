package com.campus.grid.api.dto;

import com.campus.grid.api.entity.SafetyInspect;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SafetyInspectDTO implements Serializable {
	//id
	private String inspectId;
	//网格名称
	private String resreauName;
	//巡查人
	private String userName;
	//创建时间
	private String createName;
	//问题类型
	private String functionType;
	//严重级别
	private String level;
	//隐患描述
	private String remark;
	//记录状态
	private String status;
	//学校名称
	private String schoolName;
	//所属部门
	private String deptName;
	//整改人
	private String rectification;
	//未处理过额图片
	private List<String> processedPic;
	//已处理过的图片
	private List<String> untreatedPic;

	private GridFunctionDTO gridFunctionDTO;

	//完成时间
	private String updateTime;
	//完成描述
	private String finishDesc;
	private String type;
	//处理方式
	private String mode;

	public SafetyInspectDTO() {
	}

	public SafetyInspectDTO(String inspectId, String resreauName, String userName, String createName, String functionType, String level, String remark, String status, String schoolName, String deptName, String rectification, List<String> processedPic, List<String> untreatedPic, GridFunctionDTO gridFunctionDTO, String updateTime, String finishDesc, String type,String mode) {
		this.inspectId = inspectId;
		this.resreauName = resreauName;
		this.userName = userName;
		this.createName = createName;
		this.functionType = functionType;
		this.level = level;
		this.remark = remark;
		this.status = status;
		this.schoolName = schoolName;
		this.deptName = deptName;
		this.rectification = rectification;
		this.processedPic = processedPic;
		this.untreatedPic = untreatedPic;
		this.gridFunctionDTO = gridFunctionDTO;
		this.updateTime = updateTime;
		this.finishDesc = finishDesc;
		this.type = type;
		this.mode=mode;
	}

	public SafetyInspectDTO(SafetyInspect safetyInspect) {
		this.inspectId = safetyInspect.getInspectId();
		this.resreauName = safetyInspect.getReseauName();
		this.userName = safetyInspect.getUserName();
		this.createName = null;
		this.functionType = safetyInspect.getFunctionName();
		this.level = safetyInspect.getLevel() + "";
		this.remark = safetyInspect.getRemark();
		this.status = safetyInspect.getStatus() + "";
		this.schoolName = safetyInspect.getSchoolName();
		this.rectification = safetyInspect.getRectification();
		this.deptName = null;
		this.processedPic = null;
		this.untreatedPic = null;
		this.gridFunctionDTO = null;
		this.updateTime = null;
		this.finishDesc = safetyInspect.getFinishDesc();
		this.type=safetyInspect.getType()+"";
	}

}
