package com.campus.grid.service;

import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.SchoolBusEd;

public interface SchoolBusService {

	void saveSchoolBusEd(SchoolBusEd schoolBusEd);

	PageBean queryPage(Integer current, Integer size, Integer edtype);
}
