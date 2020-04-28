package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:50:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("group_member")
public class GroupMember extends Model<GroupMember> {
private static final long serialVersionUID = 1L;

    /**
   * 小组id
   */
    @TableId
    private String groupId;
    /**
	 * 成员id
	 */
    private String userId;
    /**
   * 是否是组长 0不是组长1是组上
   */
    private String isLeader;

	public GroupMember() {
	}

	public GroupMember(String groupId, String userId, String isLeader) {
		this.groupId = groupId;
		this.userId = userId;
		this.isLeader = isLeader;
	}
}
