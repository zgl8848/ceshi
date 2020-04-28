package com.campus.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.DeptTree;
import com.campus.admin.api.dto.InspectGroupInfo;
import com.campus.admin.api.entity.SysDept;
import com.campus.admin.api.entity.SysDeptRelation;
import com.campus.admin.api.entity.SysUser;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.admin.api.vo.TreeUtil;
import com.campus.admin.mapper.SysDeptMapper;
import com.campus.admin.mapper.SysUserMapper;
import com.campus.admin.service.SysDeptRelationService;
import com.campus.admin.service.SysDeptService;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author campus
 * @since 2018-01-20
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
	private final SysDeptRelationService sysDeptRelationService;
	private final SysUserMapper sysUserMapper;
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Autowired
	private SysDeptMapper sysDeptMapper;

	/**
	 * 添加信息部门
	 *
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDept dept) {
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);
		return Boolean.TRUE;
	}


	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R<Boolean> removeDeptById(String id) {
		//级联删除部门
		List<String> idList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, id))
				.stream()
				.map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			// 判断本级和子集部门下面是否还有人员，有的话返回给前端提示，让他先删除用户
			List<SysUser> sysUserList = sysUserMapper.listUsersByDeptIds(idList);
			if (sysUserList.size() > 0) {
				return new R<>(Boolean.FALSE, "请先删除本部门和子集部门下的用户");
			}
			this.removeByIds(idList);
		}

		//删除部门级联关系
		sysDeptRelationService.deleteAllDeptRealtion(id);
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDept sysDept) {
		//更新部门状态
		this.updateById(sysDept);
		//更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() {
		return getDeptTree(this.list(Wrappers.emptyWrapper()), "0",false);
	}

	/**
	 * 查询用户部门树
	 *
	 * @return
	 */
	@Override
	public List<DeptTree> getUserTree(Boolean isSchoolTree) {
		String deptId = SecurityUtils.getUser().getDeptId();
		SysDept sysDept = new SysDept();
		sysDept.setDeptId(deptId);
		sysDept = baseMapper.selectOne(new QueryWrapper<>(sysDept));
		// 判断接口请求的来源，如果是学校管理中的部门树的话，返回本级和子集所有部门
		if (!isSchoolTree) {
			String roleType = SecurityUtils.getUser().getRoleType();
			// 角色不是系统管理员的不能看到别的任何部门的东西
			if (!CommonConstants.ROLE_TYPE_ADMIN.equals(roleType)) {
				List<DeptTree> list = new ArrayList<>();
				DeptTree deptTree = new DeptTree();

				if (sysDept != null) {
					deptTree.setName(sysDept.getName());
					deptTree.setId(sysDept.getDeptId());
					deptTree.setParentId(sysDept.getParentId());
					deptTree.setProvince(sysDept.getProvince());
					deptTree.setCity(sysDept.getCity());
					deptTree.setCounty(sysDept.getCounty());
				}

				list.add(deptTree);
				return list;
			}
		}
		List<String> descendantIdList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, deptId))
				.stream().map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());
		List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
		return getDeptTree(deptList, sysDept.getParentId(),false);
	}

	/**
	 * 构建部门树
	 *
	 * @param depts 部门
	 * @return
	 */
	private List<DeptTree> getDeptTree(List<SysDept> depts, String root,boolean isInspectGroup) {

		List<DeptTree> treeList = depts.stream()
				.filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());
					node.setProvince(dept.getProvince());
					node.setCity(dept.getCity());
					node.setCounty(dept.getCounty());
					if (isInspectGroup){
						node.setLabel(dept.getName());
						node.setInspectGroupInfos(sysUserMapper.selectInspectGroupInfos(dept.getDeptId()));
					}
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.bulid(treeList, root);
	}

	/**
	 * 获取当前用户的子部门信息
	 *
	 * @return 子部门列表
	 */
	@Override
	public List<String> getChildDepts() {
		String deptId = SecurityUtils.getUser().getDeptId();
		List<String> collect = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, deptId))
				.stream()
				.map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());
		//获取当前部门的子部门
		return collect;
	}

	@Override
	public Map<String,Object> getAllInspectGroup() {
		Map<String,Object> map=new HashMap<>();
		SysDept sysDept=new SysDept();
		sysDept.setDeptId(SecurityUtils.getUser().getDeptId());
		sysDept = baseMapper.selectOne(new QueryWrapper<>(sysDept));
		List<String> descendantIdList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor,  SecurityUtils.getUser().getDeptId()))
				.stream().map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());
		List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
		map.put("inspectGroup",getDeptTree(deptList, sysDept.getParentId(),true));
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size()==0) childDepts=null;
		List<InspectGroupInfo> inspectProjects = sysUserMapper.selectInspectProject(childDepts);
		map.put("inspectProjects",inspectProjects);
		return map;
	}

    @Override
    public String getDeptNameById(String deptId) {
        return sysDeptMapper.selectDeptNameById(deptId);
    }

}
