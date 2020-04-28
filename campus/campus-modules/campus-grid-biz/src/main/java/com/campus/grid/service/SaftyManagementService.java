package com.campus.grid.service;

import com.campus.grid.api.entity.saftymanagement.ManageOa;

import java.util.Map;


public interface SaftyManagementService {
	Map<Object, Object> getManageMap(String data);

	ManageOa geOverAll();

	Map<Object, Object> getClassify();

	Map<Object, Object> getSource();

	Map<Object, Object> getTrendData();
}
