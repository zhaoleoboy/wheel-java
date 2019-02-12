package com.leo.wheel.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件操作的工具类
 * 
 * @author leo
 *
 */
public class FileUtils {

	/**
	 * 下载文件
	 * @param sourceFile
	 * @param destFile
	 * @return
	 */
	public static String downloadFile(String sourceFile, String destFile) {
		
		return destFile;
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
	 * 通过文件的路径获取文件的名称，不包括文件的类型扩展名
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

	public static void main(String[] args) {
		String fileNameFromPath = FileUtils.getFileNameWithoutExt("D:\\codes\\wheel-java\\ss.doc");
		System.out.println(fileNameFromPath);
	}
}
