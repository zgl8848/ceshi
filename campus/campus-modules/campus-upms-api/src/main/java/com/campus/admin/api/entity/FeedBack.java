package com.campus.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("sys_feedback")

public class FeedBack extends Model<FeedBack> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "feedback_id", type = IdType.ID_WORKER_STR)
	private String feedBackId;  //主键

	private String feedBackType;//问题类型
	private String contact;     //联系方式
	private String content;     //反馈内容
	private String status;      //处理状态0未处理 1 已处理
	private String reason;      //处理意见
	private String operater;    //处理人
	private String operaterTime;//处理日期
	private String feedBackResult;//处理结果
	private LocalDateTime feedBackTime;//反馈时间
	private String picNames;//上传图片

}
