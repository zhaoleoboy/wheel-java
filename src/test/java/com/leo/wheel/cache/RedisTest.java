package com.leo.wheel.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * redis测试类，参考：https://www.cnblogs.com/byxxw/p/5822589.html
 * 
 * @author leo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void redisTest() {
		// redis存储数据
		String key = "name";
		redisTemplate.opsForValue().set(key, "yukong");
		// 获取数据
		String value = (String) redisTemplate.opsForValue().get(key);
		System.out.println("获取缓存中key为" + key + "的值为：" + value);

	}

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
