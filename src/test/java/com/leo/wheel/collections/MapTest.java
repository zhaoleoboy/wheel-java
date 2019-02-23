package com.leo.wheel.collections;

import java.util.HashMap;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * map常用方法：
 * 单线程应尽量使用 HashMap, ArrayList,除非必要，否则不推荐使用HashTable,Vector，它们使用了同步机制，而降低了性能；
 * 
 * 
 * @author leo
 *
 */
public class MapTest {
	@Test
	public void testIteratorByEntry() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("key1", 1);
		map.put("key2", 2);
		map.put("key3", 3);
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
