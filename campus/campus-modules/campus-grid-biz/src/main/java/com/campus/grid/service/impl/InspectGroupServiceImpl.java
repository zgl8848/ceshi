package com.campus.grid.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.UserDTO;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.InspectGroupDTO;
import com.campus.grid.api.entity.GroupMember;
import com.campus.grid.api.entity.InspectGroup;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.mapper.InspectGroupMapper;
import com.campus.grid.service.InspectGroupService;
import com.campus.grid.util.InspectHiddenUtils;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:54:50
 */
@Service("inspectGroupService")
@Transactional
public class InspectGroupServiceImpl extends ServiceImpl<InspectGroupMapper, InspectGroup> implements InspectGroupService {
	@Autowired
	private InspectGroupMapper inspectGroupMapper;

	@Override
	public List<UserDTO> getInspectors(String ebDeptId) {
		if(ebDeptId==null||"".equals(ebDeptId)) {
			ebDeptId=SecurityUtils.getUser().getDeptId();
		}
		return inspectGroupMapper.selectInspectors(ebDeptId);
	}


	@Override
	public boolean save(String groupId,String groupName, String leader, String remark, String groupMembers,String deptId) throws ParseException {
		String id=null;
		if (groupId!=null&&!"".equals(groupId)){
			id=groupId;
		}else {
			id = UUID.randomUUID().toString().replaceAll("-", "");
		}
		//吧小组 成员id拆分出来封装到集合中批量添加
		List<GroupMember> groupMemberList=new ArrayList<>();
		if (StringUtils.isNotBlank(groupMembers)){
			List<String> groupMember = InspectHiddenUtils.arrayToList(new ArrayList<String>(), groupMembers);
			for (String userId : groupMember) {
				groupMemberList.add(new GroupMember(id,userId,"0"));
			}
		}
		if ((StringUtils.isNotBlank(leader)))groupMemberList.add(new GroupMember(id,leader,"1"));
		if(deptId==null||"".equals(deptId)) {
			deptId=SecurityUtils.getUser().getDeptId();
		}
		InspectGroup inspectGroup=new InspectGroup(id,groupName,deptId,SecurityUtils.getUser().getId(),remark, 
				InspectHiddenUtils.date2LocalDateTime(new Date()),leader,groupMemberList.size(),"1");
		try {
			if (groupId!=null&&!"".equals(groupId)){
				SimpleDateFormat smt=new SimpleDateFormat("yyyy-MM-dd");
				List<InspectTask> listByDate=inspectGroupMapper.getTaskGroupByGroupId(groupId, smt.parse(smt.format(new Date())));
				if(listByDate!=null&&listByDate.size()>0) {//任务还未结束
					return false;
				}else {
					List<InspectTask> list=inspectGroupMapper.getTaskGroupByGroupId(groupId, null);
					if(list!=null&&list.size()>0) {//存在任务,先将原督察小组取消，再重新添加修改
						inspectGroupMapper.updateGroupStatus(groupId, "2");
						inspectGroup.setGroupId(UUID.randomUUID().toString().replaceAll("-", ""));
						inspectGroupMapper.insertInspectGroup(inspectGroup);
					}else {
						inspectGroupMapper.updateInspectGroup(inspectGroup);
						inspectGroupMapper.deleteGroupMembers(groupId);
					}
					if(groupMemberList.size()>0) {
						for (GroupMember member : groupMemberList) {
							member.setGroupId(inspectGroup.getGroupId());
						}
					}
				
				}
				
			}else {
				inspectGroupMapper.insertInspectGroup(inspectGroup);
			}
			if (groupMemberList.size()!=0) inspectGroupMapper.insertGroupMembers(groupMemberList);
			return true;
		}catch (RuntimeException r){
			r.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String groupId) throws ParseException {
		SimpleDateFormat smt=new SimpleDateFormat("yyyy-MM-dd");
		List<InspectTask> listByDate=inspectGroupMapper.getTaskGroupByGroupId(groupId, smt.parse(smt.format(new Date())));
		if(listByDate!=null&&listByDate.size()>0) {//任务还未结束
			return false;
		}else {
			List<InspectTask> list=inspectGroupMapper.getTaskGroupByGroupId(groupId, null);
			if(list!=null&&list.size()>0) {//存在任务,假删除
				inspectGroupMapper.updateGroupStatus(groupId, "2");
				return true;
			}else {
				try {
					inspectGroupMapper.deleteGroupMembers(groupId);
					inspectGroupMapper.deleteInspectGrou(groupId);
					return true;
				}catch (Exception e){
					e.printStackTrace();
					return false;
				}
			}
		}
		
		

	}


	@Override
	public Map<String, Object> getInspectGroup(Integer current, Integer size,String ebDeptId) {
		String deptId=null;
		if (StringUtils.isNotBlank(ebDeptId)){
			deptId=ebDeptId;
		}else {
			if (!"0".equals(SecurityUtils.getUser().getRoleType())){
				deptId=SecurityUtils.getUser().getDeptId();
			}
		}
	//	List<UserDTO> allUser = inspectGroupMapper.selectInspectors(deptId);
		List<InspectGroupDTO> inspectGroupDTOS = inspectGroupMapper.selectInspectGroup((current - 1) * size, size, deptId);
		for (InspectGroupDTO inspectGroupDTO : inspectGroupDTOS) {
			List<UserDTO> userDTOS = inspectGroupMapper.selectInspectorsOfGroupId(inspectGroupDTO.getGroupId());
			String groupMembers="";
			for (int i = 0; i < userDTOS.size(); i++) {
				if (i!=userDTOS.size()-1){
					groupMembers=groupMembers+userDTOS.get(i).getUsername()+",";
				}else{
					groupMembers=groupMembers+userDTOS.get(i).getUsername();
				}
			}
			inspectGroupDTO.setGroupMembers(groupMembers);
			inspectGroupDTO.setGroupMember(userDTOS);
		//	inspectGroupDTO.setUnderlingUser(InspectHiddenUtils.getUserDTO(allUser, userDTOS, inspectGroupDTO.getLeader()));
			inspectGroupDTO.setUnderlingUser(inspectGroupMapper.selectFilterInspectorsByGroupId(inspectGroupDTO.getDeptId(), inspectGroupDTO.getGroupId()));
		}
		Map<String, Object> map=new HashMap<>();
		map.put("records",inspectGroupDTOS);
		map.put("total",inspectGroupMapper.total(deptId));
		return map;
	}

	/**
	 * 判断该成员是否存在于督查小组中
	 * @param userId
	 * @return
	 */
	@Override
	public UserDTO getMemberByUserId(String userId){
		List<UserDTO> list = baseMapper.getMemberByUserId(userId);
		if (list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 *
	 * @param groupId
	 * @return
	 */
	@Override
	public List<String> getMemberByGroupId(String groupId){
		return baseMapper.getMemberByGroupId(groupId);
	}

}
