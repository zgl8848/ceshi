package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位-用户关系表
 *
 * @author CR7
 * @date 2019-01-09 10:05:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_post_user")
public class GridPostUser extends Model<GridPostUser> {
	private static final long serialVersionUID = 1L;

	/**
	 * 岗位ID
	 */
	@TableId
	private String postId;
	/**
	 * 用户ID
	 */
	private String userId;

}
