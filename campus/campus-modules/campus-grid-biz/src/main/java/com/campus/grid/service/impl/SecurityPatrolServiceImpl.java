package com.campus.grid.service.impl;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.vo.RectificationTaskVO;
import com.campus.grid.mapper.HiddenDangerMapper;
import com.campus.grid.mapper.SecurityPatrolMapper;
import com.campus.grid.util.InspectHiddenUtils;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;

import com.campus.grid.api.dto.FunctionTypeDTO;
import com.campus.grid.api.dto.GridFunctionDTO;
import com.campus.grid.api.dto.SafetyInspectDTO;
import com.campus.grid.api.entity.*;
import com.campus.grid.service.SecurityPatrolService;
import com.campus.grid.api.entity.JPush;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class SecurityPatrolServiceImpl implements SecurityPatrolService {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private SecurityPatrolMapper securityPatrolMapper;
	@Autowired
	private HiddenDangerMapper hiddenDangerMapper;
	@Autowired
	private JPush jPush;
	@Autowired
	private RemoteDeptService remoteDeptService;

	@Override
	public Map<Object, Object> queryPageData(String type) {
		List<String> roleCodes = securityPatrolMapper.findRoleCode(SecurityUtils.getUser().getId());
		String roleCode="";
		boolean contains = roleCodes.contains("ROLE_SCHOOL");
		if (contains) roleCode="ROLE_SCHOOL";
		String schoolId = SecurityUtils.getUser().getSchoolId();
		Map<Object, Object> map = new HashMap<Object, Object>();
//		根据角色学校id和当前用户获取网格 所有的问题类型 所有的事件标签 当前学校所有的用户
		map.put("reseauNames", securityPatrolMapper.selectReseauName(SecurityUtils.getUser().getId(),roleCode,schoolId,type));
		map.put("functionNames", securityPatrolMapper.selectFunctionName(type));
		map.put("eventLabels", securityPatrolMapper.selectEventLabel());
		map.put("users",hiddenDangerMapper.selectUserName(schoolId));
		return map;
	}

	@Override
	public GridFunctionDTO queryGridFunction(String reseauId) {
//		获取网格功能
		GridFunctionDTO gridFunction = securityPatrolMapper.findGridFunction(reseauId);
		return gridFunction;
	}

	@Override
	public Map<Object, Object> queryFunctionType(String functionId) {
		List<FunctionType> functionTypes = securityPatrolMapper.selectFunctionType(functionId);
//		获取问题类型获取所有的问题基本类型
		List<FunctionTypeDTO> functionTypeDTOS = new ArrayList<FunctionTypeDTO>();
		for (FunctionType functionType : functionTypes) {
			String functionTypeName = functionType.getFunctionName();
			String values = functionType.getFunctionValues();
			List<String> functionTypeValue = new ArrayList<String>();
			if (values!=null){
				String[] value = values.split(",");
				for (int i = 0; i < value.length; i++) {
					String s = value[i];
					functionTypeValue.add(s);
				}
			}
			FunctionTypeDTO functionTypeDTO = new FunctionTypeDTO(null,null,functionTypeName, functionTypeValue,null);
			functionTypeDTOS.add(functionTypeDTO);
		}
		Map<Object, Object> map = new HashMap<>();
//		根据功能id获取功能名称
		map.put("functionName", functionId);
		map.put("records", functionTypeDTOS);
		return map;
	}

	@Override
	public R addSecurityInformation(String reseauId, String functionId, Integer level, String remark, String itemLabel, String eventLabels, String picNames, String mendMan,Integer type) {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		String inspectId = UUID.randomUUID().toString().replaceAll("-", "");
		String userId = SecurityUtils.getUser().getId();
		LocalDateTime createTime = InspectHiddenUtils.date2LocalDateTime(new Date());
//		无情况时会调用这个来录入
		if (functionId == null || functionId == "" || level == null || itemLabel == "") {
			try {
				SafetyInspect safetyInspect = new SafetyInspect(inspectId, schoolId,null, reseauId,null, userId, null, null, 3, userId, "今日无情况", createTime,null,null,type);
				securityPatrolMapper.insertNoCondition(safetyInspect);
				return new R<>(null, "success");
			} catch (Exception e) {
				return new R<>(e);
			}
		}
		if (mendMan.length()==0) mendMan=userId;
		if (picNames.length()!=0) securityPatrolMapper.insertPic(inspectId,inspectId,picNames,0,createTime);
//		把问题和事件还有描述进行拼接
		String[] split1 = eventLabels.split(",");
		String neweventLabels = "";
		for (int i = 0; i < split1.length; i++) {
			String s = split1[i];
			neweventLabels = neweventLabels + s + " ";
		}
		remark = itemLabel + "  " + neweventLabels + "  " + remark;
		try {
			SafetyInspect safetyInspect = new SafetyInspect(inspectId, schoolId, null,reseauId,null, userId, functionId, level, 1,mendMan,remark,createTime,null,null,type);
			securityPatrolMapper.insertSafetyInspect(safetyInspect);
			String registrationId = (String)redisTemplate.opsForValue().get(mendMan);
			if (StringUtils.isNotBlank(registrationId)) InspectHiddenUtils.buildPushObjectAllAliasAalert(jPush.getMasterSecret(),jPush.getAppKey(),registrationId,"您有一条新的整改信息,请点击查看");
			return new R<>(null, "success");
		} catch (Exception e) {
			return new R<>(e);
		}
	}

	@Override
	public Map<Object, Object> querySafetyInspect(Integer current, Integer size, String date, String isNotarize, String startDate, String endDate,String functionId,Integer level,Integer status,String ebSchoolId,String task,String reseauName,Integer type) {
		List<String> roleCodes = securityPatrolMapper.findRoleCode(SecurityUtils.getUser().getId());
		String roleCode="";
		boolean contains = roleCodes.contains("ROLE_SCHOOL");
		if (contains) roleCode="ROLE_SCHOOL";
		String schoolId = SecurityUtils.getUser().getSchoolId();
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (!StringUtils.isNotBlank(ebSchoolId)) ebSchoolId=null;
		if (childDepts.size()==0) childDepts.add("notHave");
		Map<Object, Object> map = new HashMap<>();
		LocalDateTime startTime=InspectHiddenUtils.shellDate(date).get("startTime");
		LocalDateTime endTime=InspectHiddenUtils.shellDate(date).get("endTime");
		if (StringUtils.isNotBlank(startDate)&&StringUtils.isNotBlank(endDate)) {
			startTime = InspectHiddenUtils.string2LocalDateTime(startDate);
			endTime = InspectHiddenUtils.string2LocalDateTime(endDate);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<SafetyInspect> safetyInspects = securityPatrolMapper.selectSafetyInspect((current - 1) * size, size, startTime, endTime, SecurityUtils.getUser().getId(), isNotarize,functionId,level,status,roleType,childDepts,roleCode,schoolId,ebSchoolId,task,reseauName,type);
//		吧查询出的数据进行封装
		List<SafetyInspectDTO> safetyInspectDTOS = new ArrayList<>();
		for (SafetyInspect safetyInspect : safetyInspects) {
			String processedPics=securityPatrolMapper.selectPics(safetyInspect.getInspectId(),0);
			List<String> processedPic=new ArrayList<>();
			if (StringUtils.isNotBlank(processedPics)) processedPic=InspectHiddenUtils.arrayToList(processedPic,processedPics);
			String untreatedPics = securityPatrolMapper.selectPics(safetyInspect.getInspectId(), 1);
			List<String> untreatedPic=new ArrayList<>();
			if (StringUtils.isNotBlank(untreatedPics)) untreatedPic=InspectHiddenUtils.arrayToList(untreatedPic,untreatedPics);
			SafetyInspectDTO safetyInspectDTO=new SafetyInspectDTO(safetyInspect);
			safetyInspectDTO.setCreateName(simpleDateFormat.format(InspectHiddenUtils.localDateTime2Date(safetyInspect.getCreateTime())));
			Date date1 = InspectHiddenUtils.localDateTime2Date(safetyInspect.getUpdateTime());
			if (date1!=null)safetyInspectDTO.setUpdateTime(simpleDateFormat.format(date1));
			safetyInspectDTO.setDeptName(securityPatrolMapper.findDeptName(safetyInspect.getSchoolId()));
			safetyInspectDTO.setProcessedPic(processedPic);
			safetyInspectDTO.setUntreatedPic(untreatedPic);
			safetyInspectDTO.setGridFunctionDTO(securityPatrolMapper.findGridFunction(safetyInspect.getReseauId()));
			safetyInspectDTOS.add(safetyInspectDTO);
			}
//		查询所有的问题类型，供前台搜索界面使用
		List<FunctionType> functionTypes = securityPatrolMapper.selectFunctionName(null);
//		查询所有的条数
		Integer total=null;
		if (task==null) total = securityPatrolMapper.total(startTime, endTime, SecurityUtils.getUser().getId(), isNotarize,functionId,level,status,roleType,childDepts,roleCode,schoolId,ebSchoolId,reseauName,type);
		map.put("records", safetyInspectDTOS);
		map.put("functionTypes",functionTypes);
		map.put("total", total);
		return map;
	}

	@Override
	public R alterSafetyInspectStatus(String inspectId, String message,String picNames) {
		try {
			LocalDateTime localDateTime = InspectHiddenUtils.date2LocalDateTime(new Date());
			String id = UUID.randomUUID().toString().replaceAll("-", "");
//			查询是否已被修改
			Integer inspectStatus = securityPatrolMapper.findInspectStatus(inspectId);
			if (inspectStatus==2){
				return new R<>(null, "isok");
			}
//			判断是否传进照片，有则插入照片，无则直接自改状态
			if (picNames.length()!=0){
				securityPatrolMapper.insertPic(id,inspectId,picNames,1,localDateTime);
			}
			securityPatrolMapper.updateSafetyInspectStatus(inspectId, message, localDateTime);
			return new  R<>(null, "success");
		} catch (Exception e) {
			return new R<>(e);
		}
	}

	@Override
	public Map<Object, Object> getRegistrationId(String registrationId) {
		Map<Object, Object> map = new HashMap<>();
		redisTemplate.opsForValue().set(SecurityUtils.getUser().getId(),registrationId);
		map.put("msg","ok");
		return map;
	}

	@Override
	public Map<Object, Object> deleteRegistrationId() {
		Map<Object, Object> map = new HashMap<>();
		redisTemplate.opsForValue().set(SecurityUtils.getUser().getId(),null);
		map.put("msg","ok");
		return map;
	}

	@Override
	public Map<String, Object> recordNumber() {
		Map<String, Object> map = new HashMap<>();
		map.put("fireFighting",securityPatrolMapper.findFireFighting(SecurityUtils.getUser().getId()));
		map.put("Inspect",securityPatrolMapper.findInspect(SecurityUtils.getUser().getId()));
		map.put("hiddenDanger",securityPatrolMapper.findHiddenDanger(SecurityUtils.getUser().getId()));
		return map;
	}

	@Override
	public PageBean recordDetails(Integer currentPage, Integer size, Integer type,String userId,String rectification,Integer status) {
		int total = securityPatrolMapper.getTotal(type, userId, rectification, status);
		Integer startIndex = (currentPage - 1) * size;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<SafetyInspect> safetyInspects = securityPatrolMapper.recordDetails(startIndex, size, type, userId,rectification,status);
		List<SafetyInspectDTO> safetyInspectDTOS = new ArrayList<>();
		for (SafetyInspect safetyInspect : safetyInspects) {
			String processedPics=securityPatrolMapper.selectPics(safetyInspect.getInspectId(),0);//隐患处理前的图片
			List<String> processedPic=new ArrayList<>();
			if (StringUtils.isNotBlank(processedPics)) processedPic=InspectHiddenUtils.arrayToList(processedPic,processedPics);
			String untreatedPics = securityPatrolMapper.selectPics(safetyInspect.getInspectId(), 1);//隐患处理后的图片
			List<String> untreatedPic=new ArrayList<>();
			if (StringUtils.isNotBlank(untreatedPics)) untreatedPic=InspectHiddenUtils.arrayToList(untreatedPic,untreatedPics);
			SafetyInspectDTO safetyInspectDTO=new SafetyInspectDTO(safetyInspect);
			safetyInspectDTO.setCreateName(simpleDateFormat.format(InspectHiddenUtils.localDateTime2Date(safetyInspect.getCreateTime())));
			Date date1 = InspectHiddenUtils.localDateTime2Date(safetyInspect.getUpdateTime());
			if (date1!=null)safetyInspectDTO.setUpdateTime(simpleDateFormat.format(date1));
			safetyInspectDTO.setDeptName(securityPatrolMapper.findDeptName(safetyInspect.getSchoolId()));
			safetyInspectDTO.setProcessedPic(processedPic);
			safetyInspectDTO.setUntreatedPic(untreatedPic);
			safetyInspectDTO.setGridFunctionDTO(securityPatrolMapper.findGridFunction(safetyInspect.getReseauId()));
			safetyInspectDTOS.add(safetyInspectDTO);
			if (safetyInspect.getType()==2) safetyInspectDTO.setMode(securityPatrolMapper.findMode(safetyInspect.getInspectId()));
		}
		PageBean pageBean = new PageBean();
		pageBean.setCurrent(currentPage);
		double ceil = Math.ceil((total * 1.0) / size);
		int pages = (int) ceil;
		pageBean.setSize(size);
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setRecords(safetyInspectDTOS);
//		Map<String,Object> map=new HashMap<>();
//		map.put("records", safetyInspectDTOS);
		return pageBean;
	}

	@Override
	public PageBean rectificationTask(Integer currentPage, Integer size, Integer type) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = null;
		try {
			newDate = format.parse(format.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    //SecurityUtils.getUser().getId()
//		int total = securityPatrolMapper.reseauOfUserTotal("1187251561647050754", type);
		Integer startIndex = (currentPage - 1) * size;
		//根据用户id查询所有负责的网格
		/**
		 * 分为三种情况
		 * type为null的时候两个都差
		 * 不为null的时候只查一种
		 * 根据巡查走起啊判断今天是否该巡查 若是则添加到返回的集合之中
		 */
		List<RectificationTaskVO> rectificationTaskVOS = securityPatrolMapper.selectReseauOfUser(startIndex, size, SecurityUtils.getUser().getId(), type);
		List<RectificationTaskVO> data=new ArrayList<>();
		for (RectificationTaskVO rectificationTaskVO : rectificationTaskVOS) {
			Integer responsiblyType = rectificationTaskVO.getResponsiblyType();
			if (responsiblyType==2) responsiblyType=3;
			Integer rectificationCount = securityPatrolMapper.findRectificationCount(rectificationTaskVO.getReseauId(), InspectHiddenUtils.date2LocalDateTime(newDate),responsiblyType);
			if (rectificationCount!=0) continue;
			//获取时间差
			int dayDifference = (int) ((newDate.getTime() - InspectHiddenUtils.localDateTime2Date(rectificationTaskVO.getInspectStartDate()).getTime()) / (1000*3600*24));
			if (rectificationTaskVO.getInspectMode()==1) data.add(rectificationTaskVO);
			if (rectificationTaskVO.getInspectMode()==2 && dayDifference%2==0) data.add(rectificationTaskVO);
			if (rectificationTaskVO.getInspectMode()==3 && dayDifference%7==0) data.add(rectificationTaskVO);
			if (rectificationTaskVO.getInspectMode()==4 && dayDifference%14==0) data.add(rectificationTaskVO);
			if (rectificationTaskVO.getInspectMode()==5){
				String stringOfNewDate = format.format(newDate);
				String stringOfCreateDate = format.format(InspectHiddenUtils.localDateTime2Date(rectificationTaskVO.getInspectStartDate()));
				String[] split = stringOfCreateDate.split("-");
				String[] split1 = stringOfNewDate.split("-");
				if (split[2].equals(split1[2])) data.add(rectificationTaskVO);
			}
		}
		int total = data.size();
		PageBean pageBean = new PageBean();
		pageBean.setCurrent(currentPage);
		double ceil = Math.ceil((total * 1.0) / size);
		int pages = (int) ceil;
		pageBean.setSize(size);
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setRecords(data);
		return pageBean;
	}

}
