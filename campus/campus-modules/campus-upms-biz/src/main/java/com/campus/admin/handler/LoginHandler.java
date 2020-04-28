package com.campus.admin.handler;

import com.campus.admin.api.dto.UserInfo;

/**
 * @author campus
 * @date 2018/11/18
 * <p>
 * 登录处理器
 */
public interface LoginHandler {

	/**
	 * 通过用户传入获取唯一标识
	 *
	 * @param loginStr
	 * @return
	 */
	String identify(String loginStr);

	/**
	 * 通过openId 获取用户信息
	 *
	 * @param identify
	 * @return
	 */
	UserInfo info(String identify);

	/**
	 * 处理方法
	 *
	 * @param loginStr 登录参数
	 * @return
	 */
	UserInfo handle(String loginStr);
}
