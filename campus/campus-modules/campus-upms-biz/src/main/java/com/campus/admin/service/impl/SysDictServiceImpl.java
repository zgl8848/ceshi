package com.campus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;
import com.campus.admin.mapper.SysDictMapper;
import com.campus.admin.service.SysDictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author campus
 * @since 2017-11-19
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	@Override
	public DictInfo getInfo(String value) {
		return this.baseMapper.getInfo(value);
	}

	@Override
	public DictInfo getDict(String type, String value) {
		return this.baseMapper.getDict(type, value);
	}

}
