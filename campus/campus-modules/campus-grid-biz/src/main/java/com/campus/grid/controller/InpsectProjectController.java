package com.campus.grid.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.InpsectProject;
import com.campus.grid.service.InpsectProjectService;

import lombok.AllArgsConstructor;

/**
 * @author campus
 * @date 2019-06-20 15:46:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inpsectproject")
public class InpsectProjectController {

	private final InpsectProjectService inpsectProjectService;

	/**
	 * 分页查询
	 *
	 * @param current           分页对象
	 * @param size
	 * @return
	 */
	@GetMapping("/page")
	public R getInpsectProjectPage(Page page) {
		return new R<>(inpsectProjectService.getInpsectProjectPage(page));
	}


	/**
	 * 通过id查询
	 *
	 * @param projectId id
	 * @return R
	 */
	@GetMapping("/{projectId}")
	public R getById(@PathVariable("projectId") String projectId) {

		return new R<>(inpsectProjectService.getInpsectProjectById(projectId));
	}

	/**
	 * 新增
	 *
	 * @param inpsectProject
	 * @return R
	 */
	@SysLog("新增")
	@PostMapping
	public R save(@RequestBody InpsectProject inpsectProject) {
		return new R<>(inpsectProjectService.create(inpsectProject));
	}

	/**
	 * 修改
	 *
	 * @param inpsectProject
	 * @return R
	 */
	@SysLog("修改")
	@PutMapping
	public R updateById(@RequestBody InpsectProject inpsectProject) {
		return new R<>(inpsectProjectService.updateById(inpsectProject));
	}

	/**
	 * 通过id删除
	 *
	 * @param projectId id
	 * @return R
	 */
	@SysLog("删除")
	@DeleteMapping("/{projectId}")
	public R removeById(@PathVariable String projectId) {
		return new R<>(inpsectProjectService.deleteInpsectProjectById(projectId));
	}

	/**
	 * 通过id修改检查项目名称
	 * @param projectId
	 * @param projectName
	 * @return
	 */
	@SysLog("通过id修改检查项目名称")
	@GetMapping("/updateNameById")
	public R updateNameById(String projectId,String projectName) {
		return new R<>(inpsectProjectService.updateNameById(projectId,projectName));
	}
	
	@RequestMapping(value = "/excelImport", method = RequestMethod.POST)    
	public R uploadImg(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception {  
		boolean flag =inpsectProjectService.importExcel(file);
        if(flag){
        	return new R<>("上传成功！");   
        }else{
        	return new R<>("上传失败！");  
        }
	}

	/**
	 * 获取检查项目信息
	 *
	 */
	@GetMapping("/list")
	public R getProject() {
		return new R<>(inpsectProjectService.getProjects());
	}
	
	/**
	 * 检查项目导出
	 */
	@RequestMapping("/exportProject")
	public void exportProject(HttpServletRequest request, HttpServletResponse response)throws Exception {
		inpsectProjectService.exportProject(request,response);
	}
}
