package com.itcast.dw.test.commitRespect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.constants.RedisKey;

@Component
public class RedisLock {

	@Autowired
	private JedisUtils jedisUtils;

	/**
	 * 加锁 使用方式为： lock(); try{ executeMethod(); }finally{ unlock(); }
	 * 
	 * @param timeout
	 *            timeout的时间范围内轮询锁
	 * @param expire
	 *            设置锁超时时间
	 * @return 成功 or 失败
	 */
	public boolean lock(String key, String value, int expire) {
		try {
			// 在timeout的时间范围内不断轮询锁
			// 锁不存在的话，设置锁并设置锁过期时间，即加锁
			// jedisUtils.set(Thread.currentThread().getName(),
			// System.currentTimeMillis()+"", RedisKey.indexDB);
			if (this.jedisUtils.setnx(key, value, RedisKey.indexDB) == 1) {
				this.jedisUtils.expire(key, expire, RedisKey.indexDB);// 设置锁过期时间是为了在没有释放
				// 锁的情况下锁过期后消失，不会造成永久阻塞
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("locking error", e);
		}
		return false;
	}

	public void unlock(String key) {
		try {
			// System.out.println("删除key ===== " + this.LOCKED);
			jedisUtils.del(key);// 直接删除
		} catch (Throwable e) {

		}
	}

}
