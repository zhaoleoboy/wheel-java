package com.leo.wheel.common.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.leo.wheel.vo.common.DownloadFileInfo;

/**
 * 
 * @author leo
 *
 */
@Service
@PropertySource("classpath:jdbc.properties") // 如果是application.properties，就不用写，其他文件需要写
public class FileService {

	/**
	 * 可以通过以下两种方式读取application.properties配置文件的属性
	 */
	@Autowired
	private Environment env;

	@Value("${file.upload}")
	private String folder;// 读取applicaton.properties配置文件，获取文件上传的目录

	/**
	 * 获取DownloadFileInfo
	 * 
	 * @param fileName 文件下载的名称
	 * @param fileId   文件在服务器的名称
	 * @return
	 * @throws FileNotFoundException
	 */
	public DownloadFileInfo downloadFile(String fileName, String fileId) throws FileNotFoundException {
		File file = new File(env.getProperty("file.upload"), fileId);
		if (!file.exists()) {
			throw new FileNotFoundException("file not exist!");
		}
		return new DownloadFileInfo(fileName, file);
	}

	/**
	 * 文件批量上传，需要保证文件上传的操作和保存数据库的操作在同一个事务当中
	 * 
	 * @param fileMap
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Transactional
	public Boolean uploadFile(Map<String, MultipartFile> fileMap) throws IllegalStateException, IOException {
		long start = System.currentTimeMillis();
		Collection<MultipartFile> values = fileMap.values();

		for (MultipartFile multipartFile : values) {
			String fileName = String.valueOf(System.currentTimeMillis());
			String originalFilename = multipartFile.getOriginalFilename();
			String showName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String subfix = originalFilename.substring(showName.length());
			File destFile = new File(folder, fileName + subfix);
			multipartFile.transferTo(destFile);
			// TODO insert数据库的操作
		}

		long end = System.currentTimeMillis();
		System.out.println(String.format("文件上传花费的时间为%s", end - start));
		return Boolean.TRUE;
	}
}
