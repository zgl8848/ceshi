package com.campus.grid.util;

/**
 * @author eatheryu
 */
public interface MessageConstants {
	/**
	 * 设备标识
	 */
	String MSG_EQUIPMENT = "1";
	/**
	 * 抓拍机告警标识
	 */
	String MSG_CAMERA_ALARM = "2";
	/**
	 * 黑名单告警标识
	 */
	String MSG_BLACK_ALARM = "3";
	/**
	 * 电子围栏告警标识
	 */
	String ELECTRONIC_FENCE = "4";
	/**
	 * 增加设备信息标识
	 */
	String MSG_ADD = "add";
	/**
	 * 更新设备信息标识
	 */
	String MSG_UPDATE = "update";
	/**
	 * 图片处理标识 true=从链接获取图片 false=从base64流获取图片
	 */
	Boolean URL_PIC = true;
	Boolean BASE64_PIC = false;
	/**
	 * 图片前缀名
	 */
	String PIC_PREFIX = "campus-";
	/**
	 * 流处理最大字节数
	 */
	int BUFFER_SIZE = 1024;
	/**
	 * 读取流起点值
	 * */
	int STREAM_START_VALUE=0;
}
