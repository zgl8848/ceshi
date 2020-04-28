package com.campus.grid.service.impl;

import com.campus.admin.api.dto.DeptDto;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.admin.api.feign.RemoteUserService;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.HiddenDangerDTO;
import com.campus.grid.api.dto.PictureDTO;
import com.campus.grid.api.entity.HiddenDanger;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.api.entity.JPush;
import com.campus.grid.api.vo.HiddenDangerVO;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.mapper.HiddenDangerMapper;
import com.campus.grid.mapper.SecurityPatrolMapper;
import com.campus.grid.service.HiddenDangerService;
import com.campus.grid.service.InspectTaskService;
import com.campus.grid.service.SchoolService;
import com.campus.grid.util.InspectHiddenUtils;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.HiddenDangerDTO;
import com.campus.grid.api.entity.*;
import com.campus.grid.service.HiddenDangerService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class HiddenDangerServiceImpl implements HiddenDangerService {
	@Autowired
	private SecurityPatrolMapper securityPatrolMapper;
	@Autowired
	private HiddenDangerMapper hiddenDangerMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Autowired
	private JPush jPush;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
    private RemoteUserService remoteUserService;

	private final InspectTaskService inspectTaskService;

	private final SchoolService schoolService;

	//隐患录页面加载信息
	@Override
	public Map<Object, Object> queryPageData() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String schoolId = SecurityUtils.getUser().getSchoolId();
		map.put("functionTypes", securityPatrolMapper.selectFunctionName("2"));
		map.put("processModes", hiddenDangerMapper.selectProcessMode());
		map.put("users",hiddenDangerMapper.selectUserName(schoolId));
		map.put("eventLabels", securityPatrolMapper.selectEventLabel());
		return map;
	}

	@Override
	public Map<Object, Object> queryGrid(String reseauId) {
		Map<Object, Object> map = new HashMap();
//		网格的功能he 负责人
		map.put("gridFunction", hiddenDangerMapper.findGridFunction(reseauId));
		map.put("trueName", hiddenDangerMapper.selectTrueName(reseauId));
		return map;
	}

	@Override
	public R addHiddenDanger(String itemLabel, String reseauId, String functionId, Integer level, Integer mode, String remark, String mendMan,String picNames,String eventLabels) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		String userId = SecurityUtils.getUser().getId();
		if (!StringUtils.isNotBlank(mendMan)){
			mendMan=userId;
		}
		LocalDateTime createTime = InspectHiddenUtils.date2LocalDateTime(new Date());
		String[] split1 = eventLabels.split(",");
		String neweventLabels = "";
		for (int i = 0; i < split1.length; i++) {
			String s = split1[i];
			neweventLabels = neweventLabels + s + " ";
		}
		remark = itemLabel + "  " + neweventLabels + "  " + remark;
