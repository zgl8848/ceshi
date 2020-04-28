package com.campus.grid.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.InspectGroupInfo;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.SchoolDTO;
import com.campus.grid.api.entity.InspectGroup;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.api.entity.TaskGroupR;
import com.campus.grid.api.entity.TaskProjectR;
import com.campus.grid.api.entity.TaskResult;
import com.campus.grid.mapper.InspectTaskMapper;
import com.campus.grid.service.InspectGroupService;
import com.campus.grid.service.InspectTaskService;
import com.campus.grid.util.InspectHiddenUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class InspectTaskServiceImpl extends ServiceImpl<InspectTaskMapper,InspectTask> implements InspectTaskService {
//	@Autowired
//	private InspectTaskMapper inspectTaskMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;

//	private final SecurityPatrolMapper securityPatrolMapper;

	private final InspectGroupService inspectGroupService;
	
	/*
	@Autowired
	private final HiddenDangerService hiddenService;*/

	@Override
	public boolean save(String taskId,String taskName, String projects, String remark, String groups, Date startTime, Date endTime) {
		List<String> projectIds = InspectHiddenUtils.arrayToList(new ArrayList<>(), projects);
		List<ProjectStandard> projectStandards = baseMapper.selectProjectStandards(projectIds);
		if(taskId==null||"".equals(taskId)) {
			taskId = UUID.randomUUID().toString().replaceAll("-", "");
			InspectTask inspectTask=new InspectTask(taskId,taskName, SecurityUtils.getUser().getId(),projectIds.size(),projectStandards.size(),startTime,endTime,remark,InspectHiddenUtils.date2LocalDateTime(new Date()));
			//插入检查任务
			baseMapper.insertInspectTask(inspectTask);	
			//批量插入任务与项目关系表
			List<TaskProjectR> taskProjectRS=new ArrayList<>();
			for (String projectId : projectIds) {
				TaskProjectR taskProjectR=new TaskProjectR(taskId,projectId);
				taskProjectRS.add(taskProjectR);
			}
			baseMapper.insertTaskProjecR(taskProjectRS);
			
		} else {
			Date nowDate=new Date();
			Calendar c = Calendar.getInstance();  
            c.setTime(endTime);  
            c.add(Calendar.DAY_OF_MONTH, 1); 
            Date endTime1=c.getTime();
			if(startTime.after(nowDate)) {//开始时间>当前时间
				InspectTask inspectTask=new InspectTask(taskId,taskName, SecurityUtils.getUser().getId(),projectIds.size(),projectStandards.size(),startTime,endTime,remark,InspectHiddenUtils.date2LocalDateTime(new Date()));
				baseMapper.updateInspectTask(inspectTask);
				baseMapper.deleteGroupRBytaskId(taskId);
			//	baseMapper.deleteProjectRBytaskId(taskId);
				baseMapper.deleteSchoolRBytaskId(taskId);
				baseMapper.deleteTaskResultBytaskId(taskId);
			}
			
			if(startTime.before(nowDate)&&endTime1.after(nowDate)) {//startTime<nowDate&endTime>nowDate
				//修改  只传新增的督察小组，执行插入方法。
			}
		}
		//批量插入任务与小组关系
		List<String> groupIds = InspectHiddenUtils.arrayToList(new ArrayList<>(), groups);
		List<String> groupIdsNew =new ArrayList<String>();
		List<TaskGroupR> taskGroupRs =new ArrayList<>();
		for (String groupId : groupIds) {
			TaskGroupR taskGroupR=new TaskGroupR(taskId,groupId,null);
			taskGroupR.setDeptId(baseMapper.findDeptId(groupId));
			List<String> listGroupR=baseMapper.getGroupRByGroupId(taskId, groupId);
			if(listGroupR!=null&&listGroupR.size()>0) {
				continue;
			}
			groupIdsNew.add(groupId);
			taskGroupRs.add(taskGroupR);
		}
		if(taskGroupRs.size()<=0) {
			return true;
		}
		baseMapper.insertTaskGroupR(taskGroupRs);
		//批量插入检查任务与学校关系表
		List<String> schoolIds = baseMapper.selectSchoolOfGroupId(groupIdsNew);
		List<TaskGroupR> taskSchoolRs =new ArrayList<>();
		for (String schoolId : schoolIds) {
			TaskGroupR taskGroupR=new TaskGroupR(taskId,schoolId,"0");
			taskSchoolRs.add(taskGroupR);
		}
		baseMapper.insertTaskSchoolR(taskSchoolRs);
		//
		List<TaskResult> taskResults=new ArrayList<>();
		for (String schoolId : schoolIds) {
			for (ProjectStandard projectStandard : projectStandards) {
				TaskResult result=new TaskResult(taskId,schoolId,projectStandard.getProjectId(),projectStandard.getStandardId(),"0","0","0");
				taskResults.add(result);
			}
		}
		if (taskResults.size()!=0){
			baseMapper.insertTaskResult(taskResults);
		}
		return true;
	}

	@Override
	public IPage taskParticulars(Page page, String taskId, String deptId) {
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<String, List<String>> map = getUserRole(roleType);
		if (map==null || map.size()==0){
			return null;
		}
		List<String> schoolIds = map.get("schoolIds");
		List<String> childDepts = map.get("childDepts");
		if (StringUtils.isNotBlank(deptId)){
			childDepts.add(deptId);
		}else {
			childDepts = map.get("childDepts");
		}
		return baseMapper.selectTaskSchoolR(page, taskId, schoolIds, childDepts,roleType);
	}

	/**
	 * 分页查询检查任务信息
	 *
	 * @param page    分页对象
	 * @return
	 */
	@Override
	public IPage getInspectTaskPage(Page page,String taskName,String reportTime1) {
		Date reportTime=null;
		if(reportTime1!=null&&!"".equals(reportTime1)) {
			SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				reportTime=mat.parse(reportTime1);
			} catch (ParseException e) {
				reportTime=null;
			}
		}
		if("".equals(taskName)) taskName=null;
		String roleType = SecurityUtils.getUser().getRoleType();
		if("2".equals(roleType)){//学校用户
			String userId = SecurityUtils.getUser().getId();
			UserDTO userDTO = inspectGroupService.getMemberByUserId(userId);
			if(userDTO==null){//非督查小组成员则无权查看该信息
				return null;
			}
			List<String> schoolIds = getSchoolIds(userDTO);
			return baseMapper.getInspectTaskPage(page,roleType, schoolIds, null,taskName,reportTime);
		}else if ("1".equals(roleType)){//教育局用户
			List<String> childDepts = remoteDeptService.listChildDepts().getData();
			if (childDepts.size()==0)childDepts.add("notHave");
			return baseMapper.getInspectTaskPage(page, roleType, null, childDepts, taskName,reportTime);
		}else{//系统管理员
			return baseMapper.getInspectTaskPage(page, roleType, null, null, taskName,reportTime);
		}
	}

	/**
	 * 统计任务检查的学校（学校类型）
	 *
	 * @param taskId       任务ID
	 * @return list
	 */
	@Override
	public Map<String, Integer> statSchoolByType(String taskId){
		String roleType = SecurityUtils.getUser().getRoleType();
		Map<String, Integer> map;
		List<SchoolDTO> list;
		Map<String, List<String>> mapp = getUserRole(roleType);
		if (mapp==null || mapp.size()==0){
			return null;
		}
		List<String> schoolIds = mapp.get("schoolIds");
		List<String> childDepts = mapp.get("childDepts");
		list = baseMapper.statSchoolByType(roleType, schoolIds, childDepts, taskId);
		map = setStatMapType(list);
		return map;

	}

	public Map<String, List<String>> getUserRole(String roleType){
		Map<String, List<String>> map = new HashMap<>();
		List<String> schoolIds = null;
		List<String> childDepts = null;
		if("2".equals(roleType)){//学校用户
			String userId = SecurityUtils.getUser().getId();
			UserDTO userDTO = inspectGroupService.getMemberByUserId(userId);
			if(userDTO==null){//非督查小组成员则无权查看该信息
				return null;
			}
			schoolIds = getSchoolIds(userDTO);
			map.put("schoolIds", schoolIds);
		}else if ("1".equals(roleType)){//教育局用户
			childDepts = remoteDeptService.listChildDepts().getData();
			if (childDepts.size()==0)childDepts.add("notHave");
			map.put("childDepts", childDepts);
		}
		return map;
	}

	/**
	 * 数据封装（获取权限内的学校ID）
	 * @param userDTO
	 * @return
	 */
	public List<String> getSchoolIds(UserDTO userDTO){
		List<String> schoolIds = new ArrayList<>();
		String isleader = userDTO.getIsLeader();
		if("1".equals(isleader)){//小组组长
			schoolIds = inspectGroupService.getMemberByGroupId(userDTO.getGroupId());
		}else if("0".equals(isleader)){
			String schoolId = SecurityUtils.getUser().getSchoolId();
			schoolIds.add(schoolId);
		}
		return schoolIds;
	}

	private Map<String, Integer> setStatMapType(List<SchoolDTO> listType){
		if(listType!=null && listType.size()>0){
			Map<String, Integer> map = new HashMap<>();
			for (SchoolDTO schoolDTO : listType){
				String type = schoolDTO.getSchoolType();
				Integer count = schoolDTO.getSchoolNumber();
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


	/**
	 * 通过ID查询任务信息
	 *
	 * @param id 任务ID
	 * @return 任务信息
	 */
	public InspectTask selectTaskById(String id){
		return baseMapper.getTaskById(id);
	}

	/**
	 * 判断该检查项目是否已经在任务中
	 * @param projectId
	 * @return
	 */
	@Override
	public boolean getByProject(String projectId){
		String taskId = baseMapper.getByProject(projectId);
		if (null != taskId && !"".equals(taskId)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Map<String, Object> selectTaskDetailById(String taskId) {
		Map<String,Object> map=new HashMap<String,Object>();
		InspectTask task =baseMapper.getTaskById(taskId);
		List<InspectGroupInfo> projects=baseMapper.getProjectByTaskId(taskId); 
		List<InspectGroup> groups=baseMapper.getGroupByTaskId(taskId);
		map.put("task", task);
		map.put("projects", projects);
		map.put("groups", groups);
		return map;
	}
	

}
