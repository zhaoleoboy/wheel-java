package com.leo.wheel.common.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.leo.wheel.vo.common.DownloadFileInfo;

@Service
@PropertySource("classpath:jdbc.properties") // 如果是application.properties，就不用写，其他文件需要写
public class FileService {

	@Autowired
	private Environment env;// TODO 读取配置文件用？

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
}
