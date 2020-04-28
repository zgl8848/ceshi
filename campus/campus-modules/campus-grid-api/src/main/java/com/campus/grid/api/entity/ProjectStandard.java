package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author wang_h
 * @date 2019-06-20 15:30:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("project_standard")
public class ProjectStandard extends Model<ProjectStandard> {
private static final long serialVersionUID = 1L;

    /**
   * id
   */
    @TableId
    private String standardId;
    /**
   * 项目id
   */
    private String projectId;
    /**
   * 检查标准内容
   */
    private String content;

    private String key;

    private String value;

	public ProjectStandard() {
	}

	public ProjectStandard(String standardId, String projectId, String content) {
		this.standardId = standardId;
		this.projectId = projectId;
		this.content = content;
	}
}
