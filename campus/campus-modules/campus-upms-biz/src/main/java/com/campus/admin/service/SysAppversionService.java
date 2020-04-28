package com.campus.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.admin.api.entity.SysAppversion;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 *
 * @author hlp
 * @date 2019-05-17 10:22:50
 */
public interface SysAppversionService extends IService<SysAppversion> {

	void updateVersionStatus();

	boolean updateVersionStatusById(String id);
}
