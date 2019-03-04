package com.leo.wheel.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
	 * 	在线预览功能，支持：
	 * 	Word、Excel、PDF、TXT、常见图片格式的文件；
	 * @param sourceFilePath 源文件的路径
	 * @return
	 * @throws OfficeException
	 * @throws IOException 
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/preview")
	@ResponseBody
	public void preview(String sourceFilePath, HttpServletResponse response) throws OfficeException, IOException {
		String targetFilePath = docService.preview(sourceFilePath);
		// TODO 待完善
		File file = new File(targetFilePath);
		FileInputStream input = new FileInputStream(file);
		byte[] data = new byte[input.available()];
		input.read(data);
		response.getOutputStream().write(data);
		input.close();
	}

}
