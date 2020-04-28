package com.campus.grid.api.entity.equipmententity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author eatheryu
 * 设备消息实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentMsgSynchronize extends Model<EquipmentMsgSynchronize> {
	private static final long serialVersionUID = 1L;

	private String equipmentCode;    //	设备编号

	private String dataType;    //	数据类型 1-设备信息 2-告警信息

	private String platformType;    //	平台类型 1-视频汇聚

	private String equipmentId;    //	设备ID

	private String groupId;    //	分组ID

	private String groupName;    //	分组名称

	private String equipmentName;    //	设备名称

	private String operateType;    //	操作类型 add-增加 update-修改 del-删除

	private String equipmentIp;    //	设备IP

	private String equipmentPort;    //设备端口

	private String position;    //设备位置

	private String schoolCode;    //	学校编号（固定编号）

	private String username;    //用户名

	private String password;    //密码

	private String imgUrl;    //设备图片

	private String status;    //	状态 0-停用 1-正常

	private String remark;    //备注

}
