package com.campus.grid.util;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.BlackAlarm;
import com.campus.grid.api.entity.blackalarm.FenceAlarm;
import com.campus.grid.api.entity.blackalarm.Person;
import com.campus.grid.api.entity.hktonglinkmsg.EventNotify;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author eatheryu
 * kafka 方法公共类
 */
@Slf4j
public class MessageMethods {
	/**
	 * 图片转换
	 *
	 * @param flag: true 是抓拍机图片存储,黑名单图片存储 false 是设备图片存储
	 */
	public String saveToFile(String destUrl, MinioTemplate minioTemplate, Boolean flag) throws Exception {
		if (flag) {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			HttpURLConnection httpUrl = null;
			URL url = null;
			byte[] buf = new byte[MessageConstants.BUFFER_SIZE];
			File file = new File("alarm.jpg");
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(file);
			int size = MessageConstants.STREAM_START_VALUE;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, MessageConstants.STREAM_START_VALUE, size);
			}
			fos.flush();
			fos.close();
			bis.close();
			httpUrl.disconnect();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file);
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, bufferedInputStream);
			bufferedInputStream.close();
			return fileName;
		} else {
			String file = base64ToPic(destUrl);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file);
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, bufferedInputStream);
			bufferedInputStream.close();
			return fileName;
		}
	}


	/**
	 * 拼接抓拍机图片url
	 */
	public String getPicUrl(String alarmPlatformIp, String alarmPicturePort, String picUrl) {
		String url = "http://" + alarmPlatformIp + ":" + alarmPicturePort + "/kms/services/rest/dataInfoService/getImage?id=" + picUrl + "";
		return url;
	}

	/**
	 * Base64转图片
	 */
	public String base64ToPic(String imgStr) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < MessageConstants.STREAM_START_VALUE) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		// 生成jpeg图片
		File file = new File("equipment.jpg");
		OutputStream out = new FileOutputStream(file);
		out.write(bytes);
		out.flush();
		out.close();
		return file.getName();
	}

	public BlackAlarm beanToPerson(BlackAlarm blackAlarm) {
		//设置personid
		String uuid = UUID.randomUUID().toString().replace("-", "");
//		存入黑名单类中
		blackAlarm.setPerson(new Person(uuid, blackAlarm.getPerson().getAge(), blackAlarm.getPerson().getPersonID(), blackAlarm.getPerson().getPersonName(), blackAlarm.getPerson().getPersonType(), blackAlarm.getPerson().getSex(), blackAlarm.getPerson().getModalType()));
		return blackAlarm;
	}
}
