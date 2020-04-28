package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_vehicle_information")
public class VehicleInformation {
	@TableId(value = "v_id", type = IdType.ID_WORKER_STR)
	private String  vId;
	private String  tripId;
	private String  uuid;
	private String  deviceId;
	private String  merchantId;
	private Integer  online; //车俩是否在线，1在线，0离线
	private Integer gpsState; //GPS是否可用，1正常，0未知错误
	private String  userId;
	private String  driverName;//司机名称
	private String  licenseNumber;//车牌
	private Integer driverPhoneNumber;//司机联系方式

}
