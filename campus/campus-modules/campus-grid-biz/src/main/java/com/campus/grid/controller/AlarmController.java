package com.campus.grid.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;
import com.campus.grid.service.AlarmHistoryService;
import com.campus.grid.service.AlarmService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/alarm")
@Api(value = "alarm", description = "实时告警模块")
public class AlarmController {

	private final AlarmService alarmService;
	private final AlarmHistoryService alarmHistoryService;
	
	@PostMapping(path = {"/getImage"})
	public R getBase64Image(@RequestBody JSONObject jo) {
		try {
			String data = alarmService.getBase64Image(jo.getString("schoolId"), jo.getString("url"));
			return new R<>().setCode(0).setMsg("success").setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取图片出错");
		}
	}

	/**
	 * 按条件查询(可视化平台)
	 *
	 * @param current
	 * @param size
	 * @param schoolId
	 * @return
	 */
	@RequestMapping(path = {"/queryPage"})
	public R getQueryPage(@RequestParam(required = false)Integer current, @RequestParam(required = false)Integer size,
						  @RequestParam(required = false)String schoolId, @RequestParam(required = false)String date) {
		PageBean pageBean = alarmService.queryPage(current, size, schoolId, date);
		return new R<>(pageBean);
	}

	/**
	 * 接受告警消息接口
	 *
	 * @param alarm
	 * @return
	 */
	@PostMapping("/saveAlarmInfo")
	public R saveAlarmInfo(@RequestBody Alarm alarm) {
//		插入前先查询是否已存在告警,避免重复插入
		String alarmId = alarmService.isHaveAlarm(alarm);
//			如果存在即直接返回
		if (alarmId != null) {
			return new R<>();
		}
//			不存在则插入告警
		return new R<>(alarmService.saveAlarmInfo(alarm));

	}

	/**
	 * 修改实时告警(将此告警信息从实时表中删除,添加到历史表中)
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('alarmManagement_alarm_update')")
	public R removeById(@PathVariable String id) {
		Alarm alarm = alarmService.getById(id);
		alarm.setEndTime(new Date());
		alarmHistoryService.insertAlarmHistoryInfo(alarm);
		return new R<>(alarmService.removeById(id));
	}

	/**
	 * 存储黑名单人脸信息
	 */
	@PostMapping(value = "/saveBlackPerson")
	public R saveBlackPerson(@RequestBody Person person) {
		return new R<>(alarmService.saveBlackPerson(person));
	}

	/**
	 * 分页查询告警信息
	 *
	 * @param page 分页对象
	 * @param schoolId 学校ID
	 * @param title 告警标题
	 * @return
	 */
	@RequestMapping(path = {"/page"})
	public R getAlarmPage(Page page, @RequestParam(required = false) String schoolId, @RequestParam(required = false) String title) {
		return new R<>(alarmService.getAlarmPage(page, schoolId, title));
	}

	/**
	 * 根据告警模块 统计(可视化平台)
	 * @param schoolId
	 * @param date
	 * @return
	 */
	@RequestMapping(path = {"/stat/module"})
	public R statByModule(@RequestParam(required = false) String schoolId, @RequestParam(required = false) String date){
		return new R<>(alarmService.statByModule(schoolId, date));
	}

	/**
	 * 根据区域 统计(可视化平台)
	 * @param date
	 * @return
	 */
	@RequestMapping(path = {"/stat/area"})
	public R statByModule(@RequestParam(required = false) String date){
		return new R<>(alarmService.statByAreaCode(date));
	}

	/**
	 * 存储表情识别人脸信息
	 * @return
	 */
	@PostMapping(value = "/saveFaceInfo")
	public R saveFaceInfo(@RequestBody MongoEmotionPerson emotionPerson){
		return new R<>(alarmService.saveFaceInfo(emotionPerson));
	}
	/*
	@RequestMapping(path = {"/queryPage"})
	public R getQueryPage(@RequestParam(required = false) Integer current,@RequestParam(required = false)Integer size,
						  @RequestParam(required = false) String schoolId,@RequestParam(required = false) String date){
		PageBean pageBean = alarmService.queryPage(current,size,schoolId,date);
		return new R<>(pageBean);
	}
	git push -u origin master
	*/
	/**
	 * 根据月份 统计(可视化平台)
	 * @param date
	 * @return
	 */
	@RequestMapping(path = {"/stat/month"})
	public R statByMonth(@RequestParam(required = false) String date){
		return new R<>(alarmService.statByMonth(date));
	}
}