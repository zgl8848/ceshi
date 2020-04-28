package com.campus.admin.api.feign.fallback;

import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.entity.SysDict;
import com.campus.common.core.util.R;
import com.campus.admin.api.feign.RemoteDictService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author campus
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteDictServiceFallbackImpl implements RemoteDictService {
	@Setter
	private Throwable cause;

	@Override
	public DictInfo info(String username) {
		log.error("feign 查询指定字典类型失败:{}", cause);
		return null;
	}

	@Override
	public DictInfo info(String type, String value) {
		log.error("feign 查询指定字典类型失败:{}", cause);
		return null;
	}

	@Override
	public R<List<SysDict>> list(String type) {
		log.error("feign 查询指定字典类型失败:{}", cause);
		return null;
	}


}
