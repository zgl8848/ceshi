package com.campus.grid.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.campus.grid.api.dto.HiddenDangerDTO;
import com.campus.grid.api.entity.InspectTask;
import com.campus.grid.service.HiddenDangerService;
import com.campus.grid.service.InspectReportService;
import com.campus.grid.service.InspectTaskService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class InspectReportServiceImpl implements InspectReportService{
	
	public static final String XLS = ".xls";
	public static final String XLSX = ".xlsx";
	
	@Autowired
	private final HiddenDangerService hiddenService;
	
	@Autowired
	private final InspectTaskService inspectTaskService;
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void exportReport(HttpServletRequest request, HttpServletResponse response) throws IOException, EncryptedDocumentException, InvalidFormatException {
		String taskId=request.getParameter("taskId");
		Resource resource = new ClassPathResource("static/InspectReportTemplate.xls");
	//	boolean isFile = resource.isFile();
    //    if(!isFile){     //如果不存在返回
     //       return;
     //   }
        String fileName = "汇总报告.xls";
        InputStream in = null;
        HSSFWorkbook exl=null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = resource.getInputStream();
          //使用POIFSFileSystem构造HSSFWorkbook
            POIFSFileSystem fs = new POIFSFileSystem(in);
            exl=new HSSFWorkbook(fs);
            Sheet sheet1 = exl.getSheetAt(0);
            sheet1.setForceFormulaRecalculation(true);//设置公式自动读取
               
            //学校统计数据
    		Map<String, Integer> map=inspectTaskService.statSchoolByType(taskId);
   
    		//隐患情况数据
    		Map<String, Object> mapDanger=hiddenService.statByTask(taskId);
    		Row rowTtile = sheet1.getRow(0);
   // 		rowTtile.getCell(0)	.setCellValue(new String("汇总报告".getBytes("UTF-8"),"ISO-8859-1"));	
    		Row rowTtile1 = sheet1.getRow(1);
    		Row rowSchool = sheet1.getRow(5);//总体情况
    		Row rowLevel = sheet1.getRow(10);//隐患情况
    		
    		Row row22 = sheet1.getRow(22);	//隐患整改情况
			SimpleDateFormat smt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			row22.getCell(0).setCellValue(smt.format(new Date()));//截至日期
    		
    		if(map!=null) {
    			rowSchool.getCell(0).setCellValue(map.get("1")+map.get("2")+map.get("3")+map.get("4"));  //检查单位	
    			rowSchool.getCell(2).setCellValue(map.get("1"));     //检查幼儿园
    			rowSchool.getCell(3).setCellValue(map.get("2"));      //检查小学
    			rowSchool.getCell(4).setCellValue(map.get("3")); //检查初中
    			rowSchool.getCell(5).setCellValue(map.get("4"));  //检查高中
    		}
    		
    		if(mapDanger!=null) {
    			if(mapDanger.get("task")!=null) {
    				InspectTask task=(InspectTask) mapDanger.get("task");
    				rowTtile.getCell(0)	.setCellValue("《"+task.getTaskName()+"》汇总报告");	
    				SimpleDateFormat sat=new SimpleDateFormat("yyyy-MM-dd");
    				rowTtile1.getCell(4).setCellValue("检查时间："+sat.format(task.getStartDate())
    						+"至"+sat.format(task.getEndDate()));	
    				fileName="《"+task.getTaskName()+"》"+fileName;
    				
    			}
    			if(mapDanger.get("dept")!=null) {
    				
    				rowTtile1.getCell(0).setCellValue("检查范围："+mapDanger.get("dept"));	
    			}
    			if(mapDanger.get("level")!=null) {
    				Map<String, Integer> mapLevel=(Map<String, Integer>) mapDanger.get("level");
    				rowLevel.getCell(0).setCellValue(mapLevel.get("1")==null?0:mapLevel.get("1"));	
    				rowLevel.getCell(1).setCellValue(mapLevel.get("2")==null?0:mapLevel.get("2"));	
    				rowLevel.getCell(2).setCellValue(mapLevel.get("3")==null?0:mapLevel.get("3"));
    				int dangerCount=(mapLevel.get("1")==null?0:mapLevel.get("1"))+(mapLevel.get("2")==null?0:mapLevel.get("2"))+
    						(mapLevel.get("3")==null?0:mapLevel.get("3"));
    				rowSchool.getCell(6).setCellValue(dangerCount);  //检查高中
					row22.getCell(2).setCellValue(dangerCount);
    				if(mapDanger.get("rectification")!=null) {
    					int rectification=(Integer)(mapDanger.get("rectification")==null?0:mapDanger.get("rectification"));
    					row22.getCell(4).setCellValue(rectification);//完成整改	
    					row22.getCell(6).setCellValue(dangerCount-rectification);//剩余隐患
        			}else {
        				row22.getCell(4).setCellValue(0);//完成整改	
    					row22.getCell(6).setCellValue(dangerCount-0);//剩余隐患
        			}
    			}else {
    				rowLevel.getCell(0).setCellValue(0);	
    				rowLevel.getCell(1).setCellValue(0);	
    				rowLevel.getCell(2).setCellValue(0);
    				rowSchool.getCell(6).setCellValue(0);  //检查高中
					row22.getCell(2).setCellValue(0);
					if(mapDanger.get("rectification")!=null) {
    					int rectification=(Integer)(mapDanger.get("rectification")==null?0:mapDanger.get("rectification"));
    					row22.getCell(4).setCellValue(rectification);//完成整改	
    					row22.getCell(6).setCellValue(0-rectification);//剩余隐患
        			} else {
        				row22.getCell(4).setCellValue(0);//完成整改	
    					row22.getCell(6).setCellValue(0);//剩余隐患
        			}
    			}
    			if(mapDanger.get("type")!=null) {
    				Map<String, Integer> mapType=(Map<String, Integer>) mapDanger.get("type");
    				rowLevel.getCell(3).setCellValue(mapType.get("1"));	
    				rowLevel.getCell(4).setCellValue(mapType.get("2"));	
    				rowLevel.getCell(5).setCellValue(mapType.get("3"));	
    				rowLevel.getCell(6).setCellValue(mapType.get("4"));  //检查高中
    			}else {
    				rowLevel.getCell(3).setCellValue(0);	
    				rowLevel.getCell(4).setCellValue(0);	
    				rowLevel.getCell(5).setCellValue(0);	
    				rowLevel.getCell(6).setCellValue(0);  //检查高中
    			}
    			
    			if(mapDanger.get("school")!=null) {
    				List<HiddenDangerDTO> listSchool=(List<HiddenDangerDTO>) mapDanger.get("school");
    				if(listSchool.size()>0) {
    					for(int i=0;i<listSchool.size();i++) {
    						Row rowSch = sheet1.getRow(i+14);
    						rowSch.getCell(0).setCellValue(listSchool.get(i).getSchoolName());
    						////学校类型 1-幼儿园 2-小学 3-初中 4-高中
    						if("1".equals(listSchool.get(i).getSchoolType())) {
    							rowSch.getCell(2).setCellValue("幼儿园");      
    						}else if("2".equals(listSchool.get(i).getSchoolType())) {
    							rowSch.getCell(2).setCellValue("小学 ");     
    						}else if("3".equals(listSchool.get(i).getSchoolType())) {
    							rowSch.getCell(2).setCellValue("初中 ");     
    						}else if("4".equals(listSchool.get(i).getSchoolType())) {
    							rowSch.getCell(2).setCellValue("高中 ");     
    						}
    						rowSch.getCell(4).setCellValue(listSchool.get(i).getReportTime()); 
    						rowSch.getCell(6).setCellValue(listSchool.get(i).getCount()==null?0:listSchool.get(i).getCount());
    					}    					
    				}   				
    			}	
    		}
            //激活浏览器弹出窗口
    		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //浏览器弹出窗口显示的文件名
    		 response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
    	     response.setHeader("FileName", URLEncoder.encode(fileName, "utf-8"));
    	     response.setHeader("Access-Control-Expose-Headers", "FileName");
    		 response.setCharacterEncoding("utf-8");
            exl.write(response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (exl != null) {
                    exl.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}

	
	
	//类型转换
	private Workbook buildWorkbook(String filename,InputStream in) throws IOException {
		if (filename.endsWith(XLS)) {
		return new HSSFWorkbook(in);
		} else if (filename.endsWith(XLSX)) {
		return new XSSFWorkbook(in);
		} else {
		throw new IOException("unknown file format: " + filename);
		}
	}

}
