package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.admin.api.feign.RemoteUserService;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.entity.SchoolImg;
import com.campus.grid.api.entity.equipmententity.EquipmentImg;
import com.campus.grid.api.vo.SchoolVO;
import com.campus.grid.mapper.SchoolMapper;
import com.campus.grid.service.SchoolService;
import com.campus.grid.util.CommonFunction;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 学校
 *
 * @author campus
 * @date 2018-12-14 10:27:48
 */
@Service("schoolService")
@AllArgsConstructor
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements IService<School>, SchoolService {

	private final SchoolMapper schoolMapper;
	private final RemoteDeptService remoteDeptService;
	private final RemoteUserService remoteUserService;

	@Override
	public IPage listSchoolPage(Page page, SchoolVO schoolVO) {
		String roleType = SecurityUtils.getUser().getRoleType();
		if (StringUtils.isNotBlank(roleType)) {
			if (roleType.equals(CommonConstants.ROLE_TYPE_EDUCATION)) {
				// 只有当deptId为空的时候才获取本部门及子部门下的数据
				if (StringUtils.isBlank(schoolVO.getDeptId())) {
					List<String> listDepts = remoteDeptService.listChildDepts().getData();
					schoolVO.setDeptIds(listDepts);
				}
			}
		}
		return schoolMapper.listSchoolPage(page, schoolVO);
	}

	@Override
	public List<School> listSchoolsByDeptId(String deptId) {
		// 如果登录用户的角色是学校，那么应该只返回用户的学校；如果是角色类型是管理员那么返回的应该是部门下的所有学校
		String roleType = SecurityUtils.getUser().getRoleType();
		String schoolId = SecurityUtils.getUser().getSchoolId();
		List<School> schools = null;
		if (StringUtils.isNotBlank(deptId) && StringUtils.isNotBlank(roleType)) {
			schools = schoolMapper.listSchoolsByDeptId(deptId);
			if (roleType.equals(CommonConstants.ROLE_TYPE_SCHOOL)) {
				return schools.stream().filter(sc -> sc.getSchoolId().equals(schoolId)).collect(Collectors.toList());
			}

		}
		return schools;
	}

	@Override
	public boolean save(School school) {
		// 获取学校编号中最大的编号
		String maxSchoolCode = schoolMapper.findSchoolByMaxCode();
		String newSchoolCode = CommonFunction.getNewSchoolCode(maxSchoolCode);
		school.setSchoolCode(newSchoolCode);
		return 0 == baseMapper.insert(school) ? false : true;
	}

	@Override
	public SchoolVO findSchoolInfo() {
		String schoolId = SecurityUtils.getUser().getSchoolId();
		return StringUtils.isNotBlank(schoolId) ? baseMapper.findSchoolById(schoolId) : null;
	}

	/**
	 * 修改学校状态
	 *
	 * @param school
	 * @return
	 */
	@Override
	public R<Boolean> updateSchoolStatus(School school) {
		// 修改学校状态 锁定 正常
		// 修改学校状态的时候也要把该学校下的所有用户状态也修改成 锁定 正常
		remoteUserService.updateUserStatus(school.getSchoolId(), school.getLockFlag());
		if (baseMapper.updateById(school) > 0) {
			return new R<>(true, "修改学校状态成功");
		}
		return new R<>(false, "修改学校状失败");
	}

	@Override
	public List<SchoolImg> getSchoolImgByTime(String schooId, String startDate, String endDate) {
//		拿到规定时间范围内的所以图片信息
		List<EquipmentImg> equipmentImgList = schoolMapper.getSchoolImgByTime(schooId, startDate, endDate);
//		存放每天图片信息的集合
		List<SchoolImg> schoolImgArrayList = new ArrayList<>();
//		图片信息是否为同一天归类集合
		LinkedHashSet<String> dateSet = new LinkedHashSet<>();
//		将每张图片信息的时间截取到天,存入dateSet
		equipmentImgList.forEach(equipmentImg -> {
//			DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
//			String format = df1.format(equipmentImg.getCreateTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String format = formatter.format(equipmentImg.getCreateTime());
			dateSet.add(format);
		});
//	    根据dateSet天数来将对应天数下的图片信息存入SchoolImg
		dateSet.forEach(day -> {
			//		存放根据日期切割完的图片信息
			List<EquipmentImg> equipmentImgs = new ArrayList<>();
			equipmentImgList.forEach(equipmentImg -> {
/*				DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
				String format = df1.format(equipmentImg.getCreateTime());*/
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(equipmentImg.getCreateTime());
				if (StringUtils.equals(day, format)) {
					equipmentImgs.add(equipmentImg);
				}
			});
			SchoolImg schoolImg = new SchoolImg(day, String.valueOf(equipmentImgs.size()), equipmentImgs);
			schoolImgArrayList.add(schoolImg);
		});
		return schoolImgArrayList;
	}
}
