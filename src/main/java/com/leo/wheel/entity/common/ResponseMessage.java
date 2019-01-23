package com.leo.wheel.entity.common;

import java.util.Properties;

public class ResponseMessage {

	private Properties properties = new Properties();

	private static class SingletonHolder {
		public static final ResponseMessage INSTANCE = new ResponseMessage();
	}

	public static ResponseMessage getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public String getValue(String key) {
		return this.properties.containsKey(key)?this.properties.getProperty(key):"";
	}
}
