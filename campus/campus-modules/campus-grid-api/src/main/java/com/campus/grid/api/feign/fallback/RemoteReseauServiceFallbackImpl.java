package com.campus.grid.api.feign.fallback;

import com.campus.grid.api.feign.RemoteReseauService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author campus
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteReseauServiceFallbackImpl implements RemoteReseauService {
	@Setter
	private Throwable cause;

	@Override
	public void updateUserIsNull(String userId) {
		log.error("feign 删除网格用户失败:{}", userId, cause);
	}

	@Override
	public void updateUserNameByUserId(String userId, String userName) {
		log.error("feign 修改网格用户名称失败:{}", userId, userName, cause);
	}
}
