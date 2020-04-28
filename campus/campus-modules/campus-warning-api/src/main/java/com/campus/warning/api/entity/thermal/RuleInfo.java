package com.campus.warning.api.entity.thermal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleInfo {
	/**
	 * 规则1
	 */
    private Rule1 rule1;
    /**
	 * 规则2
	 */
    private Rule2 rule2;

}