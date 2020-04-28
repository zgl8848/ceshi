package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.SysUser;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.FunctionType;
import com.campus.grid.api.entity.Reseau;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.api.entity.ReseauVo;
import com.campus.grid.mapper.ReseauMapper;
import com.campus.grid.mapper.ReseauUserMapper;
import com.campus.grid.service.ReseauService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@Service
@AllArgsConstructor
public class ReseauServiceImpl extends ServiceImpl<ReseauMapper, Reseau> implements ReseauService {
	private ReseauMapper reseauMapper;
	private ReseauUserMapper reseauUserMapper;

	@Override
	public void updateUserNameByUserId(String userId, String userName) {
		reseauMapper.updateUserNameByUserId(userId, userName);
	}

	@Override
	public String getReseauId(Reseau reseauVaule) {
		return reseauMapper.addReseauFlag(reseauVaule);
	}

	@Override
	public List<Reseau> selectReseauExportInfoBySchoolId(String schoolId) {
		List<Reseau> reseauList = reseauMapper.selectReseauExportInfoBySchoolId(schoolId);
		return reseauList;
	}

	@Override
	public void export(HttpServletRequest request, HttpServletResponse response) {
		//Resource resource = new ClassPathResource("static/网格导出模板.xlsx");
		//创建HSSFWorkbook对象(excel的文档对象)
		XSSFWorkbook xb = new XSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		XSSFSheet sheet = xb.createSheet("学校网格导出信息");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		int cellNo = 0;
		String[] title = {"网格类型名称", "平面布局名称", "建筑结构名称", "空间结构名称", "网格命名", "网格功能名称", "普通巡查人", "普通巡查方式", "消防巡查人", "消防巡查方式"};
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		row = sheet.createRow(rowNo);
		for (int i = 0; i < title.length; i++) {
			//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			cell = row.createCell(i);
			String s = title[i];
			//设置单元格内容
			cell.setCellValue(s);
		}
		String schoolId = SecurityUtils.getUser().getSchoolId();
		List<Reseau> reseauList = reseauMapper.selectReseauExportInfoBySchoolId(schoolId);
		//row.createCell(0).setCellValue("姓名");
		//创建单元格并设置单元格内容
		Map <Integer,String>typeMap = new HashMap<Integer,String>();
		Map <String,String>spaceTypeMap = new HashMap<String,String>();
		Map <Integer,String>inspectModeMap = new HashMap<Integer,String>();
		spaceTypeMap.put("1","房间");spaceTypeMap.put("2","走廊");spaceTypeMap.put("3","楼梯");spaceTypeMap.put("4","公共区域");
		typeMap.put(1,"楼房");typeMap.put(2,"平房");typeMap.put(3,"厅/馆");typeMap.put(4,"功能空地");typeMap.put(5,"绿地");
		inspectModeMap.put(1,"一日一巡");inspectModeMap.put(2,"三日一巡");inspectModeMap.put(3,"一周一巡");
		inspectModeMap.put(4,"两周一巡");	inspectModeMap.put(4,"一月一巡");
		for (int i = 0; i < reseauList.size(); i++) {
			row = sheet.createRow(i+1);

			if(reseauList.get(i).getType() != null){
				row.createCell(0).setCellValue(typeMap.get(reseauList.get(i).getType())); //网格类型名称1-楼房 2-平房 3-厅/馆 4-功能空地 5-绿地
			}
			if (!"".equals(reseauList.get(i).getPlaneName()) && null != reseauList.get(i).getPlaneName() ){
				row.createCell(1).setCellValue(reseauList.get(i).getPlaneName());//平面布局名称
			}
			if (!"".equals(reseauList.get(i).getBuildingName()) && null != reseauList.get(i).getBuildingName() ){
				row.createCell(2).setCellValue("第"+reseauList.get(i).getBuildingName()+"层");//建筑结构名称
			}
			String spaceType = reseauList.get(i).getSpaceType();        //空间结构类型  1-房间2-走廊3-楼梯4-公共区域
			if (!"".equals(spaceType) && null != spaceType ){
				row.createCell(3).setCellValue(spaceTypeMap.get(spaceType)+reseauList.get(i).getSpaceName());//空间结构名称
			}
			if (!"".equals(reseauList.get(i).getReseauName()) && null != reseauList.get(i).getReseauName() ){
				row.createCell(4).setCellValue(reseauList.get(i).getReseauName());//网格命名
			}
			if (!"".equals(reseauList.get(i).getFunctionName()) && null != reseauList.get(i).getFunctionName() ){
				row.createCell(5).setCellValue(reseauList.get(i).getFunctionName());//网格功能名称
			}

			String reseauId = reseauList.get(i).getReseauId();
			if(!"".equals(reseauId) && reseauId != null){
				List<ReseauUser> reseauUserList = reseauUserMapper.findReseauUserByReseauId(reseauId);
				for (ReseauUser reseauUser : reseauUserList) {
					Integer responsiblyType = reseauUser.getResponsiblyType();
					if (1 == responsiblyType){
						//普通巡查人
						String userName = reseauUser.getUserName();
						row.createCell(6).setCellValue(userName);
						Integer inspectMode = reseauUser.getInspectMode();
						//巡查方式1:一日一巡 2:三日一巡 3:一周一巡 4:一月一巡
						row.createCell(7).setCellValue(inspectModeMap.get(inspectMode));
					}else if(2 == responsiblyType){
						//消防巡查人
						String userName = reseauUser.getUserName();
						row.createCell(8).setCellValue(userName);
						Integer inspectMode = reseauUser.getInspectMode();
						row.createCell(9).setCellValue(inspectModeMap.get(inspectMode));
					}
				}
			}
		}

		String newName = "学校网格导出信息.xlsx";
		try {
			newName = new String(newName.getBytes("utf-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-Disposition", "attachment;fileName" + newName);
		try {
			xb.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserIsNull(String userId) {
		reseauMapper.updateUserIsNull(userId);
	}

	@Override
	public List<Reseau> selectReseauByUid(String userId) {
			return reseauMapper.selectReseauByUid(userId);
	}

	@Override
	public List<Reseau> selectReseauByUidAndType(String id, String responsiblyType) {
		List<Reseau> reseaus = reseauMapper.selectReseauByUidAndType(id, responsiblyType);
		ArrayList<Reseau> collect = reseaus.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Reseau::getReseauId))), ArrayList::new));
		return collect;
	}

	@Override
	public void updatePrimaryPrincipal(String reseauId, String userId,Integer responsiblyType) {
		if (userId != null && !"".equals(userId) && !"undefined".equals(userId)) {
			//userId为不空时调用此方法
			String userName = reseauUserMapper.getUserNameByUserId(userId);
			reseauUserMapper.updatePrimaryPrincipal(reseauId,userId,userName,responsiblyType);
			reseauMapper.updatePrimaryPrincipal(reseauId,1);
		} else {
			reseauUserMapper.updatePrimaryPrincipal(reseauId,null,null,responsiblyType);
			reseauMapper.updatePrimaryPrincipal(reseauId,0);
		}

	}


	@Override
	public List<SysUser> selectPrimaryPrincipal(String schoolId) {
		return reseauMapper.selectPrimaryPrincipal(schoolId);
	}

	@Override
	public List<String> findBuildingList(String planename) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		return reseauMapper.findBuildingList(planename, schoolId);

	}

	@Override
	public PageBean selectReseauPageBean(int currentPage, int pageSize, String planeName, int allot) {
		List<ReseauVo> reseauVoList = new ArrayList<ReseauVo>();
		String schoolId = getSchoolId();
		PageBean pageBean = new PageBean(currentPage, pageSize);
		int startIndex = pageBean.getStartIndex();
		pageBean.setPageNumber(currentPage);
		pageBean.setPageSize(pageSize);
		int totalRecord = reseauMapper.getTotalRecord(planeName, allot, schoolId);
		pageBean.setTotalRecord(totalRecord);
		pageBean.setTotalPage(pageBean.getTotalPage());
		List<Reseau> reseaus = reseauMapper.selectReseauPageBean(startIndex, pageSize, planeName, allot, schoolId);
		for (Reseau reseau : reseaus) {
			ReseauVo reseauVo = new ReseauVo();
			String reseauId = reseau.getReseauId();
			reseauVo.setReseauId(reseauId);
			reseauVo.setReseauName(reseau.getReseauName());
			reseauVo.setBuildingDelFlag(reseau.getBuildingDelFlag());
			reseauVo.setCreateTime(reseau.getCreateTime());
			reseauVo.setBuildingName(reseau.getBuildingName());
			reseauVo.setType(reseau.getType());
			reseauVo.setSpaceType(reseau.getSpaceType());
			reseauVo.setSpaceName(reseau.getSpaceName());
			reseauVo.setParentFunctionName(reseau.getParentFunctionName());
			reseauVo.setRemark(reseau.getRemark());
			reseauVo.setPlaneDelFlag(reseau.getPlaneDelFlag());
			reseauVo.setReseauAllot(reseau.getReseauAllot());
			reseauVo.setSchoolId(reseau.getSchoolId());
			reseauVo.setFunctionId(reseau.getFunctionId());
			reseauVo.setFunctionName(reseau.getFunctionName());
			String parentFunctionId = reseau.getParentFunctionId();
			reseauVo.setParentFunctionId(parentFunctionId);
			if (!"".equals(parentFunctionId) && parentFunctionId != null){
				String parentFunctionName = reseauMapper.getFunctionNameByFid(parentFunctionId);
				reseauVo.setParentFunctionName(parentFunctionName);
			}
			if (!"".equals(reseauId) && reseauId != null) {
				List<ReseauUser> reseauUserList = reseauUserMapper.findReseauUserByReseauId(reseauId);
				if (reseauUserList != null && reseauUserList.size() != 0) {
					for (ReseauUser reseauUser : reseauUserList) {
						int responsiblyType = reseauUser.getResponsiblyType();
						reseauVo.setResponsiblyType(responsiblyType);
						if (1 == responsiblyType) {
							reseauVo.setUserName(reseauUser.getUserName());
							reseauVo.setUserId(reseauUser.getUserId());
							reseauVo.setInspectMode(reseauUser.getInspectMode());
							reseauVo.setInspectStartDate(reseauUser.getInspectStartDate());
							String format = reseauUser.getInspectStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							reseauVo.setStrInspectStartDate(format);
						} else if (2 == responsiblyType) {
							reseauVo.setFireUserName(reseauUser.getUserName());
							reseauVo.setFireUserId(reseauUser.getUserId());
							reseauVo.setFireInspectMode(reseauUser.getInspectMode());
							reseauVo.setFireInspectStartDate(reseauUser.getInspectStartDate());
							String format = reseauUser.getInspectStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							reseauVo.setStrFireInspectStartDate(format);
						}
					}
				}
			}
			reseauVoList.add(reseauVo);

		/*	if (!"".equals(reseau.getUserId()) && reseau.getUserId() != null) {
				String username = reseauMapper.findUserName(reseau.getUserId());
				if (username != null && !"".equals(username)) {
					reseauMapper.updateUserNameByReseauId(reseau.getReseauId(), username);
				}
				reseau.setUserName(username);
			}

			if ("".equals(reseau.getFunctionName()) || reseau.getFunctionName() == null) {
				String functionId = reseau.getFunctionId();
				if (functionId != null && !"".equals(functionId)) {
					String functionName = reseauMapper.getFunctionNameByFid(functionId);
					if (functionName != null && !"".equals(functionName)) {
						reseau.setFunctionName(functionName);
					}
				}
			}
			if ("".equals(reseau.getParentFunctionName()) || reseau.getParentFunctionName() == null) {
				String functionId = reseau.getFunctionId();
				if (functionId != null && !"".equals(functionId)) {
					String parentFunctionName = reseauMapper.getParentFunctionNameByFid(functionId);
					if (parentFunctionName != null && !"".equals(parentFunctionName)) {
						reseau.setParentFunctionName(parentFunctionName);
					}
				}
			}*/
		}
		pageBean.setData(reseauVoList);
		return pageBean;
	}


	@Override
	public void editReseau(ReseauVo reseauVo) {
		//-------------------修改网格--------------------------
		String functionName = reseauMapper.getFunctionNameByFid(reseauVo.getFunctionId());
		reseauVo.setFunctionName(functionName);
		reseauMapper.editReseau(reseauVo);

	}

