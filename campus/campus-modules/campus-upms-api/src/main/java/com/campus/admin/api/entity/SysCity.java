package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 城市表
 *
 * @author campus
 * @date 2019-01-17 09:41:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_city")
public class SysCity extends Model<SysCity> {
	private static final long serialVersionUID = 1L;

	/**
	 * 城市ID
	 */
	@TableId
	private Integer id;
	/**
	 * 城市code
	 */
	private String code;
	/**
	 * 城市名称
	 */
	private String name;
	/**
	 * 省份code
	 */
	private String provincecode;

}
