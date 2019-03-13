package com.leo.wheel.common;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import com.leo.wheel.common.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
	@Autowired
	private MailService mailService;

	@Value("${file.upload}")
	private String uploader;

	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void testSendSimpleTextMail() {
		String to = "zhaoleoboy@163.com";
		String subject = "Springboot 发送简单文本邮件";
		String content = "<p>第一封 Springboot 简单文本邮件</p>";
		mailService.sendSimpleTextMail(to, subject, content);
	}

	@Test
	public void testSendMessageWithAttachment() throws MessagingException, FileNotFoundException {
		String to = "zhaoleoboy@163.com";
		String subject = "Springboot 发送附件邮件";
		String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 图片邮件</p><br/><img src=\"cid:img01\" /><img src=\"cid:img02\" />";
		Map<String, String> imgMap = new HashMap<>();
		String imgPath = uploader + "test.jpg";
		imgMap.put("img01", imgPath);
		imgMap.put("img02", imgPath);
		mailService.sendMessageWithAttachment(to, subject, content, imgMap, uploader + "test.jpg",
				uploader + "test.txt");
	}

	@Test
	public void testSendTemplateMail() throws MessagingException {
		String to = "zhaoleoboy@163.com";
		String subject = "Springboot 发送 模版邮件";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", "leo");
		mailService.sendTemplateMail(to, subject, paramMap, "mail");
	}
}
