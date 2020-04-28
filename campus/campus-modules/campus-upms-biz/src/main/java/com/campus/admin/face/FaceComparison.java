package com.campus.admin.face;

import cn.hutool.core.util.StrUtil;
import com.campus.admin.api.vo.UserVO;
import com.campus.admin.service.SysUserService;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.minio.service.MinioTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.InputStream;


/**
 * @author hulipeng
 * 人脸比对
 */
@Component
@AllArgsConstructor
@Slf4j
public class FaceComparison {
	@Autowired
	private FacialFeatureValue facialFeatureValue;
	@Autowired
	private final RedisTemplate redisTemplate;
	@Autowired
	private MinioTemplate minioTemplate;
    @Autowired
	private FaceUserFeatureValue faceUserFeatureValue;

	private final SysUserService sysUserService;

	/**
	 * 使用人脸特征值比对
	 */
	public boolean faceThan(String userId, String fea1) {
		//从redis中获取用户头像的人脸特征值
		String feal2 = (String) redisTemplate.opsForValue().get(userId);
		if (StringUtils.isBlank(feal2)) {
//			redis中没有用户头像人脸特征值,重新计算
			log.info("redis中没有用户头像人脸特征值,重新计算");
			UserVO userVO = sysUserService.selectUserVoById(userId);
			String[] picName = StrUtil.split(userVO.getAvatar(), StrUtil.DASHED);
			try (InputStream picStream = minioTemplate.getObject(picName[0], picName[1])) {
				System.out.println(picStream.available());
				String picData = ImageAnd64Binary.getImageStr(picStream);
				feal2 = faceUserFeatureValue.getFacialFeatureValue(picData, userId, SecurityConstants.FROM_IN);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("获取用户头像失败");
			}
		}
		//进行对比
		String score = facialFeatureValue.getScore(fea1, feal2);
		if (FaceConstants.THRESHOLD_VALUE < Float.parseFloat(score) ) {
			return true;
		}
		return false;
	}
}
