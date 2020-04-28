package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:54:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inspect_group")
public class InspectGroup extends Model<InspectGroup> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private String groupId;
    /**
   * 小组名称
   */
    private String groupName;
    /**
   * 所属教育局
   */
    private String deptId;
    /**
   * 创建人
   */
    private String userId;
    /**
   * 描述
   */
    private String remark;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
	/**
	 * 组长id
	 */
	private String leader;
	/**
	 * 成员数量
	 */
	private Integer number;
	
	/**
	 * 有效标志（1,有效，2无效）
	 */
	private String status;

	public InspectGroup() {
	}

	public InspectGroup(String groupId, String groupName, String deptId, String userId, String remark, 
			LocalDateTime createTime, String leader, Integer number,String status) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.deptId = deptId;
		this.userId = userId;
		this.remark = remark;
		this.createTime = createTime;
		this.leader = leader;
		this.number = number;
		this.status=status;
	}
}
