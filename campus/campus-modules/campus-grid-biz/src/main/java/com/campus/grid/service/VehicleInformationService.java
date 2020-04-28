package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.common.core.util.R;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.VehicleInformation;

import java.util.List;

public interface VehicleInformationService extends IService<VehicleInformation> {

	/**
	 * 分页查询车辆信息
	 */
	PageBean queryPage(Page page,VehicleInformation vehicleInformation);

	/**
	 * 根据XX车辆信息查询
	 *
	 * @return
	 */
	List<VehicleInformation> listVehicleInformationByxx(String xx);

	/**
	 * 新增车辆信息
	 *
	 * @return
	 */
	boolean save(VehicleInformation vehicleInformation);

	/**
	 * 学校信息
	 *
	 * @return
	 */
	VehicleInformation findSchoolInfo();

	/**
	 * 修改学校状态
	 *
	 * @param school
	 * @return
	 */
	R<Boolean> updateSchoolStatus(VehicleInformation school);

	void deleteById(String vId);

	void upedate(VehicleInformation vehicleInformation);
}
