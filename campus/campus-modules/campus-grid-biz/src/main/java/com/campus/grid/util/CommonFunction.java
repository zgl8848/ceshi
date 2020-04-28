package com.campus.grid.util;

/**
 * @author wangkun
 * @since 2019/1/18
 */
public class CommonFunction {

	/**
	 * 生成学校编码：八位编号（从1开始，不够前补0）
	 */
	public static String getNewSchoolCode(String schoolCode) {
		String newSchoolCode = "00000001";

		if (schoolCode != null && !schoolCode.isEmpty()) {
			int newSchool = Integer.parseInt(schoolCode) + 1;
			newSchoolCode = String.format("%08d", newSchool);
		}

		return newSchoolCode;
	}
}
