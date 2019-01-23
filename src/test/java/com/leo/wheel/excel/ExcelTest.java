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

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;

public class ExcelTest {

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

		head.add(headCoulumn1);
		head.add(headCoulumn2);
		head.add(headCoulumn3);
		head.add(headCoulumn4);
		head.add(headCoulumn5);
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
			da.add(Long.valueOf(187837834l + i));
			da.add(Integer.valueOf(2233 + i));
			da.add(Double.valueOf(2233.00 + i));
			da.add(Float.valueOf(2233.0f + i));
			da.add(new Date());
			da.add(new BigDecimal("3434343433554545" + i));
			da.add(Short.valueOf((short) i));
			object.add(da);
		}
		return object;
	}

	@Test
	public void writeV2007() throws IOException {
		OutputStream out = new FileOutputStream("v2007.xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		Sheet sheet1 = new Sheet(1, 3);
		sheet1.setSheetName("第一个sheet");

		// 设置列宽 设置每列的宽度
		Map<Integer, Integer> columnWidth = new HashMap<Integer, Integer>();
		columnWidth.put(0, 10000);
		columnWidth.put(1, 20000);
		columnWidth.put(2, 10000);
		columnWidth.put(3, 10000);
		sheet1.setColumnWidthMap(columnWidth);
		sheet1.setHead(createTestListStringHead());
		// or 设置自适应宽度
		// sheet1.setAutoWidth(Boolean.TRUE);
		writer.write1(createTestListObject(), sheet1);
		writer.finish();
		out.close();

	}
}