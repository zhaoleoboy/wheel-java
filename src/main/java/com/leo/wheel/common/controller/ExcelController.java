package com.leo.wheel.common.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.leo.wheel.utils.DateUtils;
import com.leo.wheel.utils.ExcelUtils;

/**
 * 实现Excel 的相关操作，包括： 1，导出； 2，导入；
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

	/**
	 * 导出V2007版本的Excel
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/exportExcelV2007")
	@ResponseBody
	public void exportExcelV2007(String json, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO reponse需要封装！
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		String curDateTime = DateUtils.getCurDateTime(DateUtils.DATE_FORMAT_COMPACT);
		response.setHeader("Content-disposition", String.format("attachment;filename=%s.xlsx", "excel" + curDateTime));
		ServletOutputStream out = response.getOutputStream();
		List<List<Object>> mockDatas = ExcelUtils.mockDatas();
		List<List<String>> mockHeaders = ExcelUtils.mockHeaders();
		ExcelUtils.exportV2007(out, mockDatas, mockHeaders, true, null);
		out.flush();
	}

	/**
	 * 导入V2007版本的Excel
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/importExcelV2007")
	@ResponseBody
	public void importExcelV2007(String json, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO 导入Excel2007
	}

	/**
	 * 导出V2003版本的Excel
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/exportExcelV2003")
	@ResponseBody
	public void exportExcelV2003(String json, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO 导出Excel2003
	}

	/**
	 * 导出V2003版本的Excel
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/importExcelV2003")
	@ResponseBody
	public void importExcelV2003(String json, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO 导入Excel2003
	}

}
