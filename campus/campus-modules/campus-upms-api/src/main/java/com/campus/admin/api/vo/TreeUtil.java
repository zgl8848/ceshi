package com.campus.admin.api.vo;


import com.campus.admin.api.dto.InspectGroupInfo;
import com.campus.admin.api.dto.MenuTree;
import com.campus.admin.api.dto.TreeNode;
import com.campus.admin.api.entity.SysMenu;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author campus
 * @date 2017年11月9日23:34:11
 */
@UtilityClass
public class TreeUtil {
	/**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();

		for (T treeNode : treeNodes) {
			if (treeNode.getInspectGroupInfos()!=null && treeNode.getInspectGroupInfos().size()!=0){
				for (InspectGroupInfo inspectGroupInfo : treeNode.getInspectGroupInfos()) {
					TreeNode treeNode1=new TreeNode();
					treeNode1.setId(inspectGroupInfo.getId());
					treeNode1.setLabel(inspectGroupInfo.getLabel());
					treeNode.add(treeNode1);
				}
			}

			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);
			}

			for (T it : treeNodes) {
				if (StringUtils.isNotBlank(it.getParentId()) && StringUtils.isNotBlank(it.getId())) {
					if (it.getParentId().equals(treeNode.getId())) {
						if (treeNode.getChildren() == null) {
							treeNode.setChildren(new ArrayList<>());
						}
						treeNode.add(it);
					}
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 *
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId() == it.getParentId()) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}

	/**
	 * 通过sysMenu创建树形节点
	 *
	 * @param menus
	 * @param root
	 * @return
	 */
	public List<MenuTree> bulidTree(List<SysMenu> menus, String root) {
		List<MenuTree> trees = new ArrayList<>();
		MenuTree node;
		for (SysMenu menu : menus) {
			node = new MenuTree();
			node.setId(menu.getMenuId());
			node.setParentId(menu.getParentId());
			node.setName(menu.getName());
			node.setPath(menu.getPath());
			node.setCode(menu.getPermission());
			node.setLabel(menu.getName());
			node.setComponent(menu.getComponent());
			node.setIcon(menu.getIcon());
			node.setKeepAlive(menu.getKeepAlive());
			trees.add(node);
		}
		return TreeUtil.bulid(trees, root);
	}
}
