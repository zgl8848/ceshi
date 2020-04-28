package com.campus.grid.api.dto;

import com.campus.grid.api.entity.HiddenDanger;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HiddenDangerDTO implements Serializable {
	private String id;
	private String reseauName;
	private String hiddenType;//隐患类型
	private String hiddenLevel;//隐患等级
	private String hiddenTitle;//隐患描述
	private String reportTime;//上报时间
	private String safetyOfficer;//安全员
	private String rectification;//整改人
	private String principal;//负责人
	private String mode;//处理方式
	private String status;//状态
	private String schoolName;//所属学校
	private String deptName;//所属不笨
	//未处理过额图片
	private List<String> processedPic;
	//已处理过额图片
	private List<String> untreatedPic;
	private GridFunctionDTO gridFunctionDTO;
	//完成时间
	private String updateTime;
	//完成描述
	private String finishDesc;
	//学校ID
	private String schoolId;
	//学校类型
	private String schoolType;
	//隐患数量
	private Integer count;
	//隐患等级
	private Integer level;

	public HiddenDangerDTO() {
	}

	public HiddenDangerDTO(String id, String reseauName, String hiddenType, String hiddenLevel, String hiddenTitle, String reportTime, String safetyOfficer, String rectification, String principal, String mode, String status, String schoolName, String deptName, List<String> processedPic, List<String> untreatedPic, GridFunctionDTO gridFunctionDTO, String updateTime, String finishDesc) {
		this.id = id;
		this.reseauName = reseauName;
		this.hiddenType = hiddenType;
		this.hiddenLevel = hiddenLevel;
		this.hiddenTitle = hiddenTitle;
		this.reportTime = reportTime;
		this.safetyOfficer = safetyOfficer;
		this.rectification = rectification;
		this.principal = principal;
		this.mode = mode;
		this.status = status;
		this.schoolName = schoolName;
		this.deptName = deptName;
		this.processedPic = processedPic;
		this.untreatedPic = untreatedPic;
		this.gridFunctionDTO = gridFunctionDTO;
		this.updateTime = updateTime;
		this.finishDesc = finishDesc;
	}

	public HiddenDangerDTO(HiddenDanger hiddenDanger) {
		this.id = hiddenDanger.getInspectId();
		this.reseauName = hiddenDanger.getReseauName();
		this.hiddenType = hiddenDanger.getFunctionId();
		this.hiddenLevel = hiddenDanger.getLevel() + "";
		this.hiddenTitle = hiddenDanger.getRemark();
		this.reportTime = null;
		this.safetyOfficer = hiddenDanger.getUserId();
		this.rectification = hiddenDanger.getRectification();
		this.principal = null;
		this.mode = hiddenDanger.getModeName();
		this.status = hiddenDanger.getStatus() + "";
		this.schoolName = hiddenDanger.getSchoolName();
		this.deptName = null;
		this.processedPic = null;
		this.untreatedPic = null;
		this.gridFunctionDTO = null;
		this.updateTime = null;
		this.finishDesc = hiddenDanger.getFinishDesc();
	}
}
