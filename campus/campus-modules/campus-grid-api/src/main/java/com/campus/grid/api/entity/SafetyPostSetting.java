package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
public class SafetyPostSetting extends Model<SafetyPostSetting> {
	private static final long serialVersionUID = 1L;
	@TableId
	private String id;    //主键
	private String value; //字典数值 
	private String label; //岗位标签
}
