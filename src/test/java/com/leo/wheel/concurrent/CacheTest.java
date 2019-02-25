package com.leo.wheel.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * 	为什么要用本地缓存：
 * 	相对于IO操作：速度快，效率高；
 * 	相对于Redis：Redis是一种优秀的分布式缓存实现，受限于网卡等原因，远水救不了近火。
 * 
 * 	怎么用：
 * 	1，设置缓存容量；
 * 	2，设置超时时间；
 * 	3，提供移除监听器
 * 	4，提供缓存加载器；
 * 	5，构建缓存。
 * 
 * 
 * @author leo
 *
 */
public class CacheTest {

	/**
	 * 	参考：https://www.imooc.com/article/34924
	 * Guava Cache提供了三种基本的缓存回收方式：基于容量回收、定时回收和基于引用回收；
	 * GuavaCache的实现代码中没有启动任何线程，Cache中的所有维护操作，包括清除缓存、写入缓存等，都需要外部调用来实现。
	 * 	这在需要低延迟服务场景中使用时，需要关注，可能会在某个调用的响应时间突然变大。GuavaCache毕竟是一款面向本地缓存的，轻量级的Cache，适合缓存少量数据。
	 * 	如果你想缓存上千万数据，可以为每个key设置不同的存活时间，并且高性能，那并不适合使用GuavaCache。
	 * 
	 */
	@Test
	public void testGuavaCache() {
		// 
		CacheLoader<String, String> loader = new CacheLoader<String, String>() {
			public String load(String key) throws Exception {
				Thread.sleep(1000);
				if ("key".equals(key))
					return null;
				System.out.println(key + " is loaded from a cacheLoader!");
				return key + "'s value";
			}
		};

		RemovalListener<String, String> removalListener = new RemovalListener<String, String>() {
			public void onRemoval(RemovalNotification<String, String> removal) {
				System.out.println("[" + removal.getKey() + ":" + removal.getValue() + "] is evicted!");
			}
		};

		LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(7) // cache保存的最大记录数
				.expireAfterWrite(10, TimeUnit.MINUTES)// 10分钟后过期
				.removalListener(removalListener)// 超过缓存最大记录数时执行的回调操作
				.build(loader);

		for (int i = 0; i < 10; i++) {
			String key = "key" + i;
			String value = "value" + i;
			cache.put(key, value);
			System.out.println("[" + key + ":" + value + "] is put into cache!");
		}
		// 不会报异常的方法
		System.out.println(cache.getIfPresent("key1"));

		try {
			System.out.println(cache.get("key"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
