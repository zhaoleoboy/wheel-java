package com.leo.wheel.biz.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.leo.wheel.biz.service.WheelService;
import com.leo.wheel.entity.biz.WheelInfo;
import com.leo.wheel.entity.common.RestResponse;
import com.leo.wheel.utils.GsonUtils;

/**
 * 主要实现常用的增删改查！
 * @author leo
 *
 */
@RestController
@RequestMapping("/wheel")
public class WheelController {

	@Resource
	private WheelService wheelService;

	/**
	 * 条件查询
	 * 
	 * @GetMapping = @RequestMapping(method = RequestMethod.GET)
	 * @PostMapping = @RequestMapping(method = RequestMethod.POST)
	 * @PutMapping = @RequestMapping(method = RequestMethod.PUT)
	 * @DeleteMapping = @RequestMapping(method = RequestMethod.DELETE)
	 * 
	 * @param json     查询条件
	 * @param orderBy  排序
	 * @param pageNum  第几页
	 * @param pageSize 每页几条记录
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/getDataByCondition")
	public RestResponse<PageInfo<WheelInfo>> getDataByCondition(String json, String orderBy, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> params = GsonUtils.toMap(json, Object.class);
		PageInfo<WheelInfo> pageResult = wheelService.getPageDataByCondition(params, pageNum, pageSize, orderBy);
		return RestResponse.result(pageResult);
	}

	/**
	 * 唯一查询
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getDataById", method = { RequestMethod.GET })
	public RestResponse<WheelInfo> getDataById(String id) {
		WheelInfo info = wheelService.getDataById(id);
		return RestResponse.result(info);
	}

	/**
	 * 增
	 * @param info
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/insertData", method = { RequestMethod.GET })
	public RestResponse<Integer> insertData(WheelInfo info) {
		int result = wheelService.insertData(info);
		return RestResponse.result(new Integer(result));
	}

	/**
	 * 删
	 * @param info
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public RestResponse<Integer> delete(String json) {
		Map<String, Object> params = GsonUtils.toMap(json, Object.class);
		int result = wheelService.delete(params);
		return RestResponse.result(new Integer(result));
	}

	/**
	 * 改
	 * @param info
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/updateData", method = { RequestMethod.GET })
	public RestResponse<Integer> updateData(WheelInfo info) {
		int result = wheelService.updateData(info);
		return RestResponse.result(new Integer(result));
	}
}
