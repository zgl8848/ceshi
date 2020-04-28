package com.campus.admin.api.feign.fallback;

import com.campus.admin.api.dto.UserInfo;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.feign.RemoteUserService;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author campus
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {
	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     内外标志
	 * @return R
	 */
	@Override
	public R<UserInfo> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @param from  内外标志
	 * @return
	 */
	@Override
	public R<UserInfo> social(String inStr, String from) {
		log.error("feign 查询用户信息失败:{}", inStr, cause);
		return null;
	}

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@Override
	public R<List<SysUser>> ancestorUsers(String username) {
		log.error("feign 查询用户上级部门用户列失败:{}", username, cause);
		return null;
	}

	@Override
	public R updateUserStatus(String schoolId, String lockFlag) {
		log.error("feign 根据学校编号修改所有用户的状态失败:{}", schoolId, cause);
		return null;
	}

	@Override
	public TrueNameVO selectTrueName(String userId, String rectification) {
		log.error("feign 根据巡查人id和整改人id查询真实姓名失败:{}", userId, rectification, cause);
		return null;
	}
}
