package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 安全岗位信息表
 *
 * @author CR7
 * @date 2019-01-09 10:04:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_safety_post")
public class GridSafetyPost extends Model<GridSafetyPost> {
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
	 * 岗位类型  0-校长  1-党（总支、支部）书记   2-分管安全工作的副校长 3-分管教学副校长
	 * 4-分管后勤副校长  5-法制副校长 6-工会主席 7-安全主任  8-办公室主任 9-德育主任
	 * 10-教导主任  11-教科室主任  12-总务主任  13-少先队辅导员、团委（支部）书记
	 * 14-年级组长 15-教研组长 16-班主任  17-任课教师 18-体育教师 19-电教教师 20-心理教师或心理健康学科任课教师
	 * 21-财务人员 22-财产管理员 23-图书管理员 24-档案管理员  25-体育器材保管员 26-实验室管理员
	 * 27-食堂管理员 28-宿舍管理员
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
}
