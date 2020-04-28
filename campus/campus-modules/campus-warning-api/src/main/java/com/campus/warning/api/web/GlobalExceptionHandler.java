package com.campus.warning.api.web;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.warning.api.exception.ServiceException;
import com.campus.warning.api.util.JsonResult;
/**
 * @ControllerAdvice 注解修饰的类为一个控制层
 * 全局异常处理类,
 * @author Administrator
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @ExceptionHandler 注解修饰的方法为异常处理方法
     * @param e
     * @return
     */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult doHandleServiceException(
			ServiceException e) {
		//输出异常的的详细信息到控制台	
		e.printStackTrace();
		//封装异常基本信息
		return JsonResult.err(e.getMessage());
	}
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(
			RuntimeException e) {
		//输出异常的的详细信息到控制台
		e.printStackTrace();
		//封装异常基本信息
		return JsonResult.err(e.getMessage());
	}
	
}








