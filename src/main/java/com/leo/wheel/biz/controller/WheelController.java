package com.leo.wheel.biz.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leo.wheel.biz.service.WheelService;
import com.leo.wheel.entity.biz.WheelInfo;
import com.leo.wheel.entity.common.RestResponse;
import com.leo.wheel.utils.GsonUtils;

@RestController
@RequestMapping("/wheel")
public class WheelController {

	@Resource
	private WheelService wheelService;

	/**
	 * 条件查询
	 * 
	 * @param json     查询条件
	 * @param orderBy  排序
	 * @param pageNum  第几页
	 * @param pageSize 每页几条记录
	 * @return
	 */
	@RequestMapping(value = "/getDataByCondition", method = { RequestMethod.GET })
	public RestResponse<List<WheelInfo>> getDataByCondition(String json, String orderBy, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> map = GsonUtils.toMap(json, Object.class);
		return null;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getDataById", method = { RequestMethod.GET })
	public RestResponse<WheelInfo> getDataById(String id) {
		WheelInfo info = wheelService.getDataById(id);
		return RestResponse.result(info);
	}
}
