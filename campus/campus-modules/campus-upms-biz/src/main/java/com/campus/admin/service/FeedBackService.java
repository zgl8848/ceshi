package com.campus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.entity.FeedBack;

public interface FeedBackService extends IService<FeedBack> {

	void createFeedBack(FeedBack feedBack);

	FeedBack selectOne(String feedback_id);
}
