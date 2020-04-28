package com.campus.grid.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.common.core.util.R;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.GPS;
import com.campus.grid.api.entity.SchoolBusEd;
import com.campus.grid.api.entity.VehicleInformation;
import com.campus.grid.mapper.GPSMapper;
import com.campus.grid.mapper.SchoolMapper;
import com.campus.grid.mapper.VehicleInformationMapper;
import com.campus.grid.service.GPSServer;
import com.campus.grid.service.VehicleInformationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;


@Service("VehicleInformationService")
@AllArgsConstructor
public class VehicleInformationServiceImpl extends ServiceImpl<VehicleInformationMapper, VehicleInformation> implements  VehicleInformationService  {
	private final VehicleInformationMapper vehicleInformationMapper;

	@Override
	public PageBean queryPage(Page page,VehicleInformation vehicleInformation) {
		Long startIndex = (page.getCurrent() - 1 ) * page.getSize();
		List<VehicleInformation> vehicleInformationList = vehicleInformationMapper.queryPage(startIndex,page.getSize(),vehicleInformation);
		int total = vehicleInformationMapper.getTotal();
		PageBean pageBean = new PageBean();
		pageBean.setCurrent((int)page.getCurrent());
		double ceil = Math.ceil((total * 1.0) /  page.getSize());
		int pages = (int) ceil;
		pageBean.setSize((int)page.getSize());
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setData(vehicleInformationList);
		return  pageBean;
	}

	@Override
	public List<VehicleInformation> listVehicleInformationByxx(String xx) {
//		return vehicleInformationMapper.listVehicleInformationByxx(xx);
		return null;
	}

	@Override
	public void deleteById(String vId) {
		vehicleInformationMapper.deleteById(vId);
	}

	@Override
	public void upedate(VehicleInformation vehicleInformation) {
		if (null != vehicleInformation.getVId() && !"".equals(vehicleInformation.getVId())) {
			vehicleInformationMapper.upedateVehicleInformation(vehicleInformation);
		}
	}

	@Override
	public boolean save(VehicleInformation vehicleInformation) {
		return false;
	}

	@Override
	public VehicleInformation findSchoolInfo() {
		return null;
	}

	@Override
	public R<Boolean> updateSchoolStatus(VehicleInformation school) {
		return null;
	}


}
