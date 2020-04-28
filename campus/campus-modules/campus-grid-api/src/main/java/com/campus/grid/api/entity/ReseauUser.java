package com.campus.grid.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author hu
 * @date 2019-01-02 16:17:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_reseau_user")
@AllArgsConstructor
public class ReseauUser extends Model<ReseauUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "reseauUserId", type = IdType.ID_WORKER_STR)
	private String reseauUserId;
	/**
	 * 	网格主键
	 */
	private String reseauId;
	/**
	 * 责任人ID
	 */
	private String userId;
	/**
	 * 类型 1-主要责任人 2-消防责任人
	 */
	private Integer responsiblyType;
	/**
	 * 巡查周期
	 */
	private Integer inspectMode;
	/**
	 * 用户真实名称(责任人)
	 */
	private String userName;
	/**
	 * 巡查开始时间
	 */
	private LocalDate inspectStartDate;

	private String remark;

	public ReseauUser() {
	}

	public ReseauUser(String reseauUserId, String reseauId, String userId, Integer responsiblyType, Integer inspectMode,String remark, LocalDate inspectStartDate) {
		this.reseauUserId = reseauUserId;
		this.reseauId = reseauId;
		this.userId = userId;
		this.responsiblyType = responsiblyType;
		this.remark = remark;
		this.inspectMode = inspectMode;
		this.inspectStartDate = inspectStartDate;
	}
}
