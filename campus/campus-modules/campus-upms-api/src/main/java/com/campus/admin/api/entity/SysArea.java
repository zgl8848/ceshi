package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地区表
 *
 * @author campus
 * @date 2019-01-17 09:41:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_area")
public class SysArea extends Model<SysArea> {
	private static final long serialVersionUID = 1L;

	/**
	 * 地区ID
	 */
	@TableId
	private Integer id;
	/**
	 * 地区code
	 */
	private String code;
	/**
	 * 地区名称
	 */
	private String name;
	/**
	 * 城市code
	 */
	private String citycode;

}
