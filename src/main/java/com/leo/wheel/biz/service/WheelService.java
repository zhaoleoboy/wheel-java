package com.leo.wheel.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	 * 	条件查询
	 * @param params
	 * @param orderBy
	 * @return
	 */
	public List<WheelInfo> getDataByCondition(Map<String, Object> params, String orderBy) {
		return wheelMapper.getDataByCondition(params);
	}

	/**
	 * 	分页的条件查询
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	public PageInfo<WheelInfo> getPageDataByCondition(Map<String, Object> params, Integer pageNum, Integer pageSize,
			String orderBy) {
		if (MapUtils.isEmpty(params)) {
			params = new HashMap<String, Object>();
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if (StringUtils.isNotBlank(orderBy)) {
			params.put("_orderBy", orderBy);
		} else {
			params.put("_orderBy", orderBy);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<WheelInfo> result = wheelMapper.getDataByCondition(params);
		PageInfo<WheelInfo> page = new PageInfo<WheelInfo>(result);
		return page;
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
