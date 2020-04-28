package com.campus.common.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author campus
 * @date 2018/12/26
 * <p>
 * 数据权限类型
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {
	/**
	 * 查询全部数据
	 */
	ALL("0", "全部");

	/**
	 * 类型
	 */
	private final String type;
	/**
	 * 描述
	 */
	private final String description;
}
