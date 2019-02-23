package com.leo.wheel.baseTypes;

import static org.junit.Assert.assertArrayEquals;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 测试String常用的方法！
 * StringUtils.split；
 * StringBuffer;
 * StringBuilder;
 * 
 * 
 * @author lenovo
 *
 */
public class StringTest {

	/**
	 * apache的StringUtils的split方法比String.split方法的效率高得多，
	 * StringUtils.split(string,char)在频繁split时候可以缓存结果。
	 */
	@Test
	public void testStringUtilsSplit() {
		long start = System.currentTimeMillis();
		String[] commonSplits = null;
		String[] stringUtilsSplits = null;
		for (int i = 0; i < 100000; i++) {
			commonSplits = "a-b-c-d-e".split("-");
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("common split花费时间为%sms", end - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			stringUtilsSplits = StringUtils.split("a-b-c-d-e", "-");
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("StringUtils split花费时间为%sms", end - start));
		assertArrayEquals(commonSplits, stringUtilsSplits);
	}

	/**
	 * StringBuilder与该类相比，通常应该优先使用StringBuilder类，因为它支持所有相同的操作，但由于它不执行同步，所以速度更快；
	 * 相同情况下，使用StringBuilder比使用StringBuffer仅能获得10%~15%的性能提升，但却要冒多线程不安全的风险。综合考虑还是建议使用StringBuffer。
	 */
	@Test
	public void testStringBuilder() {

	}

	/**
	 * 测试StringBuffer:
	 * 1，默认容量为16，当StringBuffer的容量达到最大容量时，它会将自身容量增加到当前的2倍+2，也就是2*n+2。
	 * 无论何时，只要StringBuffer到达它的最大容量，它就不得不创建一个新的对象数组，然后复制旧的对象数组，这会浪费很多时间。所以给StringBuffer设置一个合理的初始化容量值，是很有必要的！
	 * 2， 线程安全的可变字符序列。一个类似于String的字符串缓冲区，但不能修改。
	 */
	@Test
	public void testStringBuffer() {

	}
}
