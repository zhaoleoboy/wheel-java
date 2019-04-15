package com.leo.wheel.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作的工具类
 * 
 * @author leo
 *
 */
public class FileUtils {

	/**
	 * 	本地文件复制
	 * @param sourceFile
	 * @param destFile
	 * @return
	 * @throws IOException 
	 */
	public static String copyFile(String sourceFile, String destFile) throws IOException {
		long start = System.currentTimeMillis();
		// 下载网络的文件
		// InputStream in = new URL(sourceFile).openStream();
		// InputStream in = new FileInputStream(sourceFile);
		// Files.copy(in, Paths.get(destFile), StandardCopyOption.REPLACE_EXISTING);

		org.apache.commons.io.FileUtils.copyFile(new File(sourceFile), new File(destFile));
		long end = System.currentTimeMillis();
		System.out.println(String.format("文件下载花费的时间为%sms", end - start));
		return destFile;
	}

	/**
	 * 	TODO 从网络下载文件
	 * @param sourceFile
	 * @param destFile
	 * @return
	 * @throws IOException 
	 */
	public static String downloadFile(String sourceFile, String destFile) throws IOException {
		return null;
	}

	/**
	 * 获取文件的类型扩展名
	 * @param filePath 文件路径或文件名
	 * @return
	 */
	public static String getFileExt(String filePath) {
		String result = null;
		if (StringUtils.isEmpty(filePath)) {
			return result;
		}
		int lastIndexOf = filePath.lastIndexOf(".") + 1;
		result = filePath.substring(lastIndexOf).toLowerCase();
		return result;
	}

	/**
	 * 	通过文件的路径获取文件的名称，不包括文件的类型扩展名
	 * @param path
	 * @return
	 */
	public static String getFileNameWithoutExt(String path) {
		String result = null;
		if (StringUtils.isEmpty(path)) {
			return result;
		}
		int lastIndexOfFileSeparator = path.lastIndexOf(File.separator) + 1;
		int indexOfFileSeparator = path.lastIndexOf(".");
		result = path.substring(lastIndexOfFileSeparator, indexOfFileSeparator);
		return result;
	}

	/**
	 * 	校验文件是否存在
	 * @param path
	 * @return
	 */
	public static Boolean isFileExists(String path) {
		if (StringUtils.isBlank(path)) {
			return Boolean.FALSE;
		}
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 	获取Spring上传文件的MD5值
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getMultipartFileMD5(MultipartFile file) throws IOException {
		long start = System.currentTimeMillis();
		byte[] uploadBytes = file.getBytes();  
		byte[] md5Digest = DigestUtils.md5Digest(uploadBytes);
        String md5 = new BigInteger(1, md5Digest).toString(16);
        long end = System.currentTimeMillis();
        System.out.println(String.format("计算文件的MD5值共花费时间为：%s", end - start));
        return md5;  
	}

	public static void main(String[] args) {
		String fileNameFromPath = FileUtils.getFileNameWithoutExt("D:\\codes\\wheel-java\\ss.doc");
		System.out.println(fileNameFromPath);
	}
}
