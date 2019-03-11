package com.leo.wheel.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.leo.wheel.Main;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class SpringPreviewTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Value("${file.viewer}")
	private String folder;// 读取applicaton.properties配置文件，获取文件上传的目录

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * 	测试分页
	 * @throws Exception
	 */
	@Test
	public void testPreviewTxt() throws Exception {
		byte[] contentAsByteArray = mockMvc
				.perform(get("/viewer/preview").contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的格式
						.param("sourceFilePath", "test.txt") // 相当于直接写在url上的参数
				).andExpect(status().isOk()) // 比较结果
				.andReturn().getResponse().getContentAsByteArray();
		File file = new File(folder + "myFile.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(contentAsByteArray);
		fos.flush();
		System.out.println(contentAsByteArray);
	}

}
