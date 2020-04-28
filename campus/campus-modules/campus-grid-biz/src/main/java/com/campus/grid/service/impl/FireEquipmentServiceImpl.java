package com.campus.grid.service.impl;

import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.FireEquipment;
import com.campus.grid.mapper.FireEquipmentMapper;
import com.campus.grid.service.FireEquipmentService;
import com.campus.grid.util.InspectHiddenUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
public class FireEquipmentServiceImpl implements FireEquipmentService {
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Autowired
	private FireEquipmentMapper fireEquipmentMapper;
	@Override
	public Map<Object, Object> queryPageData(Integer current, Integer size, String date, String ebSchoolId, String name) {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (!StringUtils.isNotBlank(ebSchoolId)) ebSchoolId=null;
		if (childDepts.size()==0) childDepts=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<Object, Object> map=new HashMap<>();
		LocalDateTime startTime=null;
		LocalDateTime endTime=null;
		if (StringUtils.isNotBlank(date)){
			String[] split = date.split(",");
			try{
				startTime=InspectHiddenUtils.date2LocalDateTime(formatter.parse(split[0]));
				endTime=InspectHiddenUtils.date2LocalDateTime(formatter.parse(split[1]));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		//查询所有消防器材数据
		List<FireEquipment> fireEquipments = fireEquipmentMapper.selectFireEquipments((current - 1) * size, size, startTime, endTime, name, schoolId, ebSchoolId, roleType, childDepts);
		map.put("records",fireEquipments);
		Integer total = fireEquipmentMapper.total(startTime, endTime, name, schoolId, ebSchoolId, roleType, childDepts);
		map.put("total",total);
		return map;
	}

	@Override
	public boolean updateFireEquipment(String id, String model, String typeName, String code, String remark, String name, Integer status,Date startTime,Date endTime) {
		if (new Date().compareTo(endTime)==-1 && status==1){
			return fireEquipmentMapper.updateFireEquipment(new FireEquipment(id,null,name,null,null,model,typeName,code,status,remark,InspectHiddenUtils.date2LocalDateTime(startTime),InspectHiddenUtils.date2LocalDateTime(endTime),null));
		} else{
			return false;
		}
	}

	@Override
	public boolean addFireEquipment(String model,String reseauId,String typeName,String code,String remark,String name,String date) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		LocalDateTime startTime=InspectHiddenUtils.shellDate(date).get("startTime");
		String[] split = date.split(",");
		LocalDateTime endTime=InspectHiddenUtils.getSpecifiedDayBefore(split[1]);
		FireEquipment fireEquipment=new FireEquipment(id,reseauId,name,SecurityUtils.getUser().getId(),SecurityUtils.getUser().getSchoolId(),model,typeName,code,1,remark,startTime,endTime,InspectHiddenUtils.date2LocalDateTime(new Date()));
		return fireEquipmentMapper.addFireEquipment(fireEquipment);
	}

	@Override
	public Map<String,Object> importFireEquipments(MultipartFile file) {
		Workbook work=null;
		List<FireEquipment> fireEquipments=null;
		List<String> l=null;
		String id = SecurityUtils.getUser().getId();
		String schoolId = SecurityUtils.getUser().getSchoolId();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map=new HashMap();
		try {
			work = new XSSFWorkbook(file.getInputStream());

			Sheet sheet=work.getSheet("Sheet1");
			fireEquipments=new ArrayList<FireEquipment>();
			l=new ArrayList<String>();
			for (int i = 3; i <= sheet.getLastRowNum(); i++) {
				FireEquipment fireEquipment=new FireEquipment();
				Row row=sheet.getRow(i);
				//器材名称
				if (StringUtils.isNotBlank(row.getCell(0).getStringCellValue())){
					fireEquipment.setName(row.getCell(0).getStringCellValue());
				}else {
					l.add(i+1+"");
					continue;
				}
				//器材类型名称
				if (row.getCell(1)!=null){
					fireEquipment.setModel(row.getCell(1).getStringCellValue());
				}else {
					l.add(i+1+"");
					continue;
				}
				//规格型号
				if (row.getCell(2)!=null){
					fireEquipment.setTypeName(row.getCell(2).getStringCellValue());
				}else {
					l.add(i+1+"");
					continue;
				}
				//设备code
				if (row.getCell(3)!=null){
					fireEquipment.setCode(row.getCell(3).getStringCellValue());
				}else{
					fireEquipment.setCode("");
				}
				//所属网格名称
				if (row.getCell(4)!=null){
					//查询是否有次网格
					String reseauId = fireEquipmentMapper.findReseauId(row.getCell(4).getStringCellValue(), schoolId);
					if (StringUtils.isNotBlank(reseauId)){
						fireEquipment.setReseauId(reseauId);
					}else {
						l.add(i+1+"");
						continue;
					}
				}else{
					l.add(i+1+"");
					continue;
				}
				//开始和结束时间
				try {
					if (row.getCell(5)	==null){
						fireEquipment.setStartTime(InspectHiddenUtils.date2LocalDateTime(new Date()));
					}else {
						fireEquipment.setStartTime(InspectHiddenUtils.date2LocalDateTime(formatter.parse(row.getCell(5).getStringCellValue())));
					}
					if (row.getCell(6)==null){
						l.add(i+1+"");
						continue;
					}else {
						Date parse = formatter.parse(row.getCell(6).getStringCellValue());
						if (new Date().compareTo(parse)==1){
							fireEquipment.setStatus(0);
						}else {
							fireEquipment.setStatus(1);
						}
						fireEquipment.setEndTime(InspectHiddenUtils.date2LocalDateTime(parse));
					}
				}catch (Exception e){
					l.add(i+1+"");
					continue;
				}
				//描述
				if (row.getCell(7)!=null){
					fireEquipment.setRemark(row.getCell(7).getStringCellValue());
				}else {
					fireEquipment.setCode("");
				}
				fireEquipment.setCreateTime(InspectHiddenUtils.date2LocalDateTime(new Date()));
				fireEquipment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				fireEquipment.setUserId(id);
				fireEquipment.setSchoolId(schoolId	);
				fireEquipments.add(fireEquipment);
			}
			if (fireEquipments.size()==0) throw new Exception();
			fireEquipmentMapper.insertFireEquipments(fireEquipments);
			map.put("isok",true);
			map.put("errorList",l);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isok",false);
			map.put("errorList",l);
			return map;
		}
	}
	//定时任务 修改过期状态
	@Scheduled(cron = "0 5 1 ? * *")
	public void updateStatus() {
		fireEquipmentMapper.updateStatus(InspectHiddenUtils.date2LocalDateTime(new Date()));
	}
}
