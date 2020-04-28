package com.campus.grid.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.InpsectProject;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:46:45
 */
public interface InpsectProjectService extends IService<InpsectProject> {

	boolean create(InpsectProject inpsectProject);
	IPage getInpsectProjectPage(Page page);
	InpsectProject getInpsectProjectById(String projectId);
	boolean deleteInpsectProjectById(String projectId);
	boolean updateNameById(String projectId,String projectName);
	
	/**
	 * 检查项目导入
	 * @param file
	 * @return
	 * @throws Exception
	 */
	boolean importExcel(MultipartFile file) throws Exception;

	/**
	 * 获取检查项目信息
	 * @return
	 */
	List<InpsectProject> getProjects();
	
	/**
	 * 修改项目
	 */
	boolean updateById(InpsectProject inpsectProject);
	
	/**
	 * 检查项目导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void  exportProject(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
