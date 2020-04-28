package com.campus.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.UserDTO;
import com.campus.admin.api.dto.UserInfo;
import com.campus.admin.api.entity.SysDept;
import com.campus.admin.api.entity.SysRole;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.entity.SysUserRole;
import com.campus.admin.api.vo.MenuVO;
import com.campus.admin.api.vo.TrueNameVO;
import com.campus.admin.api.vo.UserVO;
import com.campus.admin.face.FaceUserFeatureValue;
import com.campus.admin.mapper.SysUserMapper;
import com.campus.admin.mapper.SysUserRoleMapper;
import com.campus.admin.service.*;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.R;
import com.campus.common.data.datascope.DataScope;
import com.campus.common.security.service.CustomUser;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.feign.RemoteReseauService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author campus
 * @date 2017/10/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	private final SysMenuService sysMenuService;
	private final SysRoleService sysRoleService;
	private final SysDeptService sysDeptService;
	private final SysUserRoleService sysUserRoleService;
	private final RemoteReseauService remoteReseauService;
	private final CacheManager cacheManager;
	@Autowired
	private FaceUserFeatureValue faceUserFeatureValue;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R saveUser(UserDTO userDto) {
		List<SysRole> roles = userDto.getRole().stream().map(roleId -> {
			SysRole sysRole = sysRoleService.getById(roleId);
			return sysRole;
		}).collect(Collectors.toList());
		// 判断有没有不同的角色类型
		for (int i = 0; i < roles.size(); i++) {
			if (!roles.get(0).getRoleType().equals(roles.get(i).getRoleType())) {
				return new R(false, "一个用户不能同时拥有两种角色类型");
			}
		}
		if (StringUtils.isNotBlank(userDto.getSchoolId())){
			if(userDto.getRole().contains("7")){
				Integer integer = sysUserRoleMapper.selectInspectionOfSchool(userDto.getSchoolId());
				if (integer>0){
					return new R(false, "一个学校不能同时拥有两个督察人员");
				}
			}
		}
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);
		List<SysUserRole> userRoleList = userDto.getRole()
				.stream().map(roleId -> {
					SysUserRole userRole = new SysUserRole();
					userRole.setUserId(sysUser.getUserId());
					userRole.setRoleId(roleId);
					return userRole;
				}).collect(Collectors.toList());
		boolean b = sysUserRoleService.saveBatch(userRoleList);
		if (b) {
			return new R(true, "保存成功");
		}
		return new R(false, "保存失败");
	}

	/**
	 * 通过查用户的全部信息
	 *
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		//设置角色列表  （ID）
		List<String> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
				.stream()
				.map(SysRole::getRoleId)
				.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, String.class));

		//设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roleIds.forEach(roleId -> {
			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
					.stream()
					.filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
					.map(MenuVO::getPermission)
					.collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));

		// 设置角色类型
		List<String> roleTypes = sysRoleService.findRolesByUserId(sysUser.getUserId())
				.stream()
				.map(SysRole::getRoleType)
				.limit(1)
				.collect(Collectors.toList());
		userInfo.setRoleType(roleTypes.get(0));
		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
		CustomUser user = SecurityUtils.getUser();
		if (StringUtils.isNotBlank(user.getRoleType())) {
			if (!CommonConstants.ROLE_TYPE_ADMIN.equals(user.getRoleType())) {
				userDTO.setRoleType(user.getRoleType());
			}
			if (CommonConstants.ROLE_TYPE_SCHOOL.equals(user.getRoleType())) {
				userDTO.setSchoolId(user.getSchoolId());
			}
		}
		return baseMapper.getUserVosPage(page, userDTO, new DataScope());
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(String id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getUserId());
		this.removeById(sysUser.getUserId());
		remoteReseauService.updateUserIsNull(sysUser.getUserId());
		return Boolean.TRUE;
	}

	/**
	 * 修改某个用户的密码
	 *
	 * @param userDto 用户信息
	 * @return
	 */
	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public R<Boolean> updateUserPasswordById(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword()) && StrUtil.isNotBlank(userDto.getUserId())) {
			sysUser.setUserId(userDto.getUserId());
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
			if (baseMapper.updateById(sysUser) > 0) {
				return new R<>(Boolean.TRUE, "修改成功");
			}
		}
		return new R<>(Boolean.FALSE, "修改失败");
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public R<Boolean> updateUserInfo(UserDTO userDto) {
		UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword())
				&& StrUtil.isNotBlank(userDto.getNewpassword1())) {
			// 如果传入的原始密码是加密的，用ENCODER.matches比对的话会失败。
			// 所以在用equals比对一下，只要满足其中的一个条件就证明他们是相等的。
			if (ENCODER.matches(userDto.getPassword(), userVO.getPassword())
					|| userDto.getPassword().equals(userVO.getPassword())) {
				sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
			} else {
				log.warn("原密码错误，修改密码失败:{}", userDto.getUsername());
				return new R<>(Boolean.FALSE, "原密码错误，修改失败");
			}
		}
		sysUser.setUserId(userVO.getUserId());
		// 这里需要判断一下 PC和APP传入的参数不同，需要进行判断防止数据为空
		if (StringUtils.isNotBlank(userDto.getPhone())) {
			sysUser.setPhone(userDto.getPhone());
		}
		if (StringUtils.isNotBlank(userDto.getAvatar())) {
			sysUser.setAvatar(userDto.getAvatar());
		}

		if (userDto.getBirthday() != null) {
			sysUser.setBirthday(userDto.getBirthday());
		}
		if (StringUtils.isNotBlank(userDto.getRemark())) {
			sysUser.setRemark(userDto.getRemark());
		}
		if (StringUtils.isNotBlank(userDto.getTrueName())) {
			sysUser.setTrueName(userDto.getTrueName());
		}
		if (StringUtils.isNotBlank(userDto.getSex())) {
			sysUser.setSex(userDto.getSex());
		}
		if (StringUtils.isNotBlank(userDto.getLockFlag())) {
			sysUser.setLockFlag(userDto.getLockFlag());
		}
		try {
//			修改头像需要先将人脸特征值进行提取
			if (StringUtils.isNotBlank(userDto.getAvatar())){
				faceUserFeatureValue.getFaceUserFeatureValuye(sysUser.getUserId(),sysUser.getAvatar());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new R<>(Boolean.FALSE, "非人脸头像,修改失败");
		}
		if (this.updateById(sysUser)) {
			return new R<>(Boolean.TRUE, "修改成功");
		}
		return new R<>(Boolean.FALSE, "修改失败");
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public Boolean updateUser(UserDTO userDto) {
		if (StringUtils.isNotBlank(userDto.getSchoolId())){
			if(userDto.getRole().contains("7")){
				Integer integer = sysUserRoleMapper.selectInspectionOfSchool(userDto.getSchoolId());
				if (integer>0){
					return Boolean.FALSE;
				}
			}
		}
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);

		// 修改的时候顺便把网格责任人修改一下
		remoteReseauService.updateUserNameByUserId(sysUser.getUserId(), sysUser.getTrueName());
		sysUserRoleService.remove(Wrappers.<SysUserRole>update().lambda()
				.eq(SysUserRole::getUserId, userDto.getUserId()));
		userDto.getRole().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(roleId);
			userRole.insert();
		});
		return Boolean.TRUE;
	}

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@Override
	public List<SysUser> listAncestorUsers(String username) {
		SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda()
				.eq(SysUser::getUsername, username));

		SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
		if (sysDept == null) {
			return null;
		}

		String parentId = sysDept.getParentId();
		return this.list(Wrappers.<SysUser>query().lambda()
				.eq(SysUser::getDeptId, parentId));
	}

	/**
	 * 修改所有用户的状态信息
	 *
	 * @param schoolId
	 * @param lockFlag
	 * @return
	 */
	@Override
	public R<Boolean> updateUserLockFlagStatus(String schoolId, String lockFlag) {
		if (StringUtils.isNotBlank(schoolId) && StringUtils.isNotBlank(lockFlag)) {
			// 锁定用户
			if (baseMapper.updateUserLockFlagStatus(schoolId, lockFlag) > 0) {
				// 根据学校编号查询出来所有的用户信息。再重新更新一下缓存。不然的话即使锁定用户了，依然可以登录。
				List<UserVO> listUsers = baseMapper.listUsersBySchoolId(schoolId);

				if (listUsers.size() > 0) {
					// 更新缓存
					listUsers.stream().forEach(user -> cacheManager.getCache("user_details").evict(user.getUsername()));
				}
				return new R<>(true, "修改用户状态信息成功");
			}
		}
		return new R<>(false, "修改用户状态信息失败");
	}

    @Override
    public TrueNameVO selectTrueName(String userId, String rectification) {
        return sysUserMapper.selectTrueName(userId, rectification);
    }
}
