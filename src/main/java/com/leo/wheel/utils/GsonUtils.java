package com.leo.wheel.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.leo.wheel.entity.common.RestResponse;

/**
 * JSON工具类，转换的时候需要注意以下事项：
 * 1，为了避免不必要的问题，字符串中的key和value都要带引号；
 * 2，字符串中带有反斜杠(\)这种转移类型的字符时，需要特殊处理；
 * 
 * @author leo
 *
 */
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
	 * 	转换对象为string
	 * @param t
	 * @return
	 */
	public static <T> String convertObj2String(T t) {
		String result = JSON.toJSONString(t);
		return result;
	}

	/**
	 * json转换为Map<String, T>
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> toMap(String json, Class<T> clazz) {
		long start = System.currentTimeMillis();
		if (json == null) {
			return null;
		}
		json = json.trim();
		if (!json.startsWith("[")) {
			//
			Map<String, T> map = JSON.parseObject(json, new TypeReference<Map<String, T>>() {
			});
			long end = System.currentTimeMillis();
			System.out.println(String.format("json转换花费时间：%s", end - start));
			return map;
		}
		return null;
	}

	public static void main(String[] args) {
		RestResponse<String> result = new RestResponse<String>();
		result.setCode("123");
		String json = "{'key1':'sss\\\\ds'}";
		Map<String, String> map = GsonUtils.toMap(json, String.class);
		System.out.println(map.get("key1"));
	}
}
