package com.campus.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.entity.FeedBack;
import com.campus.admin.service.FeedBackService;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/feed")
public class FeedBackController {

	private FeedBackService feedBackService;

	/**
	 * 分页查询意见反馈信息
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getFeedBackPage(Page page,FeedBack feedBack) {
		if( "".equals(feedBack.getFeedBackType())){
			feedBack.setFeedBackType(null);
		}
		if("".equals(feedBack.getStatus())) {
			feedBack.setStatus(null);
		}
		IPage feedBackPage = feedBackService.page(page.setDesc("feed_back_time"), Wrappers.query(feedBack));
		return new R<>(feedBackPage);
	}

	/**
	 * 保存意见反馈信息
	 * @TableId(value = "feedback_id", type = IdType.ID_WORKER_STR)
	 * 	private String feedBackId;  //主键
	 */
	@RequestMapping("/createFeedBack")
	public void createFeedBack(String feedBackType, String contact, String content , String reason, String picNames) {
		FeedBack feedBack = new FeedBack();
		feedBack.setFeedBackType(feedBackType);
		feedBack.setContact(contact);
		feedBack.setContent(content);
		feedBack.setReason(reason);
		feedBack.setFeedBackTime(LocalDateTime.now());
		feedBack.setPicNames(picNames);
		feedBack.setStatus("0");
		feedBackService.createFeedBack(feedBack);
	}


	/**
	 * 删除意见反馈
	 * @param feedback_id
	 * @return R
	 */
	@SysLog("删除意见反馈")
	@DeleteMapping("/{feedback_id}")
	public R removeById(@PathVariable String feedback_id) {
		return new R<>(feedBackService.removeById(feedback_id));
	}

	/**
	 * 修改意见反馈
	 *
	 * @param feedBack 意见反馈信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改意见反馈")
	public R updateById(@Valid @RequestBody FeedBack feedBack ) {
		if(feedBack.getFeedBackResult() != null || !"".equals(feedBack.getFeedBackResult()) ){
			feedBack.setStatus("1");
		}
		return new R<>(feedBackService.updateById(feedBack));
	}

}
