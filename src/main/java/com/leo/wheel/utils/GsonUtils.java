package com.leo.wheel.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.leo.wheel.entity.common.RestResponse;

public class GsonUtils {

	/**
	 * 
	 * @param json
	 * @param token
	 * @return
	 */
	public static <T> T toObject(String json, TypeReference<T> token) {
		if (json == null)
			return null;
		T object = JSON.parseObject(json.trim(), token);
		return object;
	}

	/**
	 * json转换为Map<String, T>
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> toMap(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		json = json.trim();
		if (!json.startsWith("[")) {
			//
			Map<String, T> map = JSON.parseObject(json, new TypeReference<Map<String, T>>() {
			});
			return map;
		}
		return null;
	}

	public static void main(String[] args) {
		RestResponse<String> result = new RestResponse<String>();
		result.setCode("123");
		String json = "{'key1':'ssss'}";
		Map<String, String> map = GsonUtils.toMap(json, String.class);
		System.out.println(map.get("key1"));
	}
}
