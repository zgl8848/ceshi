package com.campus.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.codegen.entity.GenConfig;
import com.campus.codegen.service.SysGeneratorService;
import com.campus.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成器
 *
 * @author hhsep
 * @date 2018-07-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {

	private final SysGeneratorService sysGeneratorService;

	/**
	 * 列表
	 *
	 * @param tableName 参数集
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public R getPage(Page page, String tableName) {
		return new R<>(sysGeneratorService.getPage(page, tableName));
	}

	/**
	 * 生成代码
	 */
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) throws IOException {
		byte[] data = sysGeneratorService.generatorCode(genConfig);

		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH,  String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}
