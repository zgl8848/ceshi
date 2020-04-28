package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author campus
 * @since 2017-10-29
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.ID_WORKER_STR)
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
	@JsonIgnore
	private String salt;
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
	@TableLogic
	private String delFlag;
	/**
	 * 锁定标记
	 */
	private String lockFlag;
	/**
	 * 手机号码
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
	 * 微信openid
	 */
	private String wxOpenid;
	/**
	 * QQ openid
	 */
	private String qqOpenid;

	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 学校ID
	 */
	private String schoolId;
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
	/**
	 * 用户类型 0普通用户 1管理员'
	 */
	private String genre;

}
