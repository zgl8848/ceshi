package com.hcdz.hk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lc
 * @date 2019年11月4日 下午1:24:28
 * @desc 告警事件码
 */
public class AlarmCode {

	public static Map<String, String> moduleMap = new HashMap<String, String>();

	static {
		//治安监控
		moduleMap.put("131585", "1");
		moduleMap.put("131659", "1");
		moduleMap.put("1157632004", "1");
		
		//校车安全
		moduleMap.put("1157632003", "2");
		moduleMap.put("1157632004", "2");
		
		//消防安全
		moduleMap.put("254075", "3");
		moduleMap.put("254069", "3");
		moduleMap.put("254023", "3");
		moduleMap.put("254057", "3");
		
		//厨房安全
		moduleMap.put("192518", "4");
		moduleMap.put("192517", "4");
		moduleMap.put("192516", "4");
		moduleMap.put("192515", "4");
		moduleMap.put("192514", "4");
		moduleMap.put("192513", "4");
		
		//危化品安全
		
		//学生行为
		moduleMap.put("131593", "6");
		moduleMap.put("131590", "6");
		moduleMap.put("131596", "6");
		moduleMap.put("131605", "6");
		
		moduleMap.put("131331", "6");
	}
	
	
}
