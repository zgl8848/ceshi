package com.campus.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.campus.admin.face.FaceComparison;
import com.campus.admin.face.FaceUserFeatureValue;
import com.campus.admin.face.ImageAnd64Binary;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.util.R;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author campus
 * @date 2018-12-30
 * <p>
 * 文件管理
 */

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private MinioTemplate minioTemplate;
	@Autowired
	private FaceComparison faceComparison;
	@Autowired
	private FaceUserFeatureValue faceUserFeatureValue;

	/**
	 * 上传文件
	 * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 *
	 * @param file 资源
	 * @return R(bucketName, filename)
	 */


	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);
		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.builder().code(CommonConstants.FAIL)
					.msg(e.getLocalizedMessage()).build();
		}
		return R.builder().data(resultMap).build();
	}

	/**
	 * 获取文件
	 *
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */


	@GetMapping("/{fileName}")
	public void file(@PathVariable String fileName, HttpServletResponse response) {
		String[] nameArray = StrUtil.split(fileName, StrUtil.DASHED);
		try (InputStream inputStream = minioTemplate.getObject(nameArray[0], nameArray[1])) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}

	/**
	 * app端人脸比对
	 * @param file
	 * @return Boolean
	 */
	@PostMapping("/comparison")
	public R faceComparison(@RequestParam("file") MultipartFile file) {
		String userId = SecurityUtils.getUser().getId();
		//		将MultipartFile对象转为base64
		String picData = ImageAnd64Binary.getImageStr(file);
		//先进行人脸特征值计算
		String facialFeatureValue = null;
		try {
			facialFeatureValue = faceUserFeatureValue.getFacialFeatureValue(picData, userId, SecurityConstants.FROM_OUT);
		} catch (Exception e) {
			log.error("人脸特征值计算失败", e);
			return new R<>(1, "人脸比对失败", Boolean.FALSE);
		}
		//将当前图片与用户头像图片进行比对
		return new R(faceComparison.faceThan(userId, facialFeatureValue));
	}
}
