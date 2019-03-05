package com.leo.wheel.cache;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis测试类，参考：https://www.cnblogs.com/byxxw/p/5822589.html
 * 
 * @author leo
 *
 */
public class RedisTest {

	private Jedis getJedis() {
		Jedis jedis = new Jedis("47.244.165.23", 6379);
		return jedis;
	}

	@Test
	public void test_string_set() {
		Jedis jedis = getJedis();
		jedis.set("age", "1000");
		System.out.println(jedis.get("age"));
	}

	@Test
	public void test_strign_setex() {
		Jedis j = getJedis();
		j.setex("customer", 2, "张铭");
		System.out.println("customer:" + j.get("customer"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("customer sleep 3 second =" + j.get("customer"));
	}
}
