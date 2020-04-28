package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.emotion.EmotionPerson;
import com.campus.grid.api.vo.AlarmVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AlarmMapper extends BaseMapper<Alarm> {

	boolean insertAlarmInfo(Alarm alarm);

	String getAlarmByValueAndType(@Param("alarmTitle") String alarmTitle, @Param("alarmDataType") String alarmDataType);

	String isHaveAlarm(Alarm alarm);

	List<AlarmVO> queryPage(@Param("startIndex") Integer startIndex, @Param("size") Integer size,
							@Param("schoolId") String schoolId, @Param("childDepts")List<String> childDepts,
							@Param("roleType") String roleType,
							@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	String findLabelByType(@Param("value") String value, @Param("type") String type);

	Integer getTotal(@Param("schoolId") String schoolId, @Param("childDepts")List<String> childDepts,
					 @Param("roleType") String roleType,
					 @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	boolean saveBlackPerson(Person person);

	Person findBlackPersonById(@Param("alarmMsg") String alarmMsg);

	/**
	 * 分页查询告警信息
	 *
	 * @param page      	分页
	 * @param schoolId   	学校ID
	 * @param alarmTitle   	告警标题
	 *
	 * @return list
	 */
	IPage<List<AlarmVO>> getAlarmPage(Page page, @Param("schoolId") String schoolId, @Param("alarmTitle") String alarmTitle);

	/**
	 * 根据告警模块 统计
	 * @param schoolId
	 * @param childDepts
	 * @param roleType
	 * @return
	 */
	List<AlarmVO> statByModule(@Param("schoolId") String schoolId, @Param("childDepts")List<String> childDepts,
							   @Param("roleType") String roleType,
							   @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

	/**
	 * 根据区域 统计
	 * @param schoolId
	 * @param childDepts
	 * @param roleType
	 * @return
	 */
	List<AlarmVO> statByAreaCode(@Param("schoolId") String schoolId, @Param("childDepts")List<String> childDepts,
								 @Param("roleType") String roleType,
								 @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
	/**
	 * 根据月份 统计
	 * @param schoolId
	 * @param childDepts
	 * @param roleType
	 * @return
	 */
	Map statByMonth(@Param("schoolId") String schoolId, @Param("childDepts")List<String> childDepts, @Param("roleType") String roleType, @Param("year") String year);

	/**
	 * 存储人脸信息
	 * @param emotionPerson	人脸信息
	 * @return	判断是否存储成功
	 */
    boolean insertFaceInfo(EmotionPerson emotionPerson);
}
