package com.leo.wheel.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.leo.wheel.entity.common.FileInfo;

@Mapper
public interface FileMapper {

	/**
	 * 	单个上传文件
	 * @param info
	 * @return
	 */
	public int insertFile(FileInfo info);

	/**
	 * 	批量上传文件
	 * @param files
	 * @return
	 */
	public int batchInsertFileList(List<FileInfo> fileList);
}
