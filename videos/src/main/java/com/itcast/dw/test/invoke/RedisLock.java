package com.itcast.dw.test.invoke;

import com.itcast.dw.config.JedisUtils;
import com.itcast.dw.constants.RedisKey;
public class RedisLock {

	public static long MILLI_NANO_TIME = 1000;
	private JedisUtils jedisUtils;
	
	private String key;
	private String LOCKED;
	private boolean lock;
	
	public RedisLock(String key,String LOCKED,JedisUtils jedisUtils){
		this.key = key;
		this.LOCKED = LOCKED;
		this.jedisUtils = jedisUtils;
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
		long nanoTime = System.currentTimeMillis();
		timeout *= MILLI_NANO_TIME;
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.currentTimeMillis() - nanoTime < timeout) {
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
				//jedisUtils.set(Thread.currentThread().getName(), System.currentTimeMillis()+"", RedisKey.indexDB);
				if (this.jedisUtils.setnx(LOCKED, this.key,RedisKey.indexDB) == 1) {
					this.jedisUtils.expire(LOCKED, expire,RedisKey.indexDB);// 设置锁过期时间是为了在没有释放
					// 锁的情况下锁过期后消失，不会造成永久阻塞
					this.lock = true;
					return this.lock;
				}
				//System.out.println("出现锁等待");
				// 短暂休眠，避免可能的活锁
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			throw new RuntimeException("locking error", e);
		}
		return false;
	}

	public void unlock() {
		try {
			//System.out.println("删除key ===== " + this.LOCKED);
			jedisUtils.del(this.LOCKED);// 直接删除
		} catch (Throwable e) {

		}
	}

}
