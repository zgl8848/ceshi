package com.campus.admin.face;

import cn.hutool.core.util.StrUtil;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;


/**
 * @author eatheryu
 */
@Slf4j
@Component
@AllArgsConstructor
public class FaceUserFeatureValue {
	@Autowired
	private MinioTemplate minioTemplate;
	@Autowired
	private FacialFeatureValue facialFeatureValue;
	@Autowired
	private final RedisTemplate redisTemplate;

	/**
	 * 调用人脸特征值提取方法
	 * */
	public void getFaceUserFeatureValuye(String userId,String upicName) throws Exception {
		//			将上传的头像进行人脸特征值的计算并存入redis
		log.info("用户id:{}", userId);
		String[] picName = StrUtil.split(upicName, StrUtil.DASHED);
		InputStream picStream = minioTemplate.getObject(picName[0], picName[1]);
		String picData = ImageAnd64Binary.getImageStr(picStream);
		getFacialFeatureValue(picData,userId,SecurityConstants.FROM_IN);
	}


	/**
	 * 人脸特征值提取方法
	 */
	public String getFacialFeatureValue(String picData, String userId,String flag) {
		// 调用人脸归一，该步骤不可缺少
		String detectStr = facialFeatureValue.detectFace(picData);
		if (FaceConstants.STATUS_CODEONE.equals(detectStr) || FaceConstants.STATUS_CODETWO.equals(detectStr) || StringUtils.isBlank(detectStr)) {
			log.error("无法连接人脸归一化外部接口");
		}
		String fea1 = null;
		try {
			//调用接口获取照片中的人脸特征值
			JSONArray array = new JSONArray(detectStr);
			fea1 = facialFeatureValue.getFacialFeatureValue(array);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//把获取到得人脸特征值存到redis中
		if (StringUtils.equals(SecurityConstants.FROM_IN, flag)) {
			if (fea1 != null && !fea1.equals("")) {
				//获取当前登录用户名当做key存入redis
				redisTemplate.opsForValue().set(userId,fea1);
				log.info("将特征值存入Redis:{}",fea1);
			}
		}
		return fea1;
	}
}