//		把事件和物品品标签进行拆分重组，判断是否有图片插入
		try {
			if (picNames.length()!=0) securityPatrolMapper.insertPic(id,id,picNames,0,createTime);
			String schoolId = SecurityUtils.getUser().getSchoolId();
			HiddenDanger hiddenDanger = new HiddenDanger(id, schoolId, null,reseauId,null,userId, functionId, level, 1, mendMan, mode,null, null,remark, createTime,null,null);
			hiddenDangerMapper.insertHiddenDanger(hiddenDanger);
			String registrationId = (String)redisTemplate.opsForValue().get(mendMan);
			if (StringUtils.isNotBlank(registrationId)) InspectHiddenUtils.buildPushObjectAllAliasAalert(jPush.getMasterSecret(),jPush.getAppKey(),registrationId,"您有一条新的整改信息,请点击查看");
			return new R<>(null,"success");
		} catch (Exception e) {
			return new R<>(e);
		}
	}

	@Override
	public Map<Object, Object> queryHiddenDanger(Integer current, Integer size, String date, Integer level, String functionId, Integer mode,String title,String startDate,String endDate,Integer status,String ebSchoolId,String task,String reseauName,String type) {
		List<String> roleCodes = securityPatrolMapper.findRoleCode(SecurityUtils.getUser().getId());
		String roleCode="";
		boolean contains = roleCodes.contains("ROLE_SCHOOL");
		if (contains) roleCode="ROLE_SCHOOL";
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (!StringUtils.isNotBlank(ebSchoolId)) ebSchoolId=null;
		if (childDepts.size()==0)childDepts.add("notHave");
		Map<Object, Object> map=new HashMap<>();
		LocalDateTime startTime=InspectHiddenUtils.shellDate(date).get("startTime");
		LocalDateTime endTime=InspectHiddenUtils.shellDate(date).get("endTime");
//		判断搜索条件日期是否为空不为空则转换哈格式
		if (StringUtils.isNotBlank(startDate)&&StringUtils.isNotBlank(endDate)){
			startTime=InspectHiddenUtils.string2LocalDateTime(startDate);
			endTime=InspectHiddenUtils.string2LocalDateTime(endDate);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<HiddenDanger> hiddenDangers = hiddenDangerMapper.selectHiddenDanger((current - 1) * size,size,startTime,endTime,level,functionId,mode,title,SecurityUtils.getUser().getId(),roleType,status,childDepts,roleCode,SecurityUtils.getUser().getSchoolId(),ebSchoolId,task,reseauName,type);
//		吧查询出的数据进行封装成前台锁需要的数据
		List<HiddenDangerDTO> hiddenDangerDTOS=new ArrayList<>();
		for (HiddenDanger hiddenDanger : hiddenDangers) {
			HiddenDangerDTO hiddenDangerDTO=new HiddenDangerDTO(hiddenDanger);
			hiddenDangerDTO.setReportTime(simpleDateFormat.format(InspectHiddenUtils.localDateTime2Date(hiddenDanger.getCreateTime())));
			Date date1 = InspectHiddenUtils.localDateTime2Date(hiddenDanger.getUpdateTime());
			if (date1!=null)hiddenDangerDTO.setUpdateTime(simpleDateFormat.format(date1));
			hiddenDangerDTO.setPrincipal(hiddenDangerMapper.selectTrueName(hiddenDanger.getReseauId()));
			hiddenDangerDTO.setDeptName(securityPatrolMapper.findDeptName(hiddenDanger.getSchoolId()));
			String processedPics = securityPatrolMapper.selectPics(hiddenDanger.getInspectId(), 0);
//			查询每条数据中的图片
			List<String> processedPic=new ArrayList<>();
			if (processedPics!=null&&processedPics!="") processedPic=InspectHiddenUtils.arrayToList(processedPic,processedPics);
			String untreatedPics = securityPatrolMapper.selectPics(hiddenDanger.getInspectId(), 1);
			List<String> untreatedPic=new ArrayList<>();
			if (untreatedPics!=null&&untreatedPics!="") untreatedPic=InspectHiddenUtils.arrayToList(untreatedPic,untreatedPics);
			hiddenDangerDTO.setProcessedPic(processedPic);
			hiddenDangerDTO.setUntreatedPic(untreatedPic);
			hiddenDangerDTO.setGridFunctionDTO(securityPatrolMapper.findGridFunction(hiddenDanger.getReseauId()));
			hiddenDangerDTOS.add(hiddenDangerDTO);
		}
		Integer allTotal=null;
		if (task==null){
			allTotal = hiddenDangerMapper.findTotal(startTime,endTime,level,functionId,mode,title,SecurityUtils.getUser().getId(),roleType,status,childDepts,roleCode,SecurityUtils.getUser().getSchoolId(),ebSchoolId,reseauName,type);
		}
		map.put("records",hiddenDangerDTOS);
		map.put("total",allTotal);
		map.put("functionTypes",securityPatrolMapper.selectFunctionName("2"));
		map.put("processModeDTOS",hiddenDangerMapper.selectProcessMode());
		return map;
	}

	@Override
	public Map<Object, Object> hiddenDangerQuery(HiddenDangerVO hiddenDangerVO) {
//		获取参数
		Integer current = hiddenDangerVO.getCurrent();
		Integer size = hiddenDangerVO.getSize();
		String date = hiddenDangerVO.getDate();
		String ebSchoolId = hiddenDangerVO.getEbSchoolId();
        Integer level = hiddenDangerVO.getLevel();
        String functionId = hiddenDangerVO.getFunctionId();
        Integer mode = hiddenDangerVO.getMode();
        String title = hiddenDangerVO.getTitle();
        Integer status = hiddenDangerVO.getStatus();
        String reseauName = hiddenDangerVO.getReseauName();
        String type = hiddenDangerVO.getType();
        String task = hiddenDangerVO.getTask();

//      根据用户id获取该用户的角色
		List<String> roleCodes = securityPatrolMapper.findRoleCode(SecurityUtils.getUser().getId());
		String roleCode="";
		boolean contains = roleCodes.contains("ROLE_SCHOOL");
		if (contains) roleCode="ROLE_SCHOOL";

//		获取当前角色类型
		String roleType = SecurityUtils.getUser().getRoleType();
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (!StringUtils.isNotBlank(ebSchoolId)) ebSchoolId=null;
		if (childDepts.size()==0)childDepts.add("notHave");
		Map<Object, Object> map=new HashMap<>();

//		日期格式转换
		LocalDateTime startTime=InspectHiddenUtils.shellDate(date).get("startTime");
		LocalDateTime endTime=InspectHiddenUtils.shellDate(date).get("endTime");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<HiddenDanger> hiddenDangers = hiddenDangerMapper.selectHiddenDangerData((current - 1) * size,size,startTime,endTime,level,functionId,mode,title,SecurityUtils.getUser().getId(),roleType,status,childDepts,roleCode,SecurityUtils.getUser().getSchoolId(),ebSchoolId,task,reseauName);
//		把查询出的数据进行封装成前台所需要的数据
		List<HiddenDangerDTO> hiddenDangerDTOS=new ArrayList<>();
		for (HiddenDanger hiddenDanger : hiddenDangers) {
//          根据巡查人id和整改人id查询真实姓名
            TrueNameVO trueNameVO = remoteUserService.selectTrueName(hiddenDanger.getUserId(), hiddenDanger.getRectification());
            HiddenDangerDTO hiddenDangerDTO=new HiddenDangerDTO(hiddenDanger);
            hiddenDangerDTO.setSafetyOfficer(trueNameVO.getUserId());
            hiddenDangerDTO.setRectification(trueNameVO.getRectification());
			hiddenDangerDTO.setReportTime(simpleDateFormat.format(InspectHiddenUtils.localDateTime2Date(hiddenDanger.getCreateTime())));
			Date date1 = InspectHiddenUtils.localDateTime2Date(hiddenDanger.getUpdateTime());
			if (date1!=null)hiddenDangerDTO.setUpdateTime(simpleDateFormat.format(date1));
			hiddenDangerDTO.setPrincipal(hiddenDangerMapper.selectTrueNameByReseauId(hiddenDanger.getReseauId()));
            String deptId = hiddenDanger.getDeptId();
            if (deptId!=null&&deptId!=""){
                hiddenDangerDTO.setDeptName(remoteDeptService.getDeptNameById(deptId));
            }

//			查询每条数据中的图片
			List<PictureDTO> processedPics = securityPatrolMapper.selectAllPics(hiddenDanger.getInspectId());
            List<String> processedPic=null;    //存放未处理图片
            List<String> untreatedPic=null;    //存放已处理图片
            for (PictureDTO pictureDTO : processedPics){
			    if (pictureDTO.getPicStatus()!=null && pictureDTO.getPicNames()!=null && pictureDTO.getPicNames()!=""){
                    Integer picStatus = pictureDTO.getPicStatus();
                    String picNames = pictureDTO.getPicNames();
                    if (picStatus == 0){
                        processedPic=new ArrayList<>();
                        processedPic=InspectHiddenUtils.arrayToList(processedPic,picNames);
                    }
                    if (picStatus == 1){
                        untreatedPic=new ArrayList<>();
                        untreatedPic=InspectHiddenUtils.arrayToList(untreatedPic,picNames);
                    }
                }
            }
			hiddenDangerDTO.setProcessedPic(processedPic);
			hiddenDangerDTO.setUntreatedPic(untreatedPic);
			hiddenDangerDTO.setGridFunctionDTO(securityPatrolMapper.findGridFunction(hiddenDanger.getReseauId()));
			hiddenDangerDTOS.add(hiddenDangerDTO);
		}
		Integer allTotal=null;
		if (task==null){
			allTotal = hiddenDangerMapper.findTotal(startTime,endTime,level,functionId,mode,title,SecurityUtils.getUser().getId(),roleType,status,childDepts,roleCode,SecurityUtils.getUser().getSchoolId(),ebSchoolId,reseauName,type);
		}
		map.put("records",hiddenDangerDTOS);
		map.put("total",allTotal);
		map.put("functionTypes",securityPatrolMapper.selectFunctionName("2"));
		map.put("processModeDTOS",hiddenDangerMapper.selectProcessMode());
		return map;
	}

	/**
	 * 检查任务 隐患统计
	 * @param taskId
	 * @return
	 */
	@Override
	public Map<String, Object> statByTask(String taskId) {

		InspectTask inspectTask = inspectTaskService.selectTaskById(taskId);
		if (inspectTask==null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		List<HiddenDangerDTO> listLevel;
		List<HiddenDangerDTO> listType;
		List<HiddenDangerDTO> listSchool;
		Integer count;

		LocalDateTime startTime = InspectHiddenUtils.date2LocalDateTime(inspectTask.getStartDate());
		Date endDate=inspectTask.getEndDate();
		Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
		LocalDateTime endTime = InspectHiddenUtils.date2LocalDateTime(c.getTime());
		
		LocalDateTime newTime = InspectHiddenUtils.date2LocalDateTime(new Date());
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<String, List<String>> mapp = inspectTaskService.getUserRole(roleType);
		if (mapp==null || mapp.size()==0){
			return null;
		}
		List<String> schoolIds = mapp.get("schoolIds");
		List<String> childDepts = mapp.get("childDepts");

		listLevel = hiddenDangerMapper.statByLevel(startTime, endTime, roleType, schoolIds, childDepts,  taskId);
		listType = hiddenDangerMapper.statBySchoolType(startTime, endTime, roleType, schoolIds, childDepts, taskId);
		listSchool = hiddenDangerMapper.statBySchool(startTime, endTime, roleType, schoolIds, childDepts,  taskId);
		count = hiddenDangerMapper.statRectification(startTime, newTime, roleType, schoolIds, childDepts, taskId);

		map.put("task", inspectTask);
		map.put("dept", getCurrentDept());
		map.put("level", setStatMapLevel(listLevel));
		map.put("type", setStatMapType(listType));
		map.put("school", listSchool);
		map.put("rectification", count);

		return map;
	}

	/**
	 *
	 * 获取当前登录用户的部门信息
	 * @return
	 */
	private String getCurrentDept(){
		String currentDept = "";
		SchoolVO schoolVO = schoolService.findSchoolInfo();
		if (schoolVO != null){
			currentDept = schoolVO.getSchoolName();
		}else {
			String deptId = SecurityUtils.getUser().getDeptId();
			DeptDto deptDto = remoteDeptService.getById(deptId).getData();
			if (deptDto!=null){
				currentDept = deptDto.getName();
			}
		}
		return currentDept;
	}

	private Map<String, Integer> setStatMapLevel(List<HiddenDangerDTO> listLevel){
		if(listLevel!=null && listLevel.size()>0){
			Map<String, Integer> map = new HashMap<>();
			for (HiddenDangerDTO hiddenDangerDTO : listLevel){
				String level = hiddenDangerDTO.getLevel() + "";
				Integer count = hiddenDangerDTO.getCount();
				map.put(level, count);
			}
			String[] arg = {"1", "2", "3"}; //隐患严重级别
			for (String str : arg){
				if(!map.containsKey(str)){
					map.put(str, 0);
				}
			}
			return map;
		}else{
			return null;
		}
	}

	private Map<String, Integer> setStatMapType(List<HiddenDangerDTO> listType){
		if(listType!=null && listType.size()>0){
			Map<String, Integer> map = new HashMap<>();
			for (HiddenDangerDTO hiddenDangerDTO : listType){
				String type = hiddenDangerDTO.getSchoolType();
				Integer count = hiddenDangerDTO.getCount();
				map.put(type, count);
			}
			String[] arg = {"1", "2", "3", "4"}; //学校类型 1-幼儿园 2-小学 3-初中 4-高中
			for (String str : arg){
				if(!map.containsKey(str)){
					map.put(str, 0);
				}
			}
			return map;
		}else{
			return null;
		}
	}




}
