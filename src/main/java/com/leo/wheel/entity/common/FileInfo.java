package com.leo.wheel.entity.common;

import java.util.Date;

/**
 * 
 * @author leo
 *
 */
public class FileInfo {
	
	public FileInfo() {
		super();
	}

	public FileInfo(String originalName, String path, String md5) {
		super();
		this.originalName = originalName;
		this.path = path;
		this.md5 = md5;
	}

	/**
	 * 	文件的原始名称
	 */
	private String originalName;

	/**
	 * 	文件在服务器的相对路径
	 */
	private String path;

	/**
	 * 	文件的MD5
	 */
	private String md5;

	/**
	 * 	上传人
	 */
	private String uploader;

	/**
	 * 	上传时间
	 */
	private Date uploadTime;

	/**
	 * @return the originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * @param originalName the originalName to set
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the uploader
	 */
	public String getUploader() {
		return uploader;
	}

	/**
	 * @param uploader the uploader to set
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
