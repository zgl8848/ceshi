package com.campus.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.admin.api.entity.SysAppversion;

/**
 * 
 *
 * @author hlp
 * @date 2019-05-17 10:22:50
 */
public interface SysAppversionMapper extends BaseMapper<SysAppversion> {

	void updateVersionStatus();

	boolean updateVersionStatusById(String id);
}
