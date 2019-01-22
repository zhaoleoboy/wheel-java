package com.leo.wheel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {
	
	@RequestMapping(value = "/hello")
	@ResponseBody
	public String hello() {
		return "hello111 spring boot";
	}

}
