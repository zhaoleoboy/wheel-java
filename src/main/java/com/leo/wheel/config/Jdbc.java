package com.leo.wheel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jdbc.properties") // 如果是application.properties，就不用写，其他名字用写
public class Jdbc {

	@Value("${jdbc.user}")
	private String user;

	@Value("${jdbc.password}")
	private String password;

	public void speack() {
		System.out.println("username:" + user + "------" + "password:" + password);
	}
}
