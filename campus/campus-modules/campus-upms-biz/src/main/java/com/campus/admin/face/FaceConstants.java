package com.campus.admin.face;

/**
 * @author eatheryu
 */
public class FaceConstants {
	/**
	 * 人脸归一返回状态码
	 * */
	static String STATUS_CODEONE="200";
	/**
	 * 人脸归一返回状态码
	 * */
	static String STATUS_CODETWO="300";
	/**
	 * 阈值
	 * */
	static float THRESHOLD_VALUE=0.4f;
	/**
	 * 人脸归一
	 * */
	static String FACE_TO_A="http://192.168.1.200:8082/hhs/dt/detect";
	/**
	 * 人脸特征值计算
	 * */
	static String EIGENVALUE_CALCULATION="http://192.168.1.200:8082/hhs/ft/feature";
	/**
	 * 人脸比对
	 * */
	static String FACE_COMPARISON="http://192.168.1.200:8082/hhs/ft/score";
	/**
	 * 用户照片base64
	 * */
	static String BASE64_NAME="picData";
	/**
	 * 人脸比对结果的key
	 * */
	static String EIGENVALUE_KEY="feature";
	/**
	 * 编码字符集
	 * */
	static String CODED_FORMAT="utf-8";
    /**
	 * 字节数组默认长度,以及读取流的最大长度
	 * */
	static int STREAM_SIZE=2048;
    /**
	 * 读取流起点值
	 * */
	static int STREAM_START_VALUE=0;
}
