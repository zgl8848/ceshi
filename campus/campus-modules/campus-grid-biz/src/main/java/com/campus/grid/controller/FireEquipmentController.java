package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.FireEquipment;
import com.campus.grid.service.FireEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/fireEquipment")
public class FireEquipmentController {
	@Autowired
	private FireEquipmentService fireEquipmentService;
	/**
	 * 修改数据
	 */
	@RequestMapping("/updateFireEquipment")
	public R updateFireEquipment(String id,String model, String typeName, String code, String remark, String name, Integer status, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endTime){
		return new R <>(fireEquipmentService.updateFireEquipment(id,model,typeName,code,remark,name,status,startTime,endTime));
	}
	/**
	 * 添加数据
	 */
	@RequestMapping("/addFireEquipment")
	public R addFireEquipment(String model,String reseauId,String typeName,String code,String remark,String name,String date){
		return new R <>(fireEquipmentService.addFireEquipment(model,reseauId,typeName,code,remark,name,date));
	}
	/**
	 * 批量导入
	 */
	@RequestMapping("/importFireEquipments")
	public R importFireEquipments(MultipartFile file){
		return  new R <>(fireEquipmentService.importFireEquipments(file));
	}
	/**
	 * 页面数据
	 */
	@RequestMapping("/pageData")
	public R pageData(Integer current, Integer size, String date, String ebSchoolId , String name){
		return new R<>(fireEquipmentService.queryPageData(current,size,date,ebSchoolId,name));
	}
}
