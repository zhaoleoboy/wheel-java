package com.leo.wheel.common.controller;

import javax.annotation.Resource;

import org.jodconverter.office.OfficeException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leo.wheel.common.service.DocService;

/**
 * 主要用来处理文档预览功能
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/viewer")
public class PreviewController {

	@Resource
	private DocService docService;

	/**
	 * 
	 * @param sourceFilePath 源文件的路径
	 * @return
	 * @throws OfficeException
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/preview")
	@ResponseBody
	public String preview(String sourceFilePath) throws OfficeException {
		String targetFilePath = docService.preview(sourceFilePath);
		return targetFilePath;
	}

}
