package com.campus.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.entity.SysAppversion;
import com.campus.admin.mapper.SysAppversionMapper;
import com.campus.admin.service.SysAppversionService;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.FileSizeConversion;
import com.campus.common.core.util.R;
import com.campus.common.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author hlp
 * @date 2019-05-17 10:22:50
 */
@Service("sysAppversionService")
@Slf4j
@AllArgsConstructor
public class SysAppversionServiceImpl extends ServiceImpl<SysAppversionMapper, SysAppversion> implements SysAppversionService {
	private final SysAppversionMapper sysAppversionMapper;
	@Override
	public void updateVersionStatus() {
		sysAppversionMapper.updateVersionStatus();
	}

	@Override
	public boolean updateVersionStatusById(String id) {
		return sysAppversionMapper.updateVersionStatusById(id);
	}
}
