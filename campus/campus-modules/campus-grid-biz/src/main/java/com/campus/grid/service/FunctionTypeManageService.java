package com.campus.grid.service;

import com.campus.grid.api.dto.FunctionTypeDTO;

import java.util.List;
import java.util.Map;

public interface FunctionTypeManageService {
	Map<String, List<FunctionTypeDTO>> FunctionTypeManageService();

	Map<String, Object> getList(String functionId);

	Map<String, Object> deleteFunction(String functionId, String value, String status, String newValues);

	Map<String, Object> addFunction(String functionId, String functionName);

	Map<String, Object> updateFunction(String functionId, String functionName);

	Map<String, Object> delFunction(String functionId);

}
