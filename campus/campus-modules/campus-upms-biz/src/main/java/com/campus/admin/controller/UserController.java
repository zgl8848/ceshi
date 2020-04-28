package com.campus.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.admin.service.SysUserService;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.common.security.annotation.Inner;
import com.campus.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author campus
 * @date 2018/12/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", description = "用户管理模块")
public class UserController {
	private final SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public R info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		return new R<>(userService.findUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public R info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		return new R<>(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public R userById(@PathVariable String id) {
		return new R<>(userService.selectUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public R user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return new R<>(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
	public R userDel(@PathVariable String id) {
		SysUser sysUser = userService.getById(id);
		return new R<>(userService.deleteUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R user(@RequestBody UserDTO userDto) {
		return userService.saveUser(userDto);
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
		return new R<>(userService.updateUser(userDto));
	}

	/**
	 * 更新用户密码
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping("/editPassword")
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R updateUserPassword(@Valid @RequestBody UserDTO userDto) {
		return new R<>(userService.updateUserPasswordById(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getUserPage(Page page, UserDTO userDTO) {
		return new R<>(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public R listAncestorUsers(@PathVariable String username) {
		return new R<>(userService.listAncestorUsers(username));
	}

	/**
	 * 根据学校ID查询用户
	 *
	 * @return 用户列表
	 */
	@GetMapping("/allUser")
	public R getList() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser sysUser = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		String schoolId = sysUser.getSchoolId();
		return new R<>(userService.list(new QueryWrapper<SysUser>().eq("school_id", schoolId)));
	}

	/**
	 * 根据学校编号修改所有用户的状态 锁定 正常
	 *
	 * @param schoolId
	 * @param lockFlag
	 * @return
	 */
	@SysLog("据学校编号修改所有用户的状态")
	@PutMapping("/updateUserLockFlagStatus")
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R updateUserLockFlagStatus(@RequestParam("schoolId") String schoolId, @RequestParam("lockFlag") String lockFlag) {
		return new R(userService.updateUserLockFlagStatus(schoolId, lockFlag));
	}

    /**
     * 根据巡查人id和整改人id查询真实姓名
     * @param userId
     * @param rectification
     * @return
     */
    @GetMapping("/selectTrueName/{userId}/{rectification}")
    public TrueNameVO selectTrueName(@PathVariable("userId") String userId, @PathVariable("rectification") String rectification){
        return new TrueNameVO(userService.selectTrueName(userId, rectification));
    }
}
