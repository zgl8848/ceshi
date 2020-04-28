package com.campus.grid.service.impl;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.FunctionType;
import com.campus.grid.api.entity.saftymanagement.*;
import com.campus.grid.mapper.SaftyManagementMapper;
import com.campus.grid.mapper.SaftySituationMapper;
import com.campus.grid.mapper.SecurityPatrolMapper;
import com.campus.grid.mapper.SupervisionMapper;
import com.campus.grid.service.SaftyManagementService;
import com.campus.grid.util.InspectHiddenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class SaftyManagementServiceImpl implements SaftyManagementService {
	@Autowired
	private SaftyManagementMapper saftyManagementMapper;
	@Autowired
	private SaftySituationMapper saftySituationMapper;
	@Autowired
	private SupervisionMapper supervisionMapper;
	@Autowired
	private SecurityPatrolMapper securityPatrolMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Override
	public Map<Object,Object> getManageMap(String date) {
		LocalDateTime firstDayOfMonth = InspectHiddenUtils.getFirstDayOfMonth(date);
		LocalDateTime lastDayOfMonth = InspectHiddenUtils.getLastDayOfMonth(date);
		Map<Object,Object> map=new HashMap<Object,Object>();
		String roleType = SecurityUtils.getUser().getRoleType();
		int i = Integer.parseInt(roleType);
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size()==0) childDepts=null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		String deptId = SecurityUtils.getUser().getDeptId();
		List<ManageMap> list=new ArrayList<ManageMap>();
		if (i==2){
			Integer total = saftyManagementMapper.findAllHiddenDangerOfAreaCode(deptId,firstDayOfMonth,lastDayOfMonth);
			ManageMap schoolHiddenDanger = saftyManagementMapper.findSchoolHiddenDanger(schoolId,firstDayOfMonth,lastDayOfMonth);
			String schoolAreCode = saftyManagementMapper.findSchoolAreCode(schoolId);
			schoolHiddenDanger.setTotal(total+"");
			String cityCodeOfAreaCode = saftyManagementMapper.findCityCodeOfAreaCode(schoolAreCode);
			String provinceCodeOfAreaCode = saftyManagementMapper.findProvinceCodeOfAreaCode(schoolAreCode);
			schoolHiddenDanger.setArea_code(schoolAreCode);
			list.add(schoolHiddenDanger);
			map.put("records",list);
			map.put("province_code",provinceCodeOfAreaCode);
			map.put("city_code",cityCodeOfAreaCode);
			return map;
		}else if (i==1){
			List<String> strings = saftyManagementMapper.selectCounty(childDepts);
			String cityCodeOfAreaCode=null;
			String provinceCodeOfAreaCode=null;
			for (String string : strings) {
				Integer allHiddenDangerOfAreaCode = saftyManagementMapper.findAllHiddenDangerOfAreaCode(string,firstDayOfMonth,lastDayOfMonth);
				Integer hiddenDangerOfAreaCode = saftyManagementMapper.findHiddenDangerOfAreaCode(string,firstDayOfMonth,lastDayOfMonth);
				cityCodeOfAreaCode = saftyManagementMapper.findCityCodeOfAreaCode(string);
				ManageMap manageMap=new ManageMap(allHiddenDangerOfAreaCode+"",hiddenDangerOfAreaCode+"",string);
				provinceCodeOfAreaCode = saftyManagementMapper.findProvinceCodeOfAreaCode(string);
				list.add(manageMap);
				map.put("records",list);
			}
			map.put("province_code",provinceCodeOfAreaCode);
			map.put("city_code",cityCodeOfAreaCode);
			return map;
		}else if (i==0){
			String cityCodeOfAreaCode=null;
			String provinceCodeOfAreaCode=null;
			List<String> strings = saftyManagementMapper.selecAlltCounty();
			for (String string : strings) {
				Integer allHiddenDangerOfAreaCode = saftyManagementMapper.findAllHiddenDangerOfAreaCode(string,firstDayOfMonth,lastDayOfMonth);
				Integer hiddenDangerOfAreaCode = saftyManagementMapper.findHiddenDangerOfAreaCode(string,firstDayOfMonth,lastDayOfMonth);
				cityCodeOfAreaCode = saftyManagementMapper.findCityCodeOfAreaCode(string);
				ManageMap manageMap=new ManageMap(allHiddenDangerOfAreaCode+"",hiddenDangerOfAreaCode+"",string);
				list.add(manageMap);
				provinceCodeOfAreaCode = saftyManagementMapper.findProvinceCodeOfAreaCode(string);
				map.put("records",list);
			}
			map.put("province_code",provinceCodeOfAreaCode);
			map.put("city_code",cityCodeOfAreaCode);
			return map;
		}
		return null;
	}

	@Override
	public ManageOa geOverAll() {
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size()==0)childDepts=null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		Integer total = saftySituationMapper.getAllCount(schoolId, 2, roleType, childDepts);
		Integer processing = supervisionMapper.getCount(schoolId, 2,roleType,childDepts);
		String completion_rate = InspectHiddenUtils.percentage(total, total - processing);
		ManageOa manageOa=new ManageOa(total+"",processing+"",total - processing+"",completion_rate);
		return manageOa;
	}

	@Override
	public Map<Object,Object> getClassify() {
		Map<Object,Object> map=new HashMap<Object,Object>();
		List<FunctionType> functionTypes = securityPatrolMapper.selectFunctionName("2");
		List<ManageDanger> list=new ArrayList<ManageDanger>();
		for (FunctionType functionType : functionTypes) {
			String roleType = SecurityUtils.getUser().getRoleType();
			List<String> childDepts = remoteDeptService.listChildDepts().getData();
			if (childDepts.size()==0) childDepts.add("notHave");
			String schoolId = SecurityUtils.getUser().getSchoolId();
			Integer count = saftyManagementMapper.getCount(schoolId, functionType.getFunctionId(), roleType, childDepts);
			ManageDanger manageDanger=new ManageDanger(count+"",functionType.getFunctionId(),functionType.getFunctionName());
			list.add(manageDanger);
		}

		ManageDanger manageDanger=new ManageDanger("6","3","后勤管理");
		list.add(manageDanger);

		manageDanger=new ManageDanger("8","4","德育管理");
		list.add(manageDanger);

		manageDanger=new ManageDanger("11","5","校车安全");
		list.add(manageDanger);

		manageDanger=new ManageDanger("15","6","食品安全");
		list.add(manageDanger);

		map.put("records",list);
		return map;
	}

	@Override
	public Map<Object,Object> getSource() {
		Map<Object,Object> map=new HashMap<Object,Object>();
		List<ManageSource> list=new ArrayList<ManageSource>();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();

		String schoolId = SecurityUtils.getUser().getSchoolId();
		if (childDepts.size()==0) childDepts=null;
		Integer hiddenDangerTotal = saftySituationMapper.getAllCount(schoolId, 2, SecurityUtils.getUser().getRoleType(), childDepts);
		ManageSource hiddenDanger=new ManageSource(hiddenDangerTotal+"","2","专项检查");
		list.add(hiddenDanger);
		Integer securityPatrolTotal = saftySituationMapper.getAllCount(schoolId, 1, SecurityUtils.getUser().getRoleType(), childDepts);
		ManageSource securityPatrol=new ManageSource(securityPatrolTotal+"","1","日常巡查");
		list.add(securityPatrol);
		map.put("records",list);
		return map;
	}

	@Override
	public Map<Object, Object> getTrendData() {

		Map<Object,Object> map=new HashMap<Object,Object>();
		List<ManageTrendData> list=new ArrayList<ManageTrendData>();

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date date=new Date();
		String format = sdf.format(date);
		String[] split = format.split("-");
		split[1]="-01";
		String minDate=split[0]+split[1];

		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size()==0) childDepts=null;
		String schoolId = SecurityUtils.getUser().getSchoolId();
		String maxDate=sdf.format(new Date());
		List<String> monthBetween = InspectHiddenUtils.getMonthBetween(minDate, maxDate);
		for (String s : monthBetween) {
			LocalDateTime endTime = InspectHiddenUtils.getLastDayOfMonth(s);
			LocalDateTime startTime = InspectHiddenUtils.getFirstDayOfMonth(s);
			Integer count = saftyManagementMapper.getHiddenDangerCountOfMonth(schoolId, roleType, childDepts, startTime, endTime);
			SimpleDateFormat sdf1=new SimpleDateFormat("MM月");
			Date parse = null;
			try {
				parse = sdf.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String format1 = sdf1.format(parse);
			ManageTrendData manageTrendData=new ManageTrendData(count+"",format1);
			list.add(manageTrendData);
		}
		map.put("records",list);
		return map;
	}
}
