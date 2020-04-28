package com.campus.grid.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.entity.SchoolImg;
import com.campus.grid.api.vo.SchoolVO;

import java.util.List;

/**
 * 学校
 *
 * @author hhsep
 * @date 2018-12-14 10:27:48
 */
public interface SchoolService extends IService<School> {

	/**
	 * 分页查询学校信息
	 *
	 * @param page     分页对象
	 * @param schoolVO 参数列表
	 * @return
	 */
	IPage listSchoolPage(Page page, SchoolVO schoolVO);

	/**
	 * 根据部门ID查询学校
	 *
	 * @return
	 */
	List<School> listSchoolsByDeptId(String deptId);

	/**
	 * 新增用户
	 *
	 * @return
	 */
	boolean save(School school);

	/**
	 * 学校信息
	 *
	 * @return
	 */
	SchoolVO findSchoolInfo();

	/**
	 * 修改学校状态
	 *
	 * @param school
	 * @return
	 */
	R<Boolean> updateSchoolStatus(School school);
	/**
	 * 修改学校状态
	 * @param schooId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<SchoolImg> getSchoolImgByTime(String schooId, String startDate, String endDate);
}
