package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:46:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inspect_project")
public class InpsectProject extends Model<InpsectProject> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private String projectId;
    /**
   * 项目名称
   */
    private String projectName;
    /**
   * 项目创建人
   */
    private String userId;
    /**
   * 标准数量
   */
    private Integer standardCount;
	/**
	 * 所属部门id
	 */
	private String deptId ;
	/**
	 * 检查内容
	 */
	private List<ProjectStandard> projectStandards ;
	/**
	 * 检查内容
	 */
	private String strInspect ;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
	/**
	 * 用户姓名
	 */
	private String trueName;
  
}
