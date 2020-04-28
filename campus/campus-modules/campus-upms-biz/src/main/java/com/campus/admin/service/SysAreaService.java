package com.campus.admin.service;

import com.campus.admin.api.entity.SysArea;
import com.campus.admin.api.entity.SysCity;
import com.campus.admin.api.entity.SysProvince;

import java.util.List;

/**
 * @author campus
 * @date 2017/10/31
 */
public interface SysAreaService {

	/**
	 * 获取省份
	 *
	 * @return
	 */
	List<SysProvince> listProvince();

	/**
	 * 获取城市
	 *
	 * @param key
	 * @return
	 */
	List<SysCity> listCityByProvinceCode(String key);

	/**
	 * 获取地区
	 *
	 * @param key
	 * @return
	 */
	List<SysArea> listAreaByCityCode(String key);
}
