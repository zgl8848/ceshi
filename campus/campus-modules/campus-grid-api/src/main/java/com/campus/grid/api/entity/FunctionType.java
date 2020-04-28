package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author hu
 * @date 2019-01-02 16:19:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_function_type")
public class FunctionType extends Model<FunctionType> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String functionId;
	/**
	 * 父类ID
	 */
	private String parentId;
	/**
	 * 功能名称
	 * 功能名称
	 * 功能名称
	 * 功能名称
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 类别 1-网格 2-巡查 3隐患
	 */
	private Integer type;
	/**
	 * 值 只有类别为2-巡查时 的子类才有值
	 */
	private String functionValues;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 时间
	 */
	private LocalDateTime createTime;

}
