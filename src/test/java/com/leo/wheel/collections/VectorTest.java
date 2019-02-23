package com.leo.wheel.collections;

import org.junit.Test;

/**
 * Vector常用方法：
 * 1，Vector与StringBuffer类似，每次扩展容量时，所有现有元素都要赋值到新的存储空间中。Vector的默认存储能力为10个元素，扩容加倍；
 * 2，
 * @author leo
 *
 */
public class VectorTest {

	/**
	 * vector.add(index,obj) 这个方法可以将元素obj插入到index位置，但index以及之后的元素依次都要向下移动一个位置（将其索引加 1）。 除非必要，否则对性能不利。
	 */
	@Test
	public void testAdd() {

	}
	
	/**
	 * 移除此向量中指定位置的元素。将所有后续元素左移（将其索引减 1）。返回此向量中移除的元素。所以删除vector最后一个元素要比删除第1个元素开销低很多。
	 * 删除所有元素最好用removeAllElements()方法；
	 * 如果要删除vector里的一个元素可以使用 vector.remove(obj)；而不必自己检索元素位置；
	 */
	@Test
	public void testRemove() {
		
	}
}
