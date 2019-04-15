package com.leo.wheel.common.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.leo.wheel.common.mapper.FileMapper;
import com.leo.wheel.entity.common.FileInfo;
import com.leo.wheel.utils.FileUtils;
import com.leo.wheel.utils.GsonUtils;
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

	@Resource
	private FileMapper fileMapper;

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
	 * 
	 * @param fileId
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Boolean checkFile(String path) throws FileNotFoundException {
		path = folder + path;
		if (!FileUtils.isFileExists(path)) {
			throw new FileNotFoundException("文件不存在，请联系管理员！");
		}
		return Boolean.TRUE;
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

		List<FileInfo> fileList = new ArrayList<FileInfo>();
		for (MultipartFile multipartFile : values) {
			String fileName = String.valueOf(System.currentTimeMillis());
			String originalFileName = multipartFile.getOriginalFilename();
			String showName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
			String subfix = originalFileName.substring(showName.length());
			String md5 = FileUtils.getMultipartFileMD5(multipartFile);
			File destFile = new File(folder, fileName + subfix);
			multipartFile.transferTo(destFile);
			FileInfo fileInfo = new FileInfo(originalFileName, fileName + subfix, md5);
			System.out.println(GsonUtils.convertObj2String(fileInfo));
			fileList.add(fileInfo);
			
		}
		// 保存数据库
		// batchInsertFileList(fileList);
		long end = System.currentTimeMillis();
		System.out.println(String.format("文件上传花费的时间为%s", end - start));
		return Boolean.TRUE;
	}

	/**
	 * 	删除文件，分两步：
	 * 1，删除数据库记录；
	 * 2，删除文件，删除之前需要校验该文件的MD5值是否被数据库中的其他记录所引用，如果被引用，不能删除文件，否则再删除文件；
	 * @return
	 */
	public int deleteFile() {
		// TODO
		// removeFile()
		// deleteFileInfo()
		return 0;
	}

	/**
	 * 	保存数据存储记录
	 * @param info
	 * @return
	 */
	public int insertFileInfo(FileInfo info) {
		return fileMapper.insertFile(info);
	}

	/**
	 * 	保存数据存储记录
	 * @param info
	 * @return
	 */
	public int batchInsertFileList(List<FileInfo> fileList) {
		return fileMapper.batchInsertFileList(fileList);
	}
}
