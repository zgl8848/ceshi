package com.campus.admin.service.impl;

import com.campus.admin.api.entity.SysArea;
import com.campus.admin.api.entity.SysCity;
import com.campus.admin.api.entity.SysProvince;
import com.campus.admin.mapper.SysAreaMapper;
import com.campus.admin.service.SysAreaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author campus
 * @date 2018/11/14
 * <p>
 * 地区获取关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysAreaServiceImpl implements SysAreaService {

	private final SysAreaMapper sysAreaMapper;

	@Override
	public List<SysProvince> listProvince() {
		return sysAreaMapper.listProvince();
	}

	@Override
	public List<SysCity> listCityByProvinceCode(String key) {
		return sysAreaMapper.listCityByProvinceCode(key);
	}

	@Override
	public List<SysArea> listAreaByCityCode(String key) {
		return sysAreaMapper.listAreaByCityCode(key);
	}
}
