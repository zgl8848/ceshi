package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 省份表
 *
 * @author campus
 * @date 2019-01-17 09:42:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_province")
public class SysProvince extends Model<SysProvince> {
	private static final long serialVersionUID = 1L;

	/**
	 * 省ID
	 */
	@TableId
	private Integer id;
	/**
	 * 省份code
	 */
	private String code;
	/**
	 * 省份名称
	 */
	private String name;

}
