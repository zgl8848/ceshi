package com.campus.admin.mapper;

import com.campus.admin.api.entity.SysArea;
import com.campus.admin.api.entity.SysCity;
import com.campus.admin.api.entity.SysProvince;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 地区获取 Mapper 接口
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
public interface SysAreaMapper {

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
	List<SysCity> listCityByProvinceCode(@Param("key") String key);

	/**
	 * 获取地区
	 *
	 * @param key
	 * @return
	 */
	List<SysArea> listAreaByCityCode(@Param("key") String key);
}
