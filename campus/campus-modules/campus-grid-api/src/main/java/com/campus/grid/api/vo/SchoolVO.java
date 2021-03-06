package com.campus.grid.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 学校信息表
 *
 * @author campus
 * @date 2019-01-16 16:52:10
 */
@Data
public class SchoolVO implements Serializable {

	private static final long serialVersionUID = -8555548562395712892L;
	/**
	 * 主键
	 */
	private String schoolId;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 部门ID
	 */
	private String deptName;
	/**
	 * 部门ID集合
	 */
	private List<String> deptIds;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 学校编码
	 */
	private String schoolCode;
	/**
	 * 1-幼儿园 2-小学 3-初中 4高中
	 */
	private String schoolType;
	/**
	 * 学生总数
	 */
	private Integer studentTotal;
	/**
	 * 住宿生总数
	 */
	private Integer boarderTotal;
	/**
	 * 教师总数
	 */
	private Integer teacherTotal;
	/**
	 * 职工总数
	 */
	private Integer workersTotal;
	/**
	 * 临时工人
	 */
	private Integer temporaryWorkerTotal;
	/**
	 * 是否有食堂(0-无，1-有)
	 */
	private String isCanteen;
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
	 * 是否删除  1：已删除  0：正常
	 */
	private String delFlag;
	/**
	 * 是否冻结  9：冻结  0：正常
	 */
	private String lockFlag;
	/**
	 * 派出所
	 */
	private String policeOffice;
	/**
	 * 责任民警
	 */
	private String policeName;
	/**
	 * 民警联系电话
	 */
	private String policeContactPhone;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建日期
	 */
	private LocalDateTime createTime;
	/**
	 * 学校性质（1.公立、2.民办、3.私立）
	 */
	private String schoolNature;
	/**
	 * 视频平台URL地址
	 */
	private String platformUrl;
	/**
	 * 第三方平台用户名
	 */
	private String thirdPartyUsername;
	/**
	 * 第三方平台密码
	 */
	private String thirdPartyPassword;
	/**
	 * 告警平台ip
	 */
	private String alarmPlatformIp;

	/**
	 * 告警平台ip
	 */
	private String alarmPicturePort;
	/**
	 * 广播室
	 */
	private String broadcastRoom;
	/**
	 * 广播用户
	 */
	private String broadcastUser;
	/**
	 * 广播地址
	 */
	private String broadcastUrl;
	/**
	 * 广播地址
	 */
	private String broadcastRoomServerUrl;
	/**
	 * 用户行政区域码
	 */
	private String broadcastAreacode;
	/**
	 * 广播室
	 */
	private String broadcastId;

}
