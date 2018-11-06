package com.itcast.dw.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * 可以复用到方法调用耗时统计的功能上
 * 在aop面向编程的方法中也可用
 * @author ldw
 *
 */
public class ThreadLocals {
	
	/**
	 * 如果第一次调用get()没有赋值，会进行初始化
	 */
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
		protected Long initialValue(){
			return System.currentTimeMillis();
		}
	};
	
	public static final void begin(){
		//TIME_THREADLOCAL.set(System.currentTimeMillis());
		TIME_THREADLOCAL.set((long) 10000);
	}
	
	public static final long end(){
		System.out.println(TIME_THREADLOCAL.get());
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}
	
	public static void main(String[] args) {
		ThreadLocals.begin();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("程序消耗：" + ThreadLocals.end() + "mills" );
	}

}
