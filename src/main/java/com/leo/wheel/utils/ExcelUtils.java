package com.leo.wheel.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

/**
 * Excel工具类
 * 
 * @author leo
 *
 */
public class ExcelUtils {

	/**
	 * 导出Excel2007
	 * 
	 * @param out
	 * @param datas       数据集
	 * @param headers     表头
	 * @param isAutoWidth 自动调整列宽
	 * @param columnWidth 如果不自动调整宽度，需要手动设置
	 * @throws IOException
	 */
	public static void exportV2007(OutputStream out, List<List<Object>> datas, List<List<String>> headers,
			boolean isAutoWidth, Map<Integer, Integer> columnWidth) throws IOException {
		long start = System.currentTimeMillis();
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		Sheet sheet = new Sheet(1, 3);
		sheet.setSheetName("第一个sheet");

		// 设置Excel表头
		sheet.setHead(headers);
		// 设置列宽
		if (!isAutoWidth) {
			sheet.setColumnWidthMap(columnWidth);
		} else {
			sheet.setAutoWidth(isAutoWidth);
		}

		writer.write1(datas, sheet);
		writer.finish();
		long end = System.currentTimeMillis();
		System.out.println(String.format("导出Excel花费时间：%sms", end - start));
	}

	/**
	 * 模拟Excel导出的表头
	 * 
	 * @return
	 */
	public static List<List<String>> mockHeaders() {
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

	/**
	 * 模拟Excel导出的数据
	 * 
	 * @return
	 */
	public static List<List<Object>> mockDatas() {
		List<List<Object>> object = new ArrayList<List<Object>>();
		for (int i = 0; i < 1000; i++) {
			List<Object> da = new ArrayList<Object>();
			da.add("字符串" + i);
			da.add(Long.valueOf(111 + i));
			da.add(Integer.valueOf(2233 + i));
			da.add(Double.valueOf(2233.00 + i));
			da.add(Float.valueOf(2233.0f + i));
			da.add(new Date());
			da.add(new BigDecimal("3434343433554545" + i).toString());
			da.add(Short.valueOf((short) i));
			object.add(da);
		}
		return object;
	}

}
