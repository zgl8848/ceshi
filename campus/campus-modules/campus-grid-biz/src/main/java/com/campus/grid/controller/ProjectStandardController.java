package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.service.ProjectStandardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * @author wang_h
 * @date 2019-06-20 15:30:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/projectstandard")
public class ProjectStandardController {

  private final ProjectStandardService projectStandardService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param projectStandard 
   * @return
   */
  @GetMapping("/page")
  public R getProjectStandardPage(Page page, ProjectStandard projectStandard) {
    return  new R<>(projectStandardService.page(page,Wrappers.query(projectStandard)));
  }


  /**
   * 通过id查询
   * @param standardId id
   * @return R
   */
  @GetMapping("/{standardId}")
  public R getById(@PathVariable("standardId") String standardId){
    return new R<>(projectStandardService.getById(standardId));
  }

	/**
	 * 新增
	 * @param projectId
	 * @param content
	 * @return
	 */
  @SysLog("新增")
  @RequestMapping("/save")
  public R save(String projectId,String content){
    return new R<>(projectStandardService.save(projectId,content));
  }

	/**
	 * 修改
	 * @param standardId
	 * @param content
	 * @return
	 */
  @SysLog("修改")
  @RequestMapping("/updateById")
  public R updateById(String standardId,String content,String projectId){
    return new R<>(projectStandardService.updateById(new ProjectStandard(standardId,projectId,content)));
  }

  /**
   * 通过id删除
   * @param standardId id
   * @return R
   */
  @SysLog("删除")
  @RequestMapping("/removeById")
  public R removeById(String standardId,String projectId){
    return new R<>(projectStandardService.removeById(standardId,projectId));
  }

}
