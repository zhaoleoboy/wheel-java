package com.leo.wheel.common;

import org.jodconverter.office.OfficeException;
import org.junit.Test;

import com.leo.wheel.common.service.DocService;

public class PreviewTest {

	/**
	 * 	测试Excel文件的在线预览
	 * @throws OfficeException 
	 */
	@Test
	public void testPreviewExcel() throws OfficeException {
		DocService service = new DocService();
		String dest = service.preview("D:\\codes\\wheel-java\\test.xlsx");
		System.out.println(dest);
	}
}
