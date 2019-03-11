package com.leo.tool;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.File;
import java.util.ArrayList;

/**
 * <p>Mybatis generator的逆向生成工具类</p>
 *
 * @Author leo
 */
public class MybatisGenerator {

	public void generator() throws Exception {
		ArrayList<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		// 指定工程配置文件
		File configFile = new File("./mybatis-generator.xml");
		System.out.println(configFile.getAbsolutePath());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

	public static void main(String[] args) throws Exception {
		MybatisGenerator mybatisGenerator = new MybatisGenerator();
		mybatisGenerator.generator();
	}
}
