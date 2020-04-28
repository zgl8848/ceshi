package com.campus.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * @author campus
 * @date 2018/1/20
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县
	 */
	private String county;
}
