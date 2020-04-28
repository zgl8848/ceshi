package com.campus.grid.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.SysDept;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.Message;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.mapper.MessageMapper;
import com.campus.grid.mapper.SchoolMapper;
import com.campus.grid.service.MessageService;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IService<Message>, MessageService{
	
	private MessageMapper messageMapper;
	
	private SchoolMapper schoolMapper;

	@Override
	public List<Map<String, Object>> getSchoolListByCity(String city) throws Exception {
		String deptId = SecurityUtils.getUser().getDeptId();/* "1087226472829476865"; */
		String cityCode = messageMapper.getCityCodeByName(city); 
		String cityDeptId = messageMapper.getDeptIdByName(city);
		List<SysDept> deptList = messageMapper.getCountyByCity(cityDeptId,cityCode); //获取佛山市的各大区域
		Map<String, Object> retMap = null;
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		int index = 0;
		for(SysDept s:deptList) {
			String flag = messageMapper.isNullByDeptId(deptId, s.getDeptId()); //判断权限
			if(flag == null) {
				continue ;
			}
			List<School> schoolList = messageMapper.getSchoolListByCity(s.getDeptId(), cityCode); //通过区域ID获取当下的学校
			retMap = new HashMap<String, Object>();
			retMap.put("schoolName", s.getName()); //区域名称（禅城区...）
			retMap.put("children", schoolList);
			retMap.put("schoolId", index++); //前端规定
			retList.add(retMap);
		}
		return retList;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveMessage(Message message) throws Exception{
		String messageId = UUID.randomUUID().toString().replace("-", "");
		String createUserId = SecurityUtils.getUser().getId();
		String[] schoolArray = message.getMessageSchool().split(",");
		for (int i = 0; i < schoolArray.length; i++) {
			String schoolId = schoolArray[i];
			String receiver = message.getMessageReceiver(); //获取接收人员(也就是部门)
			if(!StringUtils.isBlank(receiver)) { // 接收人员在不等于空的情况下，代表只有一个学校，一个或多个部门。
				String[] receiverArray = receiver.split(",");
				for (int j = 0; j < receiverArray.length; j++) {
					String receiverId = receiverArray[i]; // 接受人员(部门)
					List<String> userList = messageMapper.getUserByReceiver(receiverId);
					for(String userId:userList) {
						messageMapper.saveMessageUser(messageId, userId,message.getMessageType());
					}
				}
			}
			// 根据学校ID获取当前学校所有人员
			List<String> userList = schoolMapper.getUserBySchoolId(schoolId);
			for(String userId:userList) {
				messageMapper.saveMessageUser(messageId, userId,message.getMessageType());
			}
		}
		message.setMessageId(messageId);
		message.setCreateUser(createUserId);
		messageMapper.saveMessage(message);
		return 1;
	}

	@Override
	public List<Map<String, String>> getPostIdBySchoolId(String schoolId) throws Exception {
		return messageMapper.getPostIdBySchoolId(schoolId);
	}

	@Override
	public IPage listMessagePage(Page page, String schoolId,String messageArea,String createTime,String endTime) throws Exception {
		if((!StringUtils.isBlank(createTime) && !StringUtils.isBlank(endTime)) && createTime.equals(endTime)) {
			createTime += " 00:00:00";
			endTime += " 24:00:00";
		}
		IPage ipage = messageMapper.listMessagePage(page, schoolId,messageArea,createTime,endTime);
		List<Message> messageList = ipage.getRecords();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messageList.size(); i++) { //前端列表展示问题 需要将学校名称拼接
			String receiver = messageList.get(i).getMessageReceiver();
			if(receiver.length() > 0) {
				List<String> receiverList = messageMapper.getPersonnelUser(receiver);//解析部门名称
				receiver = receiverList.toString().substring(1,receiverList.toString().length()-1);
				messageList.get(i).setMessageReceiver(receiver);
			}
			String school = messageList.get(i).getMessageSchool();
			List<String> schoolNameList = messageMapper.getSchoolNameById(school); //解析学校名称
			school = schoolNameList.toString().substring(1,schoolNameList.toString().length()-1);
			messageList.get(i).setMessageSchool(school);
		}
		ipage.setRecords(messageList);
		return ipage;
	}

	@Override
	public List<String> uploadImage(MultipartFile[] file) throws Exception {
		String imageUrl = "";
		List<String> imageList = new ArrayList<String>();
		if(file != null && file.length > 0) {
			for(MultipartFile f:file) {
				imageUrl = imgUpload(f);
				imageList.add(imageUrl);
			}
		}
		return imageList;
	}
	
	public String imgUpload(MultipartFile file) {
	    String fileName = file.getOriginalFilename();
	    String suffixName = fileName.substring(fileName.lastIndexOf("."));
	    fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
	    // 图片存储地址，例如"E:/imagesServer/"
	    String parent = "D://";
	    String imgName = fileName;
	    try {
	        File targetFile = new File(parent, fileName);
	        // 将上传文件存储到服务器中
	        file.transferTo(targetFile);
	        
	        // 图片显示地址，例如"http://localhost:8080/imgFiles/" + imgName
	        imgName = parent + imgName;
	        System.out.println(imgName);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return imgName;
	}

	@Override
	public List<School> getSchoolListByDeptId(String deptId) throws Exception {
		return messageMapper.getSchoolListByDeptId(deptId);
	}

	@Override
	public IPage appGetMessageListByUser(Page page,String userId,String messageType) throws Exception {
		return messageMapper.appGetMessageListByUser(page, userId, messageType);
	}

	@Override
	public int updateMessageStatusByUser(String userId,String messageType) throws Exception {
		return messageMapper.updateMessageStatusByUser(userId, messageType);
	}

	@Override
	public Map<String, Integer> messageStatusCount(String userId) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("notice", messageMapper.messageCountByUser(userId, "1"));
		return map;
	}
}
