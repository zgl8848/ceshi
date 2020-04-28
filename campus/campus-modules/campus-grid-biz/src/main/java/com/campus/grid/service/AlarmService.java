package com.campus.grid.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;

import java.util.Map;

public interface AlarmService extends IService<Alarm>{
	
	String getBase64Image(String schoolId,String url)throws Exception;

	boolean saveAlarmInfo(Alarm alarm);

	String isHaveAlarm(Alarm alarm);

	PageBean queryPage(Integer currentPage, Integer size, String schoolId, String date);

	String findLabelByType(String value, String type);

	boolean saveBlackPerson(Person person);

	/**
	 * 分页查询告警信息
	 *
	 * @param page 分页对象
	 * @param schoolId 学校ID
	 * @param title 告警标题
	 * @return
	 */
	IPage getAlarmPage(Page page, String schoolId, String title);

	/**
	 * 根据告警模块 统计
	 * @param schoolId
	 * @return
	 */
	Map<Object,Object> statByModule(String schoolId, String date);

	/**
	 * 根据区域 统计
	 * @param date
	 * @return
	 */
	Map<Object, Object> statByAreaCode(String date);

	/**
	 * 存储表情识别到的人脸信息
	 * @param mongoEmotionPerson
	 * @return
	 */
	boolean saveFaceInfo(MongoEmotionPerson mongoEmotionPerson);

	/**
	 * 根据月份 统计
	 * @param date
	 * @return
	 */
	Map<Object, Object> statByMonth(String date);
}
