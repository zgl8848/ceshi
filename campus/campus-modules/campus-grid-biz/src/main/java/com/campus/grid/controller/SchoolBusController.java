package com.campus.grid.controller;

import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.R;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.SchoolBusEd;
import com.campus.grid.service.SchoolBusService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/schoolBus")
@RestController
public class SchoolBusController {

	private final MinioTemplate minioTemplate;
	private SchoolBusService schoolBusService;

	/**
	 * 保存校车告警信息
	 */
	@RequestMapping("/schoolBusEd")
	public void saveSchoolBusEd(@RequestBody(required = false) SchoolBusEd schoolBusEd) throws Exception {

		if (schoolBusEd != null) {

			String uuid = UUID.randomUUID().toString().replace("-", "");
			schoolBusEd.setSchool_bus_id(uuid);

			try {
				schoolBusService.saveSchoolBusEd(schoolBusEd);
				//先存库
				if (2 == schoolBusEd.getEdtype()) {
					//图片信息
					String front_img = schoolBusEd.getFront_img();
					if (!"".equals(front_img) && null != front_img) {
						String absoluteFrontImg = joeSendPostURL(front_img);
						//获取图片地址
						if (!"".equals(absoluteFrontImg) && null != absoluteFrontImg) {
							saveImgFile(absoluteFrontImg, front_img);
							//存入文件服务器
						}
						saveImgFile(absoluteFrontImg, front_img);
					}

					String back_img = schoolBusEd.getBack_img();

					if (!"".equals(back_img) && null != back_img) {
						String absoluteBackImg = joeSendPostURL(schoolBusEd.getBack_img());
						//获取图片地址
						if (!"".equals(absoluteBackImg) && null != absoluteBackImg) {
							saveImgFile(absoluteBackImg, back_img);
							//存入文件服务器
						}
					}
				} else if (3 == schoolBusEd.getEdtype()) {
					//视频信息
					String front_video = schoolBusEd.getFront_img();
					if (!"".equals(front_video) && null != front_video) {
						String absoluteFrontVideo = joeSendPostURL(front_video);
						//获取视频地址
						if (!"".equals(absoluteFrontVideo) && null != absoluteFrontVideo) {
							saveImgFile(absoluteFrontVideo, front_video);
							//存入文件服务器
						}
					}

					String back_video = schoolBusEd.getBack_video();
					if (!"".equals(back_video) && null != back_video) {
						String absoluteBackVideo = joeSendPostURL(back_video);
						//获取视频地址
						if (!"".equals(absoluteBackVideo) && null != absoluteBackVideo) {
							saveImgFile(back_video, absoluteBackVideo);
							//存入文件服务器
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@RequestMapping("/page")
	public R getQueryPage(Integer current, Integer size, Integer edtype) {
		PageBean pageBean = schoolBusService.queryPage(current, size, edtype);
		return new R<>(pageBean);
	}

	private void saveImgFile(String aliyunurl, String imgName) throws Exception {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[1024];
		String fileName = imgName.substring(imgName.lastIndexOf("/") + 1);
		File file = new File(fileName);
		url = new URL(aliyunurl);
		//url = new URL("http://androidsdk.adasplus.com/reportform_mongodb_get_imgurl/http://fatigue.oss-cn-beijing.aliyuncs.com/zhongjudy20171104/20190403/pd_2186838_1554257309_640_480_1_1554273726_81_27.jpeg");
		httpUrl = (HttpURLConnection) url.openConnection();
		httpUrl.connect();
		bis = new BufferedInputStream(httpUrl.getInputStream());
		fos = new FileOutputStream(file);
		int size = 0;
		while ((size = bis.read(buf)) != -1) {
			fos.write(buf, 0, size);
		}
		fos.flush();
		fos.close();
		bis.close();
		httpUrl.disconnect();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
		minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, bufferedInputStream);
		bufferedInputStream.close();
		new File(fileName).delete();

	}

	private String joeSendPostURL(String imgOrVideo) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 参数
		StringBuffer params = new StringBuffer();
		try {
			params.append("url=http://fatigue.oss-cn-beijing.aliyuncs.com" + imgOrVideo);
			// 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
			/* params.append("name=" + URLEncoder.encode("&", "utf-8"));
			   params.append("&");
			   params.append("age=24");*/
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		HttpPost httpPost = new HttpPost("http://androidsdk.adasplus.com/reportform_mongodb_get_imgurl" + "?" + params);
		// 创建Post请求
		CloseableHttpResponse response = null;
		// 响应模型
		String url = "";
		try {
			// 由客户端执行(发送)Get请求
			response = httpClient.execute(httpPost);
			// 配置信息
			RequestConfig requestConfig = RequestConfig.custom()
					// 设置连接超时时间(单位毫秒)
					.setConnectTimeout(5000)
					// 设置请求超时时间(单位毫秒)
					.setConnectionRequestTimeout(5000)
					// socket读写超时时间(单位毫秒)
					.setSocketTimeout(5000)
					// 设置是否允许重定向(默认为true)
					.setRedirectsEnabled(true).build();

			// 将上面的配置信息 运用到这个Get请求里
			httpPost.setConfig(requestConfig);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				String s = EntityUtils.toString(responseEntity).split(",")[0];
				String url1 = s.split(":")[1];
				String url2 = s.split(":")[2];
				url = url1 + ":" + url2;
				return url.substring(2, url.length() - 1);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	//public R getById(@PathVariable("schoolId") String schoolId) {
	//return new R<>(schoolService.getById(schoolId));
	//}

}
