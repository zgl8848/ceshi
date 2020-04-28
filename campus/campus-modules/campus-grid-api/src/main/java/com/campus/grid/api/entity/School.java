package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 学校信息表
 *
 * @author campus
 * @date 2019-01-16 16:52:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_school")
public class School extends Model<School> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "school_id", type = IdType.ID_WORKER_STR)
	private String schoolId;
	/**
	 * 部门ID
	 */
	@NotBlank(message = "学校部门ID不能为空")
	private String deptId;
	/**
	 * 学校名称
	 */
	@NotBlank(message = "学校名称不能为空")
	private String schoolName;
	/**
	 * 学校编码
	 */
	private String schoolCode;
	/**
	 * 1-幼儿园 2-小学 3-初中 4高中
	 */
	@NotBlank(message = "学校类型不能为空")
	private String schoolType;
	/**
	 * 学生总数
	 */
	@NotNull(message = "学校学生总数不能为空")
	private Integer studentTotal;
	/**
	 * 住宿生总数
	 */
	@NotNull(message = "学校住宿生总数不能为空")
	private Integer boarderTotal;
	/**
	 * 教师总数
	 */
	@NotNull(message = "学校教师总数不能为空")
	private Integer teacherTotal;
	/**
	 * 职工总数
	 */
	@NotNull(message = "学校职工总数不能为空")
	private Integer workersTotal;
	/**
	 * 临时工人
	 */
	@NotNull(message = "学校临时工人不能为空")
	private Integer temporaryWorkerTotal;
	/**
	 * 是否有食堂(0-无，1-有)
	 */
	@NotBlank(message = "学校食堂不能为空")
	private String isCanteen;
	/**
	 * 省
	 */
	@NotBlank(message = "学校省份不能为空")
	private String province;
	/**
	 * 市
	 */
	@NotBlank(message = "学校城市不能为空")
	private String city;
	/**
	 * 县
	 */
	@NotBlank(message = "学校地区不能为空")
	private String county;
	/**
	 * 地址
	 */
	@NotBlank(message = "学校地址不能为空")
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
	@NotBlank(message = "学校性质不能为空")
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
