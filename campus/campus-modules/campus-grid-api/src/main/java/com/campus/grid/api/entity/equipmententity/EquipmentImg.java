package com.campus.grid.api.entity.equipmententity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("equipment_pic")
public class EquipmentImg extends Model<EquipmentImg> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value = "pic_id", type = IdType.ID_WORKER_STR)
	private String picId;
	/**
	 * 图片地址
	 */
	private String picUrl;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 学校唯一编号id
	 */
	private String schoolId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
