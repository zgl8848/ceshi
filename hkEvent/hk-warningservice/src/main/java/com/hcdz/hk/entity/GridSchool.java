package com.hcdz.hk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author CR7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("grid_school")
@Document(collection = "grid_school")
public class GridSchool {
	/**
	 * 主键
	 */
	@Id
	private String schoolId;
	/**
	 * 部门ID
	 */
	private String dept_id;
	/**
	 * 学校名称
	 */
	private String school_name;
	/**
	 * 学校编码
	 */
	private String school_code;
	/**
	 * 1-幼儿园 2-小学 3-初中 4-高中
	 */
	private String school_type;
	/**
	 * 学生总数
	 */
	private Integer	student_total;
	/**
	 * 住宿生总数
	 */
	private Integer	boarder_total;
	/**
	 * 教师总数
	 */
	private Integer	teacher_total;
	/**
	 * 职工总数
	 */
	private Integer	workers_total;
	/**
	 * 临时工人
	 */
	private Integer	temporary_worker_total;
	/**
	 * 是否有食堂(0-无，1-有)
	 */
	private String is_canteen;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city; 
	/**
	 * 县
	 */
	private String county;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	private String del_flag;
	/**
	 * 0-正常，9-锁定
	 */
	private String lock_flag;
	/**
	 * 派出所
	 */
	private String police_office;
	/**
	 * 责任民警
	 */
	private String police_name;
	/**
	 * 民警联系电话
	 */
	private String	police_contact_phone;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建日期
	 */
	private Date create_time; 
	/**
	 * 学校性质（1.公立、2.民办、3.私立）
	 */
	private String school_nature;
	/**
	 * 视频平台URL地址
	 */
	private String platform_url;
	/**
	 * 第三方用户名
	 */
	private String third_party_username;
	/**
	 * 第三方密码
	 */
	private String third_party_password;
	/**
	 * 告警平台ip
	 */
	private String alarm_platform_ip;
	/**
	 * 告警图片服务器端口
	 */
	private String alarm_picture_port;
	/**
	 * 广播房间
	 */
	private String broadcast_room;
	/**
	 * 广播用户
	 */
	private String broadcast_user;
	/**
	 * 广播地址
	 */
	private String broadcast_url;
	/**
	 * 广播室地址
	 */
	private String broadcast_room_server_url;
	/**
	 * 用户行政区域码
	 */
	private String broadcast_areacode;
	/**
	 * 广播室
	 */
	private String broadcast_id;
}
