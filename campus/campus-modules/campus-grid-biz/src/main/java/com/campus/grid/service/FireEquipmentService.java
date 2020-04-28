package com.campus.grid.service;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.FireEquipment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

public interface FireEquipmentService {
	Map<Object,Object> queryPageData(Integer current, Integer size, String date, String ebSchoolId , String name);
	boolean updateFireEquipment(String id, String model, String typeName, String code, String remark, String name, Integer status, Date startTime, Date endTime);
	boolean addFireEquipment(String model,String reseauId,String typeName,String code,String remark,String name,String date);
	Map<String,Object> importFireEquipments(MultipartFile file);
}
