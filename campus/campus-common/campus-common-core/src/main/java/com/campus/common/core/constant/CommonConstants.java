package com.campus.common.core.constant;

/**
 * @author campus
 * @date 2017/10/29
 */
public interface CommonConstants {
	/**
	 * header 中租户ID
	 */
	String TENANT_ID = "TENANT_ID";
	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "campus-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "campus";

	/**
	 * 路由存放
	 */
	String ROUTE_KEY = "gateway_route_key";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = "event_key";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;
	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 默认存储bucket
	 * 这个名称就是minio中的bucket名称
	 */
	String BUCKET_NAME = "campus";

	/**
	 * 系统角色
	 */
	String ROLE_TYPE_ADMIN = "0";

	/**
	 * 教育局角色
	 */
	String ROLE_TYPE_EDUCATION = "1";

	/**
	 * 学校角色
	 */
	String ROLE_TYPE_SCHOOL = "2";
	/**
	 * 版本比对
	 * */
	int VERSION_FLAG=1;
}
