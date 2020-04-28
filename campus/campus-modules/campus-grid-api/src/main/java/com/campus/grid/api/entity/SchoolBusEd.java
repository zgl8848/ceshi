package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("grid_school_bus_ed")
public class SchoolBusEd {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "school_bus_id", type = IdType.ID_WORKER_STR)
	private String school_bus_id;//校车信息ID
	private Integer edtype;//（1为事件基础信息，2为图片相关信息，3为视频相关信息）
	private Integer edflag;//0(有视频),1(无视频)
	private Double lon;//经度（原始坐标）
	private Integer crash_time;//碰撞时间
	private Integer pitch;//头部，
	private Integer lane_type;//车道类型
	private String merchantId;//商家ID
	private Integer ped_num;//前方行人个数
	private String tripId;//行程ID
	private String uuid;//UUID
	private Integer speed;//车速
	private Integer yaw;//头部
	private Integer ped_state;//PCW状态
	private String version;//设备系统版本号
	private Integer h;//高度（圈出上传的预警照片的发生预警的位置，如果是线的话则只有X,Y）
	private Integer type;//事件类型
	private Integer roll;//头部
	private String time;//事件ID
	private Double y;//左上角纵坐标（圈出上传的预警照片的发生预警的位置，如果是线的话则只有X,Y）
	private Integer lane_state;//LDW状态
	private Integer eye_close;//眼睛状态
	private Double lat;//纬度（原始坐标）
	private Integer ped_dis;//前方行人距离
	private String endpoint;//fatigue.oss-cn-beijing.aliyuncs.com",//阿里云OSS
	private String url;
	private Double lane_width;
	private Double mouth_distance;
	private Double lane_ttc;
	private Double w;//宽度（圈出上传的预警照片的发生预警的位置，如果是线的话则只有X,Y）
	private Double lane_curve;
	private Double x;//左上角横坐标（圈出上传的预警照片的发生预警的位置，如果是线的话则只有X,Y）
	private Double lane_dis;//车道宽度
	private Double heading;//朝向
	private Double altitude;//高程
	private String userid;//驾驶员ID
	private String deviceId;//设备ID
	private Integer fatigue_type;//疲劳状态
	private Integer lane_mid_offset;//车道偏移率
	private Integer car_state;//FCW
	private Integer smoke_phone;//手势状态
	private Integer car_dis;//前车距离
	private String front_img;//前录图片URL   type2
	private String back_img; //后录图片URL   type2
	private String back_video; //后录视频URL type3
	private String front_video;//前录视频URL type3

	@Override
	public String toString() {
		return "SchoolBusEd{" +
				"school_bus_id='" + school_bus_id + '\'' +
				", edtype=" + edtype +
				", edflag=" + edflag +
				", lon=" + lon +
				", crash_time=" + crash_time +
				", pitch=" + pitch +
				", lane_type=" + lane_type +
				", merchantId='" + merchantId + '\'' +
				", ped_num=" + ped_num +
				", tripId='" + tripId + '\'' +
				", uuid='" + uuid + '\'' +
				", speed=" + speed +
				", yaw=" + yaw +
				", ped_state=" + ped_state +
				", version='" + version + '\'' +
				", h=" + h +
				", type=" + type +
				", roll=" + roll +
				", time='" + time + '\'' +
				", y=" + y +
				", lane_state=" + lane_state +
				", eye_close=" + eye_close +
				", lat=" + lat +
				", ped_dis=" + ped_dis +
				", endpoint='" + endpoint + '\'' +
				", url='" + url + '\'' +
				", lane_width=" + lane_width +
				", mouth_distance=" + mouth_distance +
				", lane_ttc=" + lane_ttc +
				", w=" + w +
				", lane_curve=" + lane_curve +
				", x=" + x +
				", lane_dis=" + lane_dis +
				", heading=" + heading +
				", altitude=" + altitude +
				", userid='" + userid + '\'' +
				", deviceId='" + deviceId + '\'' +
				", fatigue_type=" + fatigue_type +
				", lane_mid_offset=" + lane_mid_offset +
				", car_state=" + car_state +
				", smoke_phone=" + smoke_phone +
				", car_dis=" + car_dis +
				", front_img='" + front_img + '\'' +
				", back_img='" + back_img + '\'' +
				", back_video='" + back_video + '\'' +
				", front_video='" + front_video + '\'' +
				'}';
	}
}
