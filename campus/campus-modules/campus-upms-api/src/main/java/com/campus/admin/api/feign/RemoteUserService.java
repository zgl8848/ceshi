package com.campus.admin.api.feign;

import com.campus.admin.api.dto.UserInfo;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author campus
 * @date 2018/6/22
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @param from  调用标志
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	R<List<SysUser>> ancestorUsers(@PathVariable("username") String username);

	/**
	 * 根据学校编号修改所有用户的状态 锁定 正常
	 *
	 * @param schoolId
	 * @param lockFlag
	 * @return
	 */
	@PutMapping("/user/updateUserLockFlagStatus")
	R updateUserStatus(@RequestParam("schoolId") String schoolId, @RequestParam("lockFlag") String lockFlag);

	/**
	 * 根据巡查人id和整改人id查询真实姓名
	 * @param userId
	 * @param rectification
	 * @return
	 */
	@GetMapping("/user/selectTrueName/{userId}/{rectification}")
    TrueNameVO selectTrueName(@RequestParam("userId") String userId, @RequestParam("rectification") String rectification);
}
