package com.leo.wheel;

import java.util.HashMap;
import java.util.Map;

public class TestReference {

	public void setByParam(Map<String, String> map) {
		map.put("aaa", "testaaa");
	}

	public void setByValue(Map<String, String> map) {
		map = new HashMap<String, String>();
		map.put("aaa", "testbbb");
	}

	public static void main(String[] args) {
		TestReference ref = new TestReference();
		Map<String, String> map = new HashMap<String, String>();
		ref.setByParam(map);
		System.out.println(map.get("aaa"));
		map = new HashMap<String, String>();
		ref.setByValue(map);
		System.out.println(map.get("aaa"));
	}
}
