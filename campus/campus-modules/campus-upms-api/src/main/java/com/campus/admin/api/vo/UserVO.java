package com.campus.admin.api.vo;

import com.campus.admin.api.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author campus
 * @date 2017/10/29
 */
@Data
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 随机盐
	 */
	private String salt;

	/**
	 * 微信openid
	 */
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	private String qqOpenid;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

	/**
	 * 锁定标记
	 */
	private String lockFlag;
	/**
	 * 简介
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 部门ID
	 */
	private String deptId;

	/**
	 * 租户ID
	 */
	private Integer tenantId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 角色列表
	 */
	private List<SysRole> roleList;

	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 学校ID
	 */
	private String schoolId;
	/**
	 * 学校名字
	 */
	private String schoolName;
	/**
	 * 出生日期
	 */
	private LocalDate birthday;
	/**
	 * 0-男，1-女
	 */
	private String sex;
	/**
	 * 性质 1-在编 2-外聘
	 */
	private String nature;
	/**
	 * 加入方式 1-后台加入 2-APP申请
	 */
	private String addMode;
	/**
	 * 备注
	 */
	private String remark;
}
