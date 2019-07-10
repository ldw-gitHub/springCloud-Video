package com.itcast.dw.test.invoke;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.dw.config.JedisUtils;

public class RedisLock {
	
	@Autowired
	JedisUtils jedisUtils;

	public static long MILLI_NANO_TIME = 3000;
	private String LOCKED;
	
	private String key;
	
	private boolean lock;
	
	public RedisLock(String key,String LOCKED){
		this.key = key;
		this.LOCKED = LOCKED;
	}
	

	/**
	 * 加锁 使用方式为： lock(); try{ executeMethod(); }finally{ unlock(); }
	 * 
	 * @param timeout
	 *            timeout的时间范围内轮询锁
	 * @param expire
	 *            设置锁超时时间
	 * @return 成功 or 失败
	 */
	public boolean lock(long timeout, int expire) {
		long nanoTime = System.nanoTime();
		timeout *= MILLI_NANO_TIME;
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < timeout) {
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
				if (this.jedisUtils.setnx(this.key, LOCKED) == 1) {
					this.jedisUtils.expire(key, expire,expire);// 设置锁过期时间是为了在没有释放
					// 锁的情况下锁过期后消失，不会造成永久阻塞
					this.lock = true;
					return this.lock;
				}
				System.out.println("出现锁等待");
				// 短暂休眠，避免可能的活锁
				Thread.sleep(3, RandomUtils.nextInt(0, 30));
			}
		} catch (Exception e) {
			throw new RuntimeException("locking error", e);
		}
		return false;
	}

	public void unlock() {
		try {
			if (this.lock) {
				jedisUtils.del(key);// 直接删除
			}
		} catch (Throwable e) {

		}
	}

}
