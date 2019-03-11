package com.leo.wheel.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SpringDataTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * 	测试分页
	 * @throws Exception
	 */
	@Test
	public void testGetPageDataByCondition() throws Exception {
		String responce = mockMvc
				.perform(get("/wheel/getDataByCondition").contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的格式
						.param("pageNum", "1") // 相当于直接写在url上的参数
						.param("pageSize", "5") // 相当于直接写在url上的参数
				).andExpect(status().isOk()) // 比较结果
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString();

		System.out.println(responce);
	}

	/**
	 * 	测试getDataById
	 * @throws Exception
	 */
	@Test
	public void testGetDataById() throws Exception {
		String responce = mockMvc.perform(get("/wheel/getDataById").contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的格式
				.param("id", "123SDFAE") // 相当于直接写在url上的参数
		).andExpect(status().isOk()) // 比较结果
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString();

		System.out.println(responce);
	}

	/**
	 * TODO	测试Insert
	 * 	测试Insert
	 */
	public void testInsert() {

	}

	/**
	 * TODO 测试Update
	 * 	测试Update
	 */
	public void testUpdate() {

	}

	/**
	 * TODO 测试Delete
	 * 	测试Delete
	 */
	public void testDelete() {

	}

	/**
	 * TODO 测试Batch操作
	 * 	测试Batch操作
	 */
	public void testBatch() {

	}
}
