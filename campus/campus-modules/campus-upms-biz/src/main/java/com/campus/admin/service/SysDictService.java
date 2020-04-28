package com.campus.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author campus
 * @since 2017-11-19
 */
public interface SysDictService extends IService<SysDict> {

	DictInfo getInfo(String value);

	DictInfo getDict(String type, String value);
}
