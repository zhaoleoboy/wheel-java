package com.leo.wheel.baseTypes;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.google.common.io.Files;

/**
 * 
 * @author leo
 *
 */
public class FileTest {

	/**
	 * 	测试Guava工具的文件复制
	 * @throws IOException
	 */
	@Test
	public void testGuavaCopy() throws IOException {
		File original = new File("path/to/original");
		File copy = new File("path/to/copy");
		Files.copy(original, copy);
	}

	/**
	 * 	测试Guava工具的文件重命名或文件移动
	 */
	@Test
	public void testGuavaRename() {
		File original = new File("src/main/resources/copy.txt");
		File newFile = new File("src/main/resources/newFile.txt");
		try {
			Files.move(original, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
