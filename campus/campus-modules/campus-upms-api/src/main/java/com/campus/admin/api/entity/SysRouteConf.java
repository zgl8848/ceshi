package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 路由
 *
 * @author campus
 * @date 2018-11-06 10:17:18
 */
@Data
@TableName("sys_route_conf")
@EqualsAndHashCode(callSuper = true)
public class SysRouteConf extends Model<SysRouteConf> {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 路由ID
	 */
	private String routeId;
	/**
	 * 路由名称
	 */
	private String routeName;
	/**
	 * 断言
	 */
	private String predicates;
	/**
	 * 过滤器
	 */
	private String filters;
	/**
	 * uri
	 */
	private String uri;
	/**
	 * 排序
	 */
	@TableField(value = "`order`")
	private Integer order;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

}
