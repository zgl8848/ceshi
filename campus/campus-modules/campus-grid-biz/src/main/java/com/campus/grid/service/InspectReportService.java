package com.campus.grid.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public interface InspectReportService {
	
	/**
	 * 导出报告
	 * @param request
	 * @param response
	 */
	void exportReport(HttpServletRequest request, HttpServletResponse response) 
			throws IOException,IOException, EncryptedDocumentException, InvalidFormatException ;

}
