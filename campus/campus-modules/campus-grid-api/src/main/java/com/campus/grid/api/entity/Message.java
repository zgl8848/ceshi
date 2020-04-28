package com.campus.grid.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author lc
 * @Date  2020年2月24日
 * @Desc  消息公告信息实体
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grid_message")
public class Message extends Model<Message> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6306359005055978619L;

	/**
	 * 消息ID
	 */
	private String messageId;
	
	/**
	 * 消息标题
	 */
	private String messageTitle;

	/**
	 * 消息类型
	 */
	private String messageType;

	/**
	 * 消息接收区域
	 */
	private String messageArea;

	/**
	 * 消息接收学校
	 */
	private String messageSchool;

	/**
	 * 消息接收人员
	 */
	private String messageReceiver;

	/**
	 * 消息主体
	 */
	private String messageBody;

	/**
	 * 消息状态
	 */
	private String messageStatus;

	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 结束时间（便于条件查询）
	 */
	private String endTime;

	/**
	 * 创建人
	 */
	private String createUser;

}
