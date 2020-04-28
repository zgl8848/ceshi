package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.grid.api.entity.SchoolImg;
import com.campus.grid.service.GridPostUserService;
import com.campus.grid.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eatheryu
 */
@RestController
@AllArgsConstructor
@RequestMapping("/school")
public class AreaController {

	private final GridPostUserService gridPostUserService;
	private final SchoolService schoolService;

	/**
	 * 根据schooId获取校长信息
	 */
	@GetMapping("/headmaster/{schoolId}")
		public R getHeadMaster(@PathVariable String schoolId) {
			List<HashMap<String, String>> sysUsers = gridPostUserService.getUserBySchooId(schoolId);
			return new R<>(sysUsers);
	}

	/**
	 * 平安校园图册接口
	 */
	@GetMapping("/imgpage")
	public R getSchoolImg(@RequestParam(value = "schoolId") String schoolId,
						  @RequestParam(value = "position") String position,
						  @RequestParam(value = "startDate") String startDate,
						  @RequestParam(value = "endDate") String endDate) {
		Map returnMap = new HashMap();
		List<SchoolImg> schoolImgArrayList = schoolService.getSchoolImgByTime(schoolId, startDate, endDate);
		returnMap.put("records", schoolImgArrayList);
		return new R<>(returnMap);
	}
}
