package com.campus.grid.api.feign;

import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.grid.api.feign.factory.RemoteReseauServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author campus
 * @date 2018/6/22
 */
@FeignClient(value = ServiceNameConstants.GRID_SERVICE, fallbackFactory = RemoteReseauServiceFallbackFactory.class)
public interface RemoteReseauService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param userId 用户名
	 * @return R
	 */
	@GetMapping("/reseau/updateUser/{userId}")
	void updateUserIsNull(@PathVariable("userId") String userId);

	@GetMapping("/updateUserNameByUserId/{userId}/{userName}")
	void updateUserNameByUserId(@PathVariable("userId") String userId, @PathVariable("userName") String userName);
}
