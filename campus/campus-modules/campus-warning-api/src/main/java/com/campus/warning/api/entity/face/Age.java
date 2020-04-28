package com.campus.warning.api.entity.face;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Age {
	/**
	 * 年龄段，见数据字典设备年龄段类型
	 */
    private String ageGroup;

}