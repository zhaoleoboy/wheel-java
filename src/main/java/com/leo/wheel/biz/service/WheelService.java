package com.leo.wheel.biz.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leo.wheel.biz.mapper.WheelMapper;
import com.leo.wheel.entity.biz.WheelInfo;

/**
 * service
 * 
 * @author leo
 *
 */
@Service
public class WheelService {

	@Resource
	private WheelMapper wheelMapper;

	/**
	 * 唯一性查询
	 * 
	 * @param id
	 * @return
	 */
	public WheelInfo getDataById(String id) {
		return wheelMapper.getDataById(id);
	}

	/**
	 * 条件查询
	 * 
	 * @param info
	 * @return
	 */
	public WheelInfo getDataByCondition(WheelInfo info) {
		return wheelMapper.getDataByCondition(info);
	}

	/**
	 * 插入
	 * 
	 * @param info
	 * @return
	 */
	public int insertData(WheelInfo info) {
		return 1;
	}

	/**
	 * 批量插入
	 * 
	 * @param lists
	 * @return
	 */
	public int batchInsert(List<WheelInfo> lists) {
		return lists.size();
	}

	/**
	 * 根据条件删除
	 * @param params
	 * @return
	 */
	public int delete(Map<String, Object> params) {
		return params.size();
	}

	/**
	 * 更新
	 * 
	 * @param info
	 * @return
	 */
	public int updateData(WheelInfo info) {
		return 1;
	}
}
