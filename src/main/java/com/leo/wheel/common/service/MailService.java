package com.leo.wheel.common.service;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 	邮件发送服务
 * @author leo
 *
 */
@Service
public class MailService {

	private Logger logger = LoggerFactory.getLogger(MailService.class);

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender mailSender;

	// 加载模板引擎
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * 	发送简单文本邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void sendSimpleTextMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(from);
		mailSender.send(message);
		logger.info("【文本邮件】成功发送！to={}", to);
	}

	/**
	 *	发送带有附件的邮件，且HTML中可以嵌入图片
	 * @param to
	 * @param subject
	 * @param text
	 * @param fileArr
	 * @throws MessagingException
	 */
	public void sendMessageWithAttachment(String to, String subject, String text, Map<String, String> imgMap,
			String... fileArr) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		// true表示为HTML格式的邮件
		helper.setText(text, true);
		helper.setFrom(from);
		// HTML嵌入添加图片
		if (MapUtils.isNotEmpty(imgMap)) {
			for (Map.Entry<String, String> entry : imgMap.entrySet()) {
				FileSystemResource fileResource = new FileSystemResource(new File(entry.getValue()));
				if (fileResource.exists()) {
					helper.addInline(entry.getKey(), fileResource);
				}
			}
		}
		// 添加附件
		for (String filePath : fileArr) {
			FileSystemResource fileResource = new FileSystemResource(new File(filePath));
			if (fileResource.exists()) {
				String filename = fileResource.getFilename();
				helper.addAttachment(filename, fileResource);
			}
		}
		mailSender.send(message);
		logger.info("【附件邮件】成功发送！to={}", to);
	}

	/**
	 * 	发送模板邮件
	 * @param to
	 * @param subject
	 * @param paramMap
	 * @param template
	 * @throws MessagingException
	 */
	public void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template)
			throws MessagingException {
		Context context = new Context();
		// 设置变量的值
		context.setVariables(paramMap);
		String emailContent = templateEngine.process(template, context);
		sendMessageWithAttachment(to, subject, emailContent, null);
		logger.info("【模版邮件】成功发送！paramsMap={}，template={}", paramMap, template);
	}
}
