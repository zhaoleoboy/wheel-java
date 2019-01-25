package com.leo.wheel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leo.wheel.biz.mapper.WheelMapper;
import com.leo.wheel.entity.biz.WheelInfo;

@SpringBootApplication
public class MainTest implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MainTest.class, args);
	}

	private final WheelMapper wheelMapper;

	public MainTest(WheelMapper wheelMapper) {
		this.wheelMapper = wheelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		WheelInfo bean = this.wheelMapper.getDataByCondition("feadr4t3q");
		System.out.println(bean.getName());
	}

}
