package com.campus.grid.service;


import com.campus.common.core.util.R;
import com.campus.grid.api.dto.GridFunctionDTO;
import com.campus.grid.api.dto.PageBean;
import io.swagger.models.auth.In;

import java.util.Map;

public interface SecurityPatrolService {
	//今日有情况有对应的页面信息查询
	Map<Object, Object> queryPageData(String type);

	//根据网格化名称查找网格化功能
	GridFunctionDTO queryGridFunction(String reseauId);

	//根据物品类型查找对应的物品标签
	Map<Object, Object> queryFunctionType(String functionId);

	//插入一条安全监控信息
	R addSecurityInformation(String reseauId, String functionId, Integer level, String remark, String itemLabel, String eventLabels, String picNames, String mendMan,Integer type);

	//查询巡查信息
	Map<Object, Object> querySafetyInspect(Integer current, Integer size, String date, String isNotarize, String startDate, String endDate, String functionId, Integer level, Integer status, String ebSchoolId, String task, String reseauName,Integer type);

	//修改状态
	R alterSafetyInspectStatus(String inspectId, String status, String picNames);

	//获取app用户的设备标识
	Map<Object, Object> getRegistrationId(String registrationId);

	//app用户退出时删除设备标识
	Map<Object, Object> deleteRegistrationId();

	//巡查 消防 隐患未处理条数
	Map<String,Object> recordNumber();
	//巡查，消防，隐患记录详情
	PageBean recordDetails(Integer currentPage, Integer size, Integer type, String userId, String rectification, Integer status);
	//整改任务
	PageBean rectificationTask(Integer current, Integer size, Integer type);
}
