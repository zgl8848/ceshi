package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.FeedBack;
import org.apache.ibatis.annotations.Param;

public interface SysFeedBackMapper extends BaseMapper<FeedBack> {

	void createFeedBack(FeedBack feedBack);

	FeedBack selectOne(@Param("feedbackId") String feedback_id);
}
