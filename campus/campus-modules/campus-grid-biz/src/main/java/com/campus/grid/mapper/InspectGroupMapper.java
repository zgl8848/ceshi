package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.dto.UserDTO;
import com.campus.grid.api.dto.InspectGroupDTO;
import com.campus.grid.api.entity.GroupMember;
import com.campus.grid.api.entity.InspectGroup;
import com.campus.grid.api.entity.InspectTask;

import org.apache.ibatis.annotations.Param;

import java.util.*;
/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:54:50
 */
public interface InspectGroupMapper extends BaseMapper<InspectGroup> {
	List<UserDTO> selectInspectors(String deptId);
	boolean insertInspectGroup(InspectGroup inspectGroup);
	void insertGroupMembers(List<GroupMember> groupMemberList);
	boolean deleteInspectGrou(String groupId);
	boolean deleteGroupMembers(String groupId);
	void updateInspectGroup(InspectGroup inspectGroup);
	List<InspectGroupDTO> selectInspectGroup(@Param("startSize") Integer startSize, @Param("endSize") Integer endSize,@Param("depeId") String deptId);
	List<UserDTO> selectInspectorsOfGroupId(String groupId);
	Integer total(@Param("depeId") String deptId);

	/**
	 * 根据用户ID 获取督查小组成员信息
	 * @param userId
	 * @return
	 */
	List<UserDTO> getMemberByUserId(String userId);

	/**
	 * 根据小组ID 获取督查小组所有成员所在学校
	 * @param groupId
	 * @return
	 */
	List<String> getMemberByGroupId(String groupId);
	
	/**
	 * 根据小组id过滤已存在的学校成员
	 * @param deptId
	 * @param groupId
	 * @return
	 */
	List<UserDTO> selectFilterInspectorsByGroupId(@Param("deptId") String deptId,@Param("groupId") String groupId);
	
	
	/**
	 * 1若传时间根据小组id获取还未结束的任务；2判断该督察小组是否有任务
	 * @param groupId
	 * @param nowDate
	 * @return
	 */
	List<InspectTask> getTaskGroupByGroupId(@Param("groupId") String groupId,@Param("nowDate") Date nowDate);
	
	/**
	 * 更新状态
	 * @param groupId
	 */
	void updateGroupStatus(@Param("groupId") String groupId,@Param("status") String status);
	
}

