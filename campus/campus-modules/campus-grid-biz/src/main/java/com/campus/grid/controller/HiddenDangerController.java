package com.campus.grid.controller;

import com.campus.grid.api.vo.HiddenDangerVO;
import com.campus.grid.service.HiddenDangerService;
import com.campus.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hiddenDanger")
public class HiddenDangerController {
	/**
	 * 隐患录入页面加载信息
	 */
	@Autowired
	private HiddenDangerService hiddenDangerService;
	@RequestMapping("/queryPageData")
	public R queryPageData(){
		return new R<>(hiddenDangerService.queryPageData());
	}
	/**
	 * 根据网格id超找对应的功能和负责人
	 */
	@RequestMapping("/queryGrid")
	public R queryGrid(String reseauId){
		return new R<>(hiddenDangerService.queryGrid(reseauId));
	}
	/**
	 * 插入隐患信息
	 */
	@RequestMapping("/addHiddenDanger")
		public R addHiddenDanger(String itemLabel, String reseauId, String functionId, Integer level, Integer mode, String remark, String mendMan,String picNames,String eventLabels){
		return hiddenDangerService.addHiddenDanger(itemLabel,reseauId,functionId,level,mode,remark,mendMan,picNames,eventLabels);
	}
	/**
	 * 查询隐患
	 */
	@RequestMapping("/queryHiddenDanger")
		public R queryHiddenDanger(Integer current,Integer size,String date,Integer level,String functionId,Integer mode,String title,String startDate,String endDate,Integer status,String ebSchoolId,String reseauName,String type){
		return new R<>(hiddenDangerService.queryHiddenDanger(current, size, date, level, functionId, mode,title,startDate,endDate,status,ebSchoolId,null,reseauName,type));
	}

	/**
	 * 查询隐患(第二版)
	 */
	@RequestMapping("/hiddenDangerQuery")
	public R hiddenDangerQuery(HiddenDangerVO hiddenDangerVO){
		return new R<>(hiddenDangerService.hiddenDangerQuery(hiddenDangerVO));
	}

	/**
	 * 检查任务 隐患统计
	 * @param id 任务ID
	 */
	@RequestMapping("/stat/{id}")
	public R statByTask(@PathVariable String id){
		return new R<>(hiddenDangerService.statByTask(id));
	}
}
