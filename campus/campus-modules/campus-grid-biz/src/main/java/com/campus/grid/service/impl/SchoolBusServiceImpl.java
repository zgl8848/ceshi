package com.campus.grid.service.impl;

import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.SchoolBusEd;
import com.campus.grid.mapper.SchoolBusMapper;
import com.campus.grid.service.SchoolBusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("schoolBusService")
@AllArgsConstructor
public class SchoolBusServiceImpl implements SchoolBusService {

	private SchoolBusMapper schoolBusMapper;

	@Override
	public void saveSchoolBusEd(SchoolBusEd schoolBusEd) {
		schoolBusMapper.saveSchoolBusEd(schoolBusEd);
	}

	@Override
	public PageBean queryPage(Integer current, Integer size, Integer edtype) {
		Integer startIndex = (current - 1) * size;
		List<SchoolBusEd> schoolBusEdList = schoolBusMapper.queryPage(startIndex, size, edtype);
		int total = schoolBusMapper.getTotal();
		PageBean pageBean = new PageBean();
		pageBean.setCurrent(current);
		double ceil = Math.ceil((total * 1.0) / size);
		int pages = (int) ceil;
		pageBean.setSize(size);
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setData(schoolBusEdList);
		return pageBean;
	}
}
