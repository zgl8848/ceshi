package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.service.InspectGroupService;
import lombok.AllArgsConstructor;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:54:50
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inspectgroup")
public class InspectGroupController {

	@Autowired
  	private  InspectGroupService inspectGroupService;

	/**
	 * 查询所属督查人员
	 * @return
	 */
  	@RequestMapping("/getInspectors")
	public R getInspectors(@RequestParam(required = false) String eDeptId){
  		return new R<>(inspectGroupService.getInspectors(eDeptId));
	}

	/**
	 * 添加督察小组
	 * @param groupName 小组名称
	 * @param leader 组长id
	 * @param remark 描述
	 * @param groupMembers 组员id集合
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/save")
	public R save(String groupId,String groupName,String leader,String remark,String groupMembers,String depId) throws ParseException{
		return new R<>(inspectGroupService.save(groupId,groupName,leader,remark,groupMembers,depId));
	}

	/**
	 * 督查小组删除
	 * @param groupId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/delete")
	public R delete(String groupId) throws ParseException{
		return new R<>(inspectGroupService.delete(groupId));
	}

	/**
	 * 督查小组查询
	 * @param current 当前页数
	 * @param size 每页条数
	 * @return
	 */
	@RequestMapping("/getInspectGroup")
	public R getInspectGroup(Integer current, Integer size,String ebDeptId){
		return new R<>(inspectGroupService.getInspectGroup( current,  size,ebDeptId));
	}
}
