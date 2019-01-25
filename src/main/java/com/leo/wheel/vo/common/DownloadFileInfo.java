package com.leo.wheel.vo.common;

import java.io.File;

public class DownloadFileInfo {
	private String fileName;
	private File file;

	public DownloadFileInfo(String fileName, File file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
