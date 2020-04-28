package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.alarmManagement.AlarmHistory;
import com.campus.grid.service.AlarmHistoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/alarmHistory")
@Api(value = "alarmHistory", description = "历史告警模块")
public class AlarmHistoryController {

	private final AlarmHistoryService alarmHistoryService;

	/**
	 * 分页查询历史告警
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, AlarmHistory alarmHistory) {
		return new R<>(alarmHistoryService.page(page, Wrappers.query(alarmHistory)));
	}
}
