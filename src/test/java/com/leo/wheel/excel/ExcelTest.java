package com.leo.wheel.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.TableStyle;
import com.leo.wheel.Main;
import com.leo.wheel.entity.excel.WriteModel;
import com.leo.wheel.utils.DateUtils;
import com.leo.wheel.utils.ExcelUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class ExcelTest {

	@Value("${file.upload}")
	private String uploader;

	public static TableStyle createTableStyle() {
		TableStyle tableStyle = new TableStyle();
		Font headFont = new Font();
		headFont.setBold(true);
		headFont.setFontHeightInPoints((short) 22);
		headFont.setFontName("楷体");
		tableStyle.setTableHeadFont(headFont);
		tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

		Font contentFont = new Font();
		contentFont.setBold(true);
		contentFont.setFontHeightInPoints((short) 22);
		contentFont.setFontName("黑体");
		tableStyle.setTableContentFont(contentFont);
		tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
		return tableStyle;
	}

	public static List<List<String>> createTestListStringHead() {
		// 写sheet3 模型上没有注解，表头数据动态传入
		List<List<String>> head = new ArrayList<List<String>>();
		List<String> headCoulumn1 = new ArrayList<String>();
		List<String> headCoulumn2 = new ArrayList<String>();
		List<String> headCoulumn3 = new ArrayList<String>();
		List<String> headCoulumn4 = new ArrayList<String>();
		List<String> headCoulumn5 = new ArrayList<String>();
		List<String> headCoulumn6 = new ArrayList<String>();

		// 合并的标题行list大小需要相等
		headCoulumn1.add("第一列");
		headCoulumn1.add("第一列");
		headCoulumn1.add("第一列");
		headCoulumn2.add("第一列");
		headCoulumn2.add("第一列1");
		headCoulumn2.add("第一列2");
		//
		headCoulumn3.add("第二列");
		headCoulumn4.add("第三列");
		headCoulumn5.add("第4列");
		headCoulumn6.add("第4列");

		head.add(headCoulumn1);
		head.add(headCoulumn2);
		head.add(headCoulumn3);
		head.add(headCoulumn4);
		head.add(headCoulumn5);
		head.add(headCoulumn6);
		return head;
	}

	public static List<WriteModel> createTestListJavaMode() {
		List<WriteModel> model1s = new ArrayList<WriteModel>();
		for (int i = 0; i < 10000; i++) {
			WriteModel model1 = new WriteModel();
			model1.setP1("第一列，第行");
			model1.setP2("121212jjj");
			model1.setP3(33 + i);
			model1.setP4(44);
			model1.setP5("555");
			model1.setP6(666.2f);
			model1.setP7(new BigDecimal("454545656343434" + i));
			model1.setP8(new Date());
			model1.setP9("llll9999>&&&&&6666^^^^");
			model1.setP10(1111.77 + i);
			model1s.add(model1);
		}
		return model1s;
	}

	public static List<List<Object>> createTestListObject() {
		List<List<Object>> object = new ArrayList<List<Object>>();
		for (int i = 0; i < 1000; i++) {
			List<Object> da = new ArrayList<Object>();
			da.add("字符串" + i);
			da.add("字符串" + i);
			da.add(Integer.valueOf(2233 + i));
			da.add(Double.valueOf(5555.00 + i));
			da.add(String.valueOf(Float.valueOf(2233.0f + i)));
			da.add(DateUtils.getCurDateTime(DateUtils.DATE_FORMAT_SHORT));
			da.add(new BigDecimal("3434343433554545" + i).toString());
			da.add(Short.valueOf((short) i));
			object.add(da);
		}
		return object;
	}

	@Test
	public void writeV2007() throws IOException {
		long time = new Date().getTime();
		OutputStream out = new FileOutputStream(uploader + "export" + time + ".xlsx");
		// 设置列宽 设置每列的宽度
		Map<Integer, Integer> columnWidth = new HashMap<Integer, Integer>();
		columnWidth.put(0, 10000);
		columnWidth.put(1, 20000);
		columnWidth.put(2, 10000);
		columnWidth.put(3, 10000);
		// TODO 这里只有表头列的合并，看看内容能不能合并？
		ExcelUtils.exportV2007(out, createTestListObject(), createTestListStringHead(), false, columnWidth);
		out.close();
	}
}