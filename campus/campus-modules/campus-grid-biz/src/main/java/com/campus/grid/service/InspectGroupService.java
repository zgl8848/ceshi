package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.dto.UserDTO;
import com.campus.grid.api.entity.InspectGroup;

import java.text.ParseException;
import java.util.*;
/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:54:50
 */
public interface InspectGroupService extends IService<InspectGroup> {
	List<UserDTO> getInspectors(String ebDeptId);	
	boolean save(String groupId,String groupName,String leader,String remark,String groupMembers,String deptId)throws ParseException ;
	boolean delete(String groupId)throws ParseException ;
	Map<String,Object> getInspectGroup(Integer current, Integer size,String ebDeptId);

	UserDTO getMemberByUserId(String userId);

	/**
	 * 根据小组ID 查询督查小组所有成员所属学校信息
	 * @param groupId
	 * @return
	 */
	List<String> getMemberByGroupId(String groupId);
}
