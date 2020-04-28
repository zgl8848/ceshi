package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.service.FunctionTypeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/functionTypeManage")
public class FunctionTypeManageController {
	@Autowired
	FunctionTypeManageService functionTypeManageService;

	@RequestMapping("/getFunctionTypeManageTree")
	public R getFunctionTypeManageTree() {
		return new R<>(functionTypeManageService.FunctionTypeManageService());
	}

	@RequestMapping("/getList")
	public R getList(String functionId) {
		return new R<>(functionTypeManageService.getList(functionId));
	}

	@RequestMapping("/updateeFunction")
	public R deleteFunction(String functionId, String value, String status, String newValues) {
		return new R<>(functionTypeManageService.deleteFunction(functionId, value, status, newValues));
	}

	@RequestMapping("/addFunction")
	public R addFunction(String functionId, String functionName) {
		return new R<>(functionTypeManageService.addFunction(functionId, functionName));
	}

	@RequestMapping("/updateFunction")
	public R updateFunction(String functionId, String functionName) {
		return new R<>(functionTypeManageService.updateFunction(functionId, functionName));
	}

	@RequestMapping("/delFunction")
	public R delFunction(String functionId) {
		return new R<>(functionTypeManageService.delFunction(functionId));
	}
}
