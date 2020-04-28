package com.campus.grid.api.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.campus.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PostVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId
	private String postId;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 岗位类型  1-校长 2-副校长 3-工会主席 4-保卫科主任
	 */
	private String postType;
	/**
	 * 岗位职责
	 */
	private String remark;
	/**
	 * 学校ID
	 */
	private String schoolId;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 是该职位的用户集合
	 */
	private List<SysUser> userList;

}
