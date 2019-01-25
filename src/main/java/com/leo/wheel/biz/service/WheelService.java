package com.leo.wheel.biz.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leo.wheel.biz.mapper.WheelMapper;
import com.leo.wheel.entity.biz.WheelInfo;

/**
 * service
 * @author leo
 *
 */
@Service
public class WheelService {

	@Resource
	private WheelMapper wheelMapper;

	public WheelInfo getDataById(String id) {
		return wheelMapper.getDataById(id);
	}
}