/*
	@Override
	public void updatePlaneName(String planeName) {
		reseauMapper.updatePlaneName(planeName);
	}
*/

	@Override
	public Map<String, String> selectParentGridFunction() {
		List<FunctionType> list = reseauMapper.selectParentGridFunction();
		HashMap<String, String> map = new HashMap<>();
		for (FunctionType functionType : list) {
			String parentFunctionId = functionType.getFunctionId();
			String parentFunctionName = functionType.getFunctionName();
			map.put(parentFunctionId, parentFunctionName);
		}
		return map;
	}


	@Override
	public Map<String, String> selectChildrenGridFunction(String parentId) {
		List<FunctionType> list = reseauMapper.selectChildrenGridFunction(parentId);
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getFunctionId(), list.get(i).getFunctionName());
		}
		return map;
	}


	@Override
	public String isHaveReseauName(Reseau reseauVaule) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		reseauVaule.setSchoolId(schoolId);
		// 将spaceName 变为正确格式
		String regex = "[^0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(reseauVaule.getSpaceName());
		reseauVaule.setSpaceName(m.replaceAll("").trim());
		// 查询reseaId并返回
		String reseaId = reseauMapper.addReseauFlag(reseauVaule);
		return reseaId;
	}

	@Override
	public String getSchoolId() {
		// 获取登录用户的对象信息
		String schoolId = SecurityUtils.getUser().getSchoolId();
		if (StringUtils.isBlank(schoolId)) {
			return null;
		}
		return schoolId;
	}

	@Override
	public List<Reseau> findReseauName(String spacename) {
		//将条件切割分为 平面名称以及建筑结构名称 例如:  教学楼>第1层  0 教学楼  1 第1层
		String[] split = spacename.split("-第");
		String[] ceng = split[1].split("层-");
		//将第1层中的1取出
	/*	String regex = "[^-0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(split[1]);
		String spaceName = m.replaceAll("").trim();*/
		String spaceName = ceng[0];
		String schoolId = getSchoolId();
		List<Reseau> reseauName = reseauMapper.findReseauName(split[0], spaceName, ceng[1], schoolId);
//		对集合进行排序(顺序)
		Collections.sort(reseauName, (o1, o2) -> {
			if (o1.getSpaceType().equals(o2.getSpaceType())) {
				return Integer.parseInt(o1.getSpaceName()) - Integer.parseInt(o2.getSpaceName());
			} else {
				return 0;
			}
		});
		return reseauName;
	}

	@Override
	public boolean delReseau(String reseauid) {
		return reseauMapper.delReseau(reseauid);
	}

	@Override
	public boolean delPlaneName(String planename) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		String[] planeNameAndType = planename.split(",");
		return reseauMapper.delPlaneName(planeNameAndType[0], planeNameAndType[1], schoolId);
	}

	@Override
	public boolean delBuilddingName(String builddingname) {
//		教学楼-第-1层,1  按需截取
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		//获取type
		String[] planeNameAndTypeAndbuliddingName = builddingname.split("层,");
		//获取planename
		String[] planeNameAndbuliddingName = planeNameAndTypeAndbuliddingName[0].split("-第");
		return reseauMapper.delBuilddingName(planeNameAndbuliddingName[1], planeNameAndbuliddingName[0], planeNameAndTypeAndbuliddingName[1], schoolId);
	}

	@Override
	public boolean isHavePlaneName(String planeName) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		String[] planeNameSplit = planeName.split("-");
		String havePlaneName = reseauMapper.isHavePlaneName(planeNameSplit[0], planeNameSplit[1], schoolId);
		if (StringUtils.isNotBlank(havePlaneName)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isHaveBuilddingName(String builddingName) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		String[] builddingNameSplit = builddingName.split("-");
		String havebuilddingName = reseauMapper.isHaveBuilddingName(builddingNameSplit[0], builddingNameSplit[1], builddingNameSplit[2], schoolId);
		if (StringUtils.isNotBlank(havebuilddingName)) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	public String creatReseau(Reseau reseauVaule) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
//		设置网格创建时间
		reseauVaule.setCreateTime(System.currentTimeMillis());
//		将spaceName 变为正确格式
		String regex = "[^0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(reseauVaule.getSpaceName());
		reseauVaule.setSpaceName(m.replaceAll("").trim());
		reseauVaule.setSchoolId(schoolId);
		//先查询数据是否存在
		String flag = reseauMapper.addReseauFlag(reseauVaule);
		if (StringUtils.isNotBlank(flag)) {
			reseauVaule.setReseauId(flag);
			reseauMapper.creatReseau(reseauVaule);
			return flag;
		}
		reseauMapper.creatReseau(reseauVaule);
//		添加网格的同时向 网格负责人表添加相应信息
		LocalDate today = LocalDate.now();
		ReseauUser reseauUser = new ReseauUser(null,reseauVaule.getReseauId(),null,1,2,null,today);
		reseauUserMapper.createReseauUser(reseauUser);
		ReseauUser reseauUser2= new ReseauUser(null,reseauVaule.getReseauId(),null,2,2,null,today);
		reseauUserMapper.createReseauUser(reseauUser2);
		return reseauMapper.addReseauFlag(reseauVaule);
	}


	@Override
	public List<String> findSpaceNameList(String spacename) {
		// 获取登录用户的对象信息
		String schoolId = getSchoolId();
		String[] split = spacename.split("-");
		List<String> spaceNameList = reseauMapper.findSpaceNameList(split[0], split[1], schoolId);
		Collections.sort(spaceNameList);
		return spaceNameList;
	}

	public Reseau getReseau(String schoolId, String reseauId, String userId){
		return baseMapper.getReseau(schoolId,reseauId, userId);
	}
}
