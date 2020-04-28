package com.campus.grid.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.entity.SysUser;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.TaskResultDTO;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.api.entity.TaskAudit;
import com.campus.grid.api.entity.TaskResult;
import com.campus.grid.mapper.TaskResultMapper;
import com.campus.grid.service.InspectGroupService;
import com.campus.grid.service.InspectTaskService;
import com.campus.grid.service.TaskAuditService;
import com.campus.grid.service.TaskResultService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author hhskj on 2019/9/25 11:10
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class TaskResultServiceImpl extends ServiceImpl<TaskResultMapper, TaskResult> implements TaskResultService {

	private final TaskAuditService taskAuditService;

	private final InspectGroupService inspectGroupService;
	
	private final InspectTaskService inspectTaskService;
		

	/**
	 * 查询任务结果信息
	 * 
	 * @param taskId   任务id
	 * @param schoolId 学校Id
	 * @return
	 */
	@Override
	public Map<String, Object> getResultBySchoolIdAndTaskId(@Param("taskId") String taskId,
			@Param("schoolId") String schoolId,@Param("type") String type) {
		if(type==null||"".equals(type)){
			type="1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String roleType = SecurityUtils.getUser().getRoleType();
		String role = "4";// 4不能做业务的用户
		if ("2".equals(roleType)) {// 学校用户
			String userId = SecurityUtils.getUser().getId();
			UserDTO userDTO = inspectGroupService.getMemberByUserId(userId);
			if (userDTO == null) {// 非督查小组成员则无权查看该信息
				return null;
			}
			String isleader = userDTO.getIsLeader();
			String schoolIdBy = SecurityUtils.getUser().getSchoolId();
			if ("1".equals(isleader)) {// 小组组长
				if (schoolIdBy != null && schoolIdBy.equals(schoolId)) {
					role = "1,2";
				} else {
					role = "2";
				}
			} else {
				if (schoolIdBy != null && schoolIdBy.equals(schoolId)) {
					role = "1";
				}
			}
		} else if ("1".equals(roleType)) {// 教育局用户
			role = "3";
		}
		InspectTask task= inspectTaskService.selectTaskById(taskId);
		Date endDate=task.getEndDate();
		Date nowDate=new Date();
		Calendar c = Calendar.getInstance();  
        c.setTime(endDate);  
        c.add(Calendar.DAY_OF_MONTH, 1); 
        Date endTime1=c.getTime();//结束时间+1		
        List<TaskResultDTO> list = baseMapper.getResultBySchoolIdAndTaskId(taskId, schoolId,type,role);    
		if (list != null && list.size() > 0) {
			String  projectId="";
			int num=0;
			for (TaskResultDTO result : list) {
				if("".equals(projectId)){
					 projectId = result.getProjectId();
					 num++;
					 result.setNum(num);
				}else {
					if(projectId.equals(result.getProjectId())) {
						num++;
						result.setNum(num);
					}else {
						 projectId = result.getProjectId();
						 num=1;
						 result.setNum(num);
					}		
				}				
				result.setRole(role);
				
				if(nowDate.after(endTime1)) {
					List<String> listStatus=baseMapper.getSchoolStatus(taskId,
							schoolId);
					//检查状态（0未检查、1自查不通过、2自查通过，3小组不通过，4小组通过、5督查不通过，6督查通过）
					if(listStatus!=null&&listStatus.size()>0) {
						String status=listStatus.get(0);
						if(role.equals("1")) {
							if("0".equals(status)||"1".equals(status)) {
								if("0".equals(result.getSchoolStatus())) {
									result.setSchoolStatus("2");
								}
							}
						}else if(role.equals("1,2")) {
							if("0".equals(status)||"1".equals(status)) {
								if("0".equals(result.getSchoolStatus())) {
									result.setSchoolStatus("2");
								}
							}else if("2".equals(status)||"3".equals(status)) {
								if("0".equals(result.getGroupStatus())) {
									result.setGroupStatus("2");
								}
							}
						}else if(role.equals("2")) {
							if("2".equals(status)||"3".equals(status)) {
								if("0".equals(result.getGroupStatus())) {
									result.setGroupStatus("2");
								}
							}
						}else if(role.equals("3")) {
							if("4".equals(status)||"5".equals(status)) {
								if("0".equals(result.getDeptStatus())) {
									result.setDeptStatus("2");;
								}
							}
						}
					}
		        }   
				
			}
		}
		map.put("tasks", list);
		map.put("role", role);
		return map;
	}

	/**
	 * 更新任务检查状态
	 * 
	 * @param taskResult
	 */
	@Override
	public void updateStatus(TaskResultDTO taskResult) {
		baseMapper.updateStatusByresult(taskResult);
	}

	@Override
	public boolean savetaskResults(List<TaskResultDTO> taskResults) {
		if (taskResults != null && taskResults.size() > 0) {
			SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = smt.format(new Date());
			String userName=SecurityUtils.getUser().getUsername();
			List<SysUser> sysUserList=baseMapper.getUserByUsername(userName);
			
			if(sysUserList!=null&&sysUserList.size()>0) {
				userName=sysUserList.get(0).getTrueName();
			}
			String remarkSchool = userName + "(负责人)已审核，";
			String remarkGroup = userName + "(小组长)已审核，";
			String remarkDept = userName + "(安全主任)已审核，";
			String ProjectName = "";
			boolean flagSchool = true;
			boolean flagGroup = true;
			boolean flagDept = true;
			String statusSchool="0";
			String statusGroup="0";
			String statusDept="0";
			for (TaskResultDTO taskResult : taskResults) {
				if ("1".equals(taskResult.getSchoolStatus())) {// 未通过
					if (!ProjectName.equals(taskResult.getProjectName())) {
						remarkSchool += taskResult.getProjectName() + "," + taskResult.getContent() + "未通过，";
					} else {
						remarkSchool += taskResult.getContent() + "未通过，";
					}
					flagSchool = false;
				}

				if ("1".equals(taskResult.getGroupStatus())) {// 未通过
					if (!ProjectName.equals(taskResult.getProjectName())) {
						remarkGroup += taskResult.getProjectName() + "," + taskResult.getContent() + "未通过，";
					} else {
						remarkGroup += taskResult.getContent() + "未通过，";
					}
					flagGroup = false;
				}

				if ("1".equals(taskResult.getDeptStatus())) {// 未通过
					if (!ProjectName.equals(taskResult.getProjectName())) {
						remarkDept += taskResult.getProjectName() + "," + taskResult.getContent() + "未通过，";
					} else {
						remarkDept += taskResult.getContent() + "未通过，";
					}
					flagDept = false;
				}
				baseMapper.updateStatusByresult(taskResult);
			}
			if (flagSchool) {
				remarkSchool += "检查项目全通过(" + time + ")";
				statusSchool="2";
			} else {
				statusSchool="1";
				remarkSchool = remarkSchool.substring(0, remarkSchool.length() - 1) + "(" + time + ")";
			}
			if (flagGroup) {
				statusGroup="4";
				remarkGroup += "检查项目全通过(" + time + ")";
			} else {
				statusGroup="3";
				remarkGroup = remarkGroup.substring(0, remarkGroup.length() - 1) + "(" + time + ")";
			}
			if (flagDept) {
				statusDept="6";
				remarkDept += "检查项目全通过(" + time + ")";
			} else {
				statusDept="5";
				remarkDept = remarkDept.substring(0, remarkDept.length() - 1) + "(" + time + ")";
			}

			TaskAudit adult = new TaskAudit();
			adult.setTaskId(taskResults.get(0).getTaskId());
			adult.setSchoolId(taskResults.get(0).getSchoolId());
			adult.setUserId(SecurityUtils.getUser().getId());
			String status="0";
			if ("1".equals(taskResults.get(0).getRole())) {
				adult.setRemark(remarkSchool);
				status=statusSchool;
			} else if ("2".equals(taskResults.get(0).getRole())) {
				adult.setRemark(remarkGroup);
				status=statusGroup;
			} else if ("3".equals(taskResults.get(0).getRole())) {
				adult.setRemark(remarkDept);
				status=statusDept;
			} else {
				List<String> listStatus=baseMapper.getSchoolStatus(taskResults.get(0).getTaskId(),
						taskResults.get(0).getSchoolId());
				if(listStatus!=null&&listStatus.size()>0) {
					if("0".equals(listStatus.get(0))||"1".equals(listStatus.get(0))) {//自查不通过
						adult.setRemark(remarkSchool);
						status=statusSchool;
					}else if("2".equals(listStatus.get(0))||"3".equals(listStatus.get(0))){//自查通过 或者小组不通过
						adult.setRemark(remarkGroup);
						status=statusGroup;
					}
				}			
			}
			taskAuditService.insertAudit(adult);
			baseMapper.updateSchoolStatus(taskResults.get(0).getTaskId(), taskResults.get(0).getSchoolId(), status);
		}
		return true;
	}

	/**
	 * 判断该检查标准是否已经在任务中
	 * @param projectId
	 * @return
	 */
	@Override
	public boolean getByProject(String projectId){
		List<String> taskIds = baseMapper.getByProject(projectId);
		if (null != taskIds && taskIds.size()>0) {
			return true;
		} else {
			return false;
		}
	}

}
