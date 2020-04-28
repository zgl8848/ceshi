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


/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("equipment_info")
public class Equipment extends Model<Equipment> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 设备ID
	 */
	private String equipmentId;
	/**
	 * 分组id
	 */
	private String groupId;
	/**
	 * 分组名称
	 */
	private String groupName;

	/**
	 * 设备名称
	 */
	private String equipmentName;
	/**
	 * 设备编号
	 */
	private String equipmentCode;
	/**
	 * 学校唯一编号id
	 */
	private String schoolId;
	/**
	 * 设备ip
	 */
	private String equipmentIp;
	/**
	 * 设备端口号
	 */
	private String equipmentPort;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 设备状态 0-停用 1-正常
	 */
	private String status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 设备位置
	 */
	private String position;
	/**
	 * 图片
	 */
	private String imgUrl;

}
