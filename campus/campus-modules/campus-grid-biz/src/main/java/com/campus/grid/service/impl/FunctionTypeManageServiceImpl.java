package com.campus.grid.service.impl;

import com.campus.grid.api.dto.FunctionTypeDTO;
import com.campus.grid.api.dto.FunctionTypeManageDTO;
import com.campus.grid.api.entity.FunctionType;
import com.campus.grid.mapper.FunctionTypeManageMapper;
import com.campus.grid.mapper.SecurityPatrolMapper;
import com.campus.grid.service.FunctionTypeManageService;
import com.campus.grid.util.InspectHiddenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class FunctionTypeManageServiceImpl implements FunctionTypeManageService {
	@Autowired
	private SecurityPatrolMapper securityPatrolMapper;
	@Autowired
	private FunctionTypeManageMapper functionTypeManageMapper;

	@Override
	public Map<String, List<FunctionTypeDTO>> FunctionTypeManageService() {
		//		获取所有的问题类型
		List<FunctionTypeDTO> list=new ArrayList<FunctionTypeDTO>();
		Map<String, List<FunctionTypeDTO>> map=new ConcurrentHashMap<>();
		List<FunctionType> functionNames = securityPatrolMapper.selectFunctionName("2");
		for (FunctionType function : functionNames) {
			List<FunctionTypeDTO> functionChildren = functionTypeManageMapper.getFunctionChildren(function.getFunctionId());
			FunctionTypeDTO functionTypeDTO=new FunctionTypeDTO(function.getFunctionId(),function.getFunctionName(),null,null,functionChildren);
			list.add(functionTypeDTO);
		}
		map.put("records",list);
		return map;
	}

	@Override
	public Map<String, Object> getList(String functionId) {
		Map<String, Object> map=new ConcurrentHashMap<>();
		String isParent=null;
		if (!StringUtils.isNotBlank(functionId)){
			functionId=null;
		}else {
			String parentIdOfFunction = functionTypeManageMapper.getParentIdOfFunction(functionId);
			if ("0".equals(parentIdOfFunction)){
				isParent="";
			}
		}
		List<FunctionTypeManageDTO> list=new ArrayList<>();
		List<FunctionTypeManageDTO> AllList = functionTypeManageMapper.getList(isParent,functionId);
		for (FunctionTypeManageDTO functionTypeManageDTO : AllList) {
			if (functionTypeManageDTO.getValue()!=null){
				String[] split = functionTypeManageDTO.getValue().split(",");
				for (String s : split) {
					FunctionTypeManageDTO functionTypeManageDTO1=new FunctionTypeManageDTO(s,functionTypeManageDTO.getParentId(),functionTypeManageDTO.getParentValue(),functionTypeManageDTO.getGrandParentId(),functionTypeManageDTO.getGrandParentValue());
					list.add(functionTypeManageDTO1);
				}
			}
		}
		Integer total=0;
		List<FunctionTypeManageDTO> list1 = functionTypeManageMapper.getList(isParent, functionId);
		for (FunctionTypeManageDTO functionTypeManageDTO : list1) {
			if (functionTypeManageDTO.getValue()!=null) {
				String[] split = functionTypeManageDTO.getValue().split(",");
				total += split.length;
			}
		}
		map.put("records",list);
		map.put("total",total);
		return map;
	}

	@Override
	public Map<String, Object> deleteFunction(String functionId, String value,String status,String newValues) {
		Map<String, Object> map=new HashMap<>();
		try {
			String functionLabel = functionTypeManageMapper.findFunctionLabel(functionId);
			List<String> list=new ArrayList<>();
			list = InspectHiddenUtils.arrayToList(list, functionLabel);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(value)){
					list.remove(i);
				}
			}
			if ("1".equals(status)){
				list.add(newValues);
			}
			String s = InspectHiddenUtils.listToString(list);

			functionTypeManageMapper.setChildrenOfFunctionType(functionId,s);
			map.put("msg","ok");
			return map;
		}catch (Exception e){
			map.put("msg","error");
			return map;
		}
	}

	@Override
	public Map<String, Object> addFunction(String functionId, String functionName) {
		Map<String, Object> map=new HashMap<>();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		LocalDateTime time=InspectHiddenUtils.date2LocalDateTime(new Date());
		if ("".equals(functionId)){
			try {
				functionTypeManageMapper.insertFunction(id,functionName,time);
				map.put("msg","ok");
				return map;
			}catch (Exception e){
				map.put("msg","error");
				return map;
			}
		}
		try{
			String parentIdOfFunction = functionTypeManageMapper.getParentIdOfFunction(functionId);
			if ("0".equals(parentIdOfFunction)){
				functionTypeManageMapper.insertFunctionLabel(id,functionId,functionName,time);
			}else {
				String functionLabel = functionTypeManageMapper.findFunctionLabel(functionId);
				List<String> list=new ArrayList<>();
				if (functionLabel!=null){
					list = InspectHiddenUtils.arrayToList(list, functionLabel);
				}
				list.add(functionName);
				String s = InspectHiddenUtils.listToString(list);
				functionTypeManageMapper.setChildrenOfFunctionType(functionId,s);
			}
			map.put("msg","ok");
			return map;
		}catch (Exception e){
			map.put("msg","error");
			return map;
		}
	}

	@Override
	public Map<String, Object> updateFunction(String functionId, String functionName) {
		Map<String, Object> map=new HashMap<>();
		try {
			functionTypeManageMapper.updateFunctionName(functionId,functionName);
			map.put("msg","ok");
			return map;
		}catch (Exception e){
			map.put("msg","errot");
			return map;
		}
	}

	@Override
	public Map<String, Object> delFunction(String functionId) {
		Map<String, Object> map=new HashMap<>();
		String parentIdOfFunction = functionTypeManageMapper.getParentIdOfFunction(functionId);
		if ("0".equals(parentIdOfFunction)){
			List<String> strings = functionTypeManageMapper.selectFunctionLabel(functionId);
			strings.add(functionId);
			functionTypeManageMapper.delFunctionLabel(strings);
		}else {
			functionTypeManageMapper.delFunction(functionId);
		}
		map.put("msg","ok");
		return map;
	}
}
