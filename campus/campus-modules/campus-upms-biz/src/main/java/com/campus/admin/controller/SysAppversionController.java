package com.campus.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.entity.SysAppversion;
import com.campus.admin.service.SysAppversionService;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.FileSizeConversion;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.common.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hlp
 * @date 2019-05-17 10:22:50
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysappversion")
@Slf4j
public class SysAppversionController {
	private final SysAppversionService sysAppversionService;
	@Autowired
	private MinioTemplate minioTemplate;
	/**
	 * 分页查询
	 *
	 * @param page          分页对象
	 * @param sysAppversion
	 * @return
	 */
	@GetMapping("/page")
	public R getSysAppversionPage(Page page, SysAppversion sysAppversion) {
		return new R<>(sysAppversionService.page(page, Wrappers.query(sysAppversion)));
	}


	/**
	 * 通过id查询
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") String id) {
		return new R<>(sysAppversionService.getById(id));
	}

	/**
	 * 新增
	 *
	 * @param sysAppversion
	 * @return R
	 */
	@SysLog("新增")
	@PostMapping
	public R save(@RequestBody SysAppversion sysAppversion) {
		               sysAppversionService.updateVersionStatus();
		return new R<>(sysAppversionService.save(sysAppversion));
	}

	/**
	 * 修改
	 *
	 * @param sysAppversion
	 * @return R
	 */
	@SysLog("修改")
	@PutMapping
	public R updateById(@RequestBody SysAppversion sysAppversion) {
		return new R<>(sysAppversionService.updateById(sysAppversion));
	}

	/**
	 * 通过id删除
	 *
	 * @param id id
	 * @return R
	 */
	@SysLog("删除")
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		return new R<>(sysAppversionService.updateVersionStatusById(id));
	}

	/**
	 * 上传版本包并计算文件大小
	 *
	 * @param file file
	 * @return R
	 */
	@PostMapping("/fileUpload")
	public R upload(@RequestParam("file") MultipartFile file) {
		//		计算出文件大小
		String fileSize = FileSizeConversion.FormetFileSize(file.getSize());
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);
		resultMap.put("fileSize", fileSize);
		try {
			//将文件上传
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
			//上传成功存入数据库

		} catch (Exception e) {
			log.error("上传失败", e);
			return R.builder().code(CommonConstants.FAIL)
					.msg(e.getLocalizedMessage()).build();
		}
		return R.builder().data(resultMap).build();
	}


	/**
	 *aap版本更新接口
	* */
	@GetMapping("/versionUpdating/{appVsersion}")
	public R versionUpdating(@PathVariable("appVsersion") String appVsersion) {
//		获取当前唯一的启用版本
		SysAppversion sysAppversion = sysAppversionService.getOne(Wrappers.query(new SysAppversion()).eq(SysAppversion::getVersionStatus, "1"));
//		将启用版本号与app版本号进行比对
		int flag = FileSizeConversion.compareVersion(sysAppversion.getCode(), appVsersion);
		if (flag==CommonConstants.VERSION_FLAG){
			return new R(sysAppversion.getDownUrl());
		}
		return new R().setData(false);
	}

}
