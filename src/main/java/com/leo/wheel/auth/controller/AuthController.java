package com.leo.wheel.auth.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leo.wheel.entity.auth.User;
import com.leo.wheel.entity.common.RestResponse;
import com.leo.wheel.utils.GsonUtils;

/**
 * 登录校验
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	/**
	 * 通过用户名和密码登录
	 * 
	 * @param      <T>
	 * @param json
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/loginByUser", method = { RequestMethod.GET })
	public RestResponse<User> loginByUser(String json) {
		// TODO 添加随机验证码
		// TODO 添加https？
		Map<String, String> map = GsonUtils.toMap(json, String.class);
		RestResponse<User> result = new RestResponse<User>();
		if (map == null) {
			result.setMessage("error");
			return result;
		}
		String username = map.get("user");
		String pwd = map.get("pwd");
		if ("admin".equalsIgnoreCase(username) && "123".equals(pwd)) {
			result.setMessage("ok");
			User user = new User();
			user.setId(10000);
			user.setName("leo");
			user.setTime(new Date());
			user.setEmail("zhaoleoboy@163.com");
			user.setToken("token123123");
			result.setResult(user);
			return result;
		}
		return result;
	}
}
