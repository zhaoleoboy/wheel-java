package com.leo.wheel.collections;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 * 集合常用的方法：
 * ArrayList是线性表，LinkedList是链表，一句话，随机查询尽量使用ArrayList，ArrayList优于LinkedList，LinkedList还要移动指针，添加删除的操作LinkedList优于ArrayList，ArrayList还要移动数据；
 * 
 * @author lenovo
 *
 */
public class ArrayTest {

	/**
	 * System.arraycopy() 要比通过循环来复制数组快的多。
	 * 数组
	 */
	@Test
	public void testCopyArray() {
		int[] source = new int[100000000];
		int[] dest = new int[100000000];
		for (int i = 0; i < source.length; i++) {
			source[i] = i;
		}
		// 测试System.arraycopy的方法
		long start = System.currentTimeMillis();
		System.arraycopy(source, 0, dest, 0, source.length);
		long end = System.currentTimeMillis();
		System.out.println(String.format("System.arraycopy花费时间为%sms", end - start));

		// 测试循环复制的方法
		dest = new int[100000000];
		start = System.currentTimeMillis();
		for (int i = 0; i < dest.length; i++) {
			dest[i] = source[i];
		}
		end = System.currentTimeMillis();
		System.out.println(String.format("循环复制花费时间为%sms", end - start));
		assertArrayEquals(source, dest);
	}

}
