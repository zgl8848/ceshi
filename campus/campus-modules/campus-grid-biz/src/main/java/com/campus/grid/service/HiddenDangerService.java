package com.campus.grid.service;

import com.campus.common.core.util.R;
import com.campus.grid.api.vo.HiddenDangerVO;

import java.util.Map;

public interface HiddenDangerService {
	//隐患录入页面加载信息
	Map<Object,Object> queryPageData();
	//根据网格id超找对应的功能和负责人
	Map<Object,Object> queryGrid(String reseauId);
	//插入隐患信息
	R addHiddenDanger(String itemLabel, String reseauId, String functionId, Integer level, Integer mode, String remark, String mendMan,String picNames,String eventLabels);
	//查询隐患
	Map<Object,Object> queryHiddenDanger(Integer current, Integer size, String date, Integer level, String functionId, Integer mode,String title,String startDate,String endDate,Integer status,String ebSchoolId,String task,String reseauName,String type);

	/**
	 * 检查任务 隐患统计
	 * @param taskId
	 * @return
	 */
	Map<String, Object> statByTask(String taskId);

	//查询隐患(第二版)
	Map<Object,Object> hiddenDangerQuery(HiddenDangerVO hiddenDangerVO);
}
