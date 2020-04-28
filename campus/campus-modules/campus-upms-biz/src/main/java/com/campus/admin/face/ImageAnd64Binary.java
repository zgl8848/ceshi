package com.campus.admin.face;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
/**
 * 说明：BASE64处理
 * 创建人：hulipeng
 * @author eatheryu
 */
@Slf4j
@Component
public class ImageAnd64Binary {

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 *
	 * @param file 文件对象
	 * @return
	 */
	public static String getImageStr(MultipartFile file) {
		byte[] data = null;
		BASE64Encoder encoder = null;
		try {
			data = file.getBytes();
			//对字节数组Base64编码
			encoder = new BASE64Encoder();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("图片获取字节数组失败:{}", e);
		}
		//返回Base64编码过的字节数组字符串
		return encoder.encode(data);
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 *
	 * @param imgStr        转换为图片的字符串
	 * @param imgCreatePath 将64编码生成图片的路径
	 * @return
	 */
	public static boolean generateImage(String imgStr, String imgCreatePath) {
		//图像数据为空
		if (imgStr == null) {
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = FaceConstants.STREAM_START_VALUE; i < b.length; ++i) {
				//调整异常数据
				if (b[i] < FaceConstants.STREAM_START_VALUE) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgCreatePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将图片流进行Base64解码
	 */

	public static String getImageStr(InputStream in) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[FaceConstants.STREAM_SIZE];
			int rc;
			while ((rc = in.read(buff, FaceConstants.STREAM_START_VALUE, FaceConstants.STREAM_SIZE)) > FaceConstants.STREAM_START_VALUE) {
				swapStream.write(buff, FaceConstants.STREAM_START_VALUE, rc);
			}
			data = swapStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Base64.encode(data);
	}
}