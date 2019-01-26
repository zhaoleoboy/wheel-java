package com.leo.wheel.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.leo.wheel.common.service.FileService;
import com.leo.wheel.entity.common.RestResponse;
import com.leo.wheel.vo.common.DownloadFileInfo;

/**
 * 文件上传、下载
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

	@Resource
	private FileService fileService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/downloadFile")
	@ResponseBody
	public ResponseEntity<org.springframework.core.io.Resource> downloadFile(String fileId, String fileName)
			throws IOException {
		DownloadFileInfo fileInfo = fileService.downloadFile(fileName, fileId);
		return downloadResponse(fileInfo);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/uploadFile")
	public RestResponse<Boolean> uploadFile(HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
		fileService.uploadFile(fileMap);
		return RestResponse.result(Boolean.TRUE);
	}

	/**
	 * 文件下载
	 * 
	 * @param fileInfo
	 * @return
	 */
	protected ResponseEntity<org.springframework.core.io.Resource> downloadResponse(DownloadFileInfo fileInfo) {
		File file = fileInfo.getFile();
		String fileName = fileInfo.getFileName();
		org.springframework.core.io.Resource body = new FileSystemResource(file);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String header = request.getHeader("User-Agent").toUpperCase();
		HttpStatus status = HttpStatus.CREATED;
		try {
			if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
				// IE下载文件名空格变+号问题
				fileName = URLEncoder.encode(fileName, "UTF-8");
				fileName = fileName.replace("+", "%20");
				status = HttpStatus.OK;
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentLength(file.length());
		return new ResponseEntity<org.springframework.core.io.Resource>(body, headers, status);
	}

}
