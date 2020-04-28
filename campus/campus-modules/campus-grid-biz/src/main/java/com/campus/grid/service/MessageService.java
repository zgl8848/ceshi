package com.campus.grid.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.Message;
import com.campus.grid.api.entity.School;

public interface MessageService extends IService<Message> {

	/**
	 *   获取消息公告发布页面学校列表
	 * @param cityName
	 * @return 
	 */
	List<Map<String, Object>> getSchoolListByCity(String city)throws Exception;
	
	/**
	 * 根据区域deptId获取学校
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	List<School> getSchoolListByDeptId(String deptId)throws Exception;
	
	/**
	 * 保存消息公告信息
	 */
	int saveMessage(Message message) throws Exception;
	
	/**
	 * 通过学校ID获取部门
	 */
	List<Map<String, String>> getPostIdBySchoolId(String schoolId) throws Exception;
	
	/**
	 *   分页查询消息公告列表
	 * @param page
	 * @param message
	 * @return
	 * @throws Exception
	 */
	IPage listMessagePage(Page page,String schoolId,String messageArea,String createTime,String endTime) throws Exception;
	
	/**
	 *  上传图片返回地址
	 * @param file
	 * @return
	 */
	List<String> uploadImage(MultipartFile[] file) throws Exception;
	
	/**
	 * app获取当前用户消息列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	IPage appGetMessageListByUser(Page page,String userId,String messageType)throws Exception;
	
	/**
	 *   统计用户未读消息数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Map<String, Integer> messageStatusCount(String userId)throws Exception;
	
	/**
	 *   更改用户消息状态
	 * @return
	 * @throws Exception
	 */
	int updateMessageStatusByUser(String userId,String messageType)throws Exception;
}
