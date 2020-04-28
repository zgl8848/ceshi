package com.campus.admin.controller;

import com.campus.admin.service.MobileService;
import com.campus.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author campus
 * @date 2018/11/14
 * <p>
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", description = "手机管理模块")
public class MobileController {
	private final MobileService mobileService;

	@GetMapping("/{mobile}")
	public R sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}
}
