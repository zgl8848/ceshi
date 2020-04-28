package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.entity.equipmententity.EquipmentImg;
import com.campus.grid.api.vo.SchoolVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学校
 *
 * @author hhsep
 * @date 2018-12-14 10:27:48
 */
public interface SchoolMapper extends BaseMapper<School> {

	IPage listSchoolPage(Page page, @Param("query") SchoolVO schoolVO);

	List<School> listSchoolsByDeptId(@Param("deptId") String deptId);

	String findSchoolByMaxCode();

	SchoolVO findSchoolById(String schoolId);

	List<EquipmentImg> getSchoolImgByTime(@Param("schooId")String schooId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	// 根据学校ID获取所有用户 lc
	List<String> getUserBySchoolId(@Param("schoolId")String schoolId);
}
