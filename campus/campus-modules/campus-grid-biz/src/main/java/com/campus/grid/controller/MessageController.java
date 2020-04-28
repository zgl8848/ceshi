package com.campus.grid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.Message;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.service.MessageService;
import com.campus.grid.service.impl.MessageServiceImpl;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
@Api(value = "message", description = "消息公告模块")
@Slf4j
public class MessageController {

	private MessageService messageService;
	
	/**
	 * 获取消息公告发布页面学校列表
	 * @param cityName
	 * @return
	 */
	@RequestMapping(path="/getSchoolListByCity")
	public R getSchoolListByCity() {
		PageBean pagebean = new PageBean();
		try {
			List<Map<String, Object>> schoolList = messageService.getSchoolListByCity("佛山市");
			pagebean.setRecords(schoolList);
		} catch (Exception e) {
			log.error("获取学校列表出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取学校列表出错!");
		}
		return new R<>(pagebean);
	}
	
	/**
	 * 通过学校ID获取部门
	 * @param schoolId
	 * @return
	 */
	@RequestMapping(path="/getPostIdBySchoolId")
	public R getPostIdBySchoolId(@RequestParam("schoolId")String schoolId) {
		PageBean pagebean = new PageBean();
		try {
			List<Map<String, String>> postIdList = messageService.getPostIdBySchoolId(schoolId);
			pagebean.setRecords(postIdList);
		} catch (Exception e) {
			log.error("获取学校部门出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取学校部门出错!");
		}
		return new R<>(pagebean);
	}
	
	/**
	 * 通过部门ID获取部门
	 * @param schoolId
	 * @return
	 */
	@RequestMapping(path="/getSchoolListByDeptId")
	public R getPostIdByDeptId(@RequestParam("deptId")String deptId) {
		PageBean pagebean = new PageBean();
		try {
			List<School> schoolList = messageService.getSchoolListByDeptId(deptId);
			pagebean.setRecords(schoolList);
		} catch (Exception e) {
			log.error("获取学校部门出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取学校部门出错!");
		}
		return new R<>(pagebean);
	}
	
	/**
	 * 获取消息公告列表
	 * @param page
	 * @param schoolId
	 * @return
	 */
	@GetMapping("/page")
	public R getMessagePage(Page page,String schoolId,String messageArea,String createTime,String endTime) {
		try {
			return new R<>(messageService.listMessagePage(page, schoolId,messageArea,createTime,endTime));
		} catch (Exception e) {
			log.error("获取消息列表出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("获取消息列表出错!");
		}
	}
	
	/**
	 *  保存消息公告发布消息
	 * @param message
	 * @return
	 */
	@PostMapping(path="/saveMessage")
	public R saveMessage(Message message) {
		try {
			return new R<>(messageService.saveMessage(message));
		} catch (Exception e) {
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("保存消息公告出错!");
		}
	}
	
	/**
	 *   上传图片
	 * @param file
	 * @return
	 */
	@PostMapping(path="/upload")
	public R upload(@RequestParam("file") MultipartFile[] file) {
		Map<String, List<String>> retImageMap = new HashMap<String, List<String>>();
		try {
			List<String> imageList = messageService.uploadImage(file);
			retImageMap.put("image", imageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.builder().data(retImageMap).build();
	}
	
	/**
	 * app获取当前用户消息列表
	 * @param page
	 * @param userId
	 * @param messageType
	 * @return
	 */
	@GetMapping("/appMessagePage")
	public R appGetMessageListByUser(Page page,String userId,String messageType) {
		try {
			return new R<>(messageService.appGetMessageListByUser(page, userId, messageType));
		} catch (Exception e) {
			log.error("app获取当前用户消息列表出错!");
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("app获取当前用户消息列表出错!");
		}
	}
	
	/**
	 *  更改用户消息状态
	 * @param message
	 * @return
	 */
	@GetMapping(path="/updateMessageStatus")
	public R updateMessageStatusByUser(String userId,String messageType) {
		try {
			return new R<>(messageService.updateMessageStatusByUser(userId, messageType));
		} catch (Exception e) {
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("更改用户消息状态出错!");
		}
	}
	
	/**
	 *   统计用户未读消息数量
	 * @param userId
	 * @return
	 */
	@GetMapping(path="/messageStatusCount")
	public R messageStatusCount(String userId) {
		Map<String, Integer> retImageMap = null;
		try {
			retImageMap = messageService.messageStatusCount(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new R<>().setCode(1).setMsg("统计用户未读消息数量出错!");
		}
		return R.builder().data(retImageMap).build();
	}
}
