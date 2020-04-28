package com.campus.grid.service.impl;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.feign.RemoteDeptService;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.InpsectProject;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.mapper.InpsectProjectMapper;
import com.campus.grid.service.InpsectProjectService;
import com.campus.grid.service.TaskResultService;
import com.campus.grid.util.InspectHiddenUtils;

import lombok.AllArgsConstructor;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:46:45
 */
@Service
@AllArgsConstructor
public class InpsectProjectServiceImpl extends ServiceImpl<InpsectProjectMapper, InpsectProject>
		implements InpsectProjectService {

	private InpsectProjectMapper inpsectProjectMapper;

	private final RemoteDeptService remoteDeptService;

	private TaskResultService taskResultService;

	@Override
	public boolean create(InpsectProject inpsectProject) {
		List<String> contents = InspectHiddenUtils.arrayToList(new ArrayList<>(), inpsectProject.getStrInspect());
		if (StringUtils.isNotBlank(inpsectProject.getStrInspect())) {
			inpsectProject.setStandardCount(contents.size());
		} else {
			inpsectProject.setStandardCount(0);
		}
		if (StringUtils.isNotBlank(inpsectProject.getProjectId())) {
			boolean flag = taskResultService.getByProject(inpsectProject.getProjectId());
			if (flag) {// 存在已开启的任务
				return false;
			} else {
				inpsectProjectMapper.updateInpsectProject(inpsectProject);
				inpsectProjectMapper.deleteProjectStandardOfProjectId(inpsectProject.getProjectId());
			}

		} else {
			inpsectProject.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
			inpsectProject.setUserId(SecurityUtils.getUser().getId());
			inpsectProject.setDeptId(SecurityUtils.getUser().getDeptId());
			inpsectProject.setCreateTime(LocalDateTime.now());
			inpsectProjectMapper.create(inpsectProject);
		}
		if (StringUtils.isNotBlank(inpsectProject.getStrInspect())) {
			List<ProjectStandard> projectStandards = new ArrayList<>();
			for (String content : contents) {
				ProjectStandard projectStandard = new ProjectStandard(UUID.randomUUID().toString().replaceAll("-", ""),
						inpsectProject.getProjectId(), content);
				projectStandards.add(projectStandard);
			}
			inpsectProjectMapper.insertProjectStandard(projectStandards);
		}
		return true;

	}

	@Override
	public IPage getInpsectProjectPage(Page page) {
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0)
			childDepts.add("notHave");
		return inpsectProjectMapper.selectInpsectProjects(page, childDepts);
	}

	@Override
	public InpsectProject getInpsectProjectById(String projectId) {
		return inpsectProjectMapper.findInpsectProjectOfProjectId(projectId);
	}

	@Override
	public boolean deleteInpsectProjectById(String projectId) {
		try {
			boolean flag = taskResultService.getByProject(projectId);
			if (flag) {// 存在已开启的任务
				return false;
			} else {
				this.removeById(projectId);
				inpsectProjectMapper.deleteProjectStandardOfProjectId(projectId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateNameById(String projectId, String projectName) {
		try {
			boolean flag = taskResultService.getByProject(projectId);
			if (flag) {// 存在已开启的任务
				return false;
			} else {
				inpsectProjectMapper.updateNameById(projectId, projectName);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean importExcel(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		List<InpsectProject> listProject = new ArrayList<InpsectProject>();

		// HSSFWorkbook workbook2 = new HSSFWorkbook(file.getInputStream());
		// 1.得到上传的表
		Workbook workbook2 = WorkbookFactory.create(file.getInputStream());
		Sheet sheet2 = workbook2.getSheetAt(0);
		// 获取表的总行数
		int num = sheet2.getLastRowNum();
		// 总列数
		int col = sheet2.getRow(0).getLastCellNum();
		// 遍历excel每一行
		String number = "";
		InpsectProject inpsectProject = new InpsectProject();
		List<ProjectStandard> projectStandards = new ArrayList<ProjectStandard>();
		for (int j = 1; j <= num; j++) {
			Row row1 = sheet2.getRow(j);
			// 如果单元格中有数字或者其他格式的数据，则调用setCellType()转换为string类型
			Cell cell1 = row1.getCell(0);
			cell1.setCellType(CellType.STRING);
			Cell cell2 = row1.getCell(1);
			cell2.setCellType(CellType.STRING);
			Cell cell3 = row1.getCell(2);
			cell3.setCellType(CellType.STRING);
			if (j == 1 || "".equals(number)) {
				number = cell1.getStringCellValue();
				inpsectProject.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
				inpsectProject.setUserId(SecurityUtils.getUser().getId());
				inpsectProject.setDeptId(SecurityUtils.getUser().getDeptId());
				inpsectProject.setCreateTime(LocalDateTime.now());
				inpsectProject.setProjectName(cell3.getStringCellValue());
				// inpsectProject.setStandardCount(standardCount);
				listProject.add(inpsectProject);
			} else {
				if (cell1.getStringCellValue() == null || "".equals(cell1.getStringCellValue())
						|| cell3.getStringCellValue() == null || cell3.getStringCellValue() == null) {// 编号，内容不能为空
					continue;
				} else {
					if (number.equals(cell1.getStringCellValue().substring(0, number.length()))) {// 有值子类
						ProjectStandard projectStandard = new ProjectStandard(
								UUID.randomUUID().toString().replaceAll("-", ""), inpsectProject.getProjectId(),
								cell3.getStringCellValue());
						projectStandards.add(projectStandard);
						inpsectProject.setProjectStandards(projectStandards);
					} else {
						number = cell1.getStringCellValue();
						inpsectProject = new InpsectProject();
						inpsectProject.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
						inpsectProject.setUserId(SecurityUtils.getUser().getId());
						inpsectProject.setDeptId(SecurityUtils.getUser().getDeptId());
						inpsectProject.setCreateTime(LocalDateTime.now());
						inpsectProject.setProjectName(cell3.getStringCellValue());
						projectStandards = new ArrayList<ProjectStandard>();
						listProject.add(inpsectProject);
					}

				}

			}

		}

		if (listProject.size() > 0) {
			for (InpsectProject project : listProject) {
				List<ProjectStandard> standards = project.getProjectStandards();
				if (standards != null && standards.size() > 0) {
					project.setStandardCount(standards.size());
					inpsectProjectMapper.insertProjectStandard(standards);
					inpsectProjectMapper.create(project);
				}
			}
		}
		return true;
	}

	/**
	 * 获取检查项目信息
	 */
	public List<InpsectProject> getProjects() {
		List<String> childDepts = remoteDeptService.listChildDepts().getData();
		if (childDepts.size() == 0)
			childDepts.add("notHave");
		return baseMapper.getProjects(childDepts);
	}

	@Override
	public boolean updateById(InpsectProject entity) {
		boolean flag = taskResultService.getByProject(entity.getProjectId());
		if (flag) {// 存在已开启的任务
			return false;
		} else {
			return super.updateById(entity);
		}
	}

	@Override
	public void exportProject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook exl = new HSSFWorkbook();
		try {
			Sheet sheet1 = exl.createSheet("sheet1");
			sheet1.setForceFormulaRecalculation(true);// 设置公式自动读取
			String fileName = "检查项目.xls";
			int i = 0;
			List<String> childDepts = remoteDeptService.listChildDepts().getData();
			if (childDepts.size() == 0)
				childDepts.add("notHave");
			List<InpsectProject> projectList = baseMapper.getInsepectProjects(childDepts);
			if (projectList != null && projectList.size() > 0) {
				for (InpsectProject inpsectProject : projectList) {					
					Row row =null;
					if(i==0) {
						 row = sheet1.createRow(i);
					}else {
						row =sheet1.createRow(++i);
					}
					row.createCell(0).setCellValue("项目名称");
					row.createCell(1).setCellValue(inpsectProject.getProjectName());
					List<ProjectStandard> standards = baseMapper.getProjectStandardOfProjectId(inpsectProject.getProjectId());
					if (standards!= null&& standards.size() > 0) {
						int j = 0;// 判断是否为标准第一行
						for (ProjectStandard projectStandard : standards) {
							if (projectStandard.getContent() != null && !"".equals(projectStandard.getContent())) {
								row = sheet1.createRow(++i);
								if (j == 0) {
									row.createCell(0).setCellValue("检查标准");
									row.createCell(1).setCellValue(projectStandard.getContent());
								} else {
									row.createCell(1).setCellValue(projectStandard.getContent());
								}
								j++;
							}
						}
					}else {
						//i++;
					}
				}
			}

			// 激活浏览器弹出窗口
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			// 浏览器弹出窗口显示的文件名
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			response.setHeader("FileName", URLEncoder.encode(fileName, "utf-8"));
			response.setHeader("Access-Control-Expose-Headers", "FileName");
			response.setCharacterEncoding("utf-8");
			exl.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (exl != null) {
				exl.close();
			}
		}
	}

}
