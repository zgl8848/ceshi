package com.campus.grid.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.entity.SysDept;
import com.campus.grid.api.entity.Message;
import com.campus.grid.api.entity.School;

public interface MessageMapper extends BaseMapper<Message>{
	
	/**
	 *  通过城市获取区域
	 * @param code
	 * @return
	 */
	@Select("SELECT dept_id deptId,name FROM sys_dept WHERE parent_id=#{deptId} AND city=#{code}")
	List<SysDept> getCountyByCity(@Param("deptId")String deptId,@Param("code")String code);
	
	/**
	 *  通过当前用户deptId判断是否有对应dept权限
	 * @param ancestor
	 * @param descendant
	 * @return
	 */
	@Select("select ancestor from sys_dept_relation where ancestor=#{ancestor} and descendant=#{descendant} limit 0,1")
	String isNullByDeptId(@Param("ancestor")String ancestor,@Param("descendant")String descendant);
	
	/**
	 * 获取消息公告发布页面学校列表
	 * @param cityName
	 * @return 
	 */
	List<School> getSchoolListByCity(@Param("deptId")String deptId,@Param("cityCode")String cityCode);
	
	/**
	 * 根据区域deptId获取学校
	 * @param deptId
	 * @return
	 */
	List<School> getSchoolListByDeptId(@Param("deptId")String deptId);
	
	/**
	 * 保存消息公告信息
	 */
	void saveMessage(Message message);
	
	/**
	 * 保存用户接收消息公告信息详情
	 */
	@Insert("insert into grid_message_user(message_id,user_id,message_type) values(#{messageId},#{userId},#{messageType})")
	void saveMessageUser(@Param("messageId")String messageId,@Param("userId")String userId,@Param("messageType")String messageType);
	
	/**
	 * 根据接受人员（部门ID）获取当前部门所有人员
	 * @param receiverId
	 */
	@Select("select supervisor_id from grid_post_info where school_division_id=#{receiverId}")
	List<String> getUserByReceiver(@Param("receiverId")String receiverId);

	/**
	 * 通过学校ID获取部门
	 * @param schoolId
	 * @return
	 */
	List<Map<String, String>> getPostIdBySchoolId(@Param("schoolId")String schoolId);	
	
	/**
	 *  通过学校ID获取区域ID
	 * @param schoolId
	 * @return
	 */
	List<String> getDeptIdBySchool(@Param("schoolId")String schoolId);
	
	/**
	 *  通过岗位ID获取岗位名称
	 * @param receiver
	 * @return
	 */
	@Select("select post_name postName from grid_personnel_post where post_id in (${receiver})")
	List<String> getPersonnelUser(@Param("receiver")String receiver);
	
	@Select("select school_name schoolName from grid_school where school_id in (${schoolId})")
	List<String> getSchoolNameById(@Param("schoolId")String schoolId);
	
	/**
	 *   分页查询消息公告列表
	 * @param page
	 * @param message
	 * @return
	 */
	IPage listMessagePage(Page page,@Param("schoolId")String schoolId,@Param("messageArea")String messageArea,@Param("createTime")String createTime,@Param("endTime")String endTime);
	
	/**
	 * 获取城市code
	 * @param name
	 * @return
	 */
	@Select("select code from sys_city where name=#{name}")
	String getCityCodeByName(@Param("name")String name);
	
	/**
	 * 获取城市deptId
	 * @param name
	 * @return
	 */
	@Select("select dept_id deptId from sys_dept where name=#{name}")
	String getDeptIdByName(@Param("name")String name);
	
	/**
	 * app获取当前用户消息列表
	 * @param userId
	 * @return
	 */
	IPage appGetMessageListByUser(Page page,@Param("userId")String userId,@Param("messageType")String messageType);
	
	@Select("select COUNT(0) from grid_message_user where user_id=#{userId} and status='0' and message_type=#{messageType}")
	int messageCountByUser(@Param("userId")String userId,@Param("messageType")String messageType);
	
	/**
	 *   更改用户消息状态
	 * @param userId
	 * @return
	 */
	@Update("UPDATE grid_message_user SET status='1' WHERE user_id=#{userId} AND message_type=#{messageType} ")
	int updateMessageStatusByUser(@Param("userId")String userId,@Param("messageType")String messageType);

	/**
	 * 获取区域名称
	 * @param deptId 区域id
	 * @return
	 */
	@Select("select name from sys_dept where dept_id=#{deptId}")
	String getDeptNameById(@Param("deptId") String deptId);
}
