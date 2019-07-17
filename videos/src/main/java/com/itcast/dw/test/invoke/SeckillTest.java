/*package com.itcast.dw.test.invoke;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itcast.dw.config.JedisUtils;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillTest {
	
	@Autowired
	JedisUtils jedisUtils;
	
	public static void main(String[] args) {
	}
	
	@Test
	public void testSecKill() {
		int thread_number = 3;
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(thread_number);
		long startTime = System.currentTimeMillis();
		SecKillImpl testClass = new SecKillImpl();
		for(int i = 0;i < 60 ;i++){
			newFixedThreadPool.submit(() ->{
				try {
					// 用动态代理的方式调用secKill方法
					SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(
							SeckillInterface.class.getClassLoader(), new Class[] { SeckillInterface.class },
							new CacheLockInterceptor(testClass,jedisUtils));
					if(SecKillImpl.inventory.get(10000001L) < 0){
						System.out.println("商品已售空==");
					}else{
						proxy.secKill("test", 10000001L);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		newFixedThreadPool.shutdown();
		
		while(true){
			if(newFixedThreadPool.isTerminated()){
				newFixedThreadPool.shutdown();
				// 观察秒杀结果是否正确
				System.out.println(SecKillImpl.inventory.get(10000001L));
				System.out.println("error count" + CacheLockInterceptor.ERROR_COUNT);
				System.out.println("right count" + CacheLockInterceptor.RIGHT_COUNT);
				System.out.println("total count" + CacheLockInterceptor.TOTAL_COUNT);
				System.out.println("time :" + (System.currentTimeMillis() - startTime));
				
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		int threadCount = 500;
		int splitPoint = 500;
		CountDownLatch endCount = new CountDownLatch(threadCount);
		CountDownLatch beginCount = new CountDownLatch(1);
		SecKillImpl testClass = new SecKillImpl();

		Thread[] threads = new Thread[threadCount];
		// 起500个线程，秒杀第一个商品
		for (int i = 0; i < splitPoint; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					try {
						// 等待在一个信号量上，挂起
						beginCount.await();
						// 用动态代理的方式调用secKill方法
						SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(
								SeckillInterface.class.getClassLoader(), new Class[] { SeckillInterface.class },
								new CacheLockInterceptor(testClass));
						proxy.secKill("test", 500l);
						endCount.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			threads[i].start();

		}
		// 再起500个线程，秒杀第二件商品
		for (int i = splitPoint; i < threadCount; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					try {
						// 等待在一个信号量上，挂起
						beginCount.await();
						// 用动态代理的方式调用secKill方法
						SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(
								SeckillInterface.class.getClassLoader(), new Class[] { SeckillInterface.class },
								new CacheLockInterceptor(testClass));
						proxy.secKill("test", 500l);
						// testClass.testFunc("test", 10000001L);
						endCount.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			threads[i].start();

		}

		long startTime = System.currentTimeMillis();
		// 主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
		beginCount.countDown();

		try {
			// 主线程等待结束信号量
			endCount.await();
			// 观察秒杀结果是否正确
			System.out.println(SecKillImpl.inventory.get(500l));
			System.out.println(SecKillImpl.inventory.get(500l));
			System.out.println("error count" + CacheLockInterceptor.ERROR_COUNT);
			System.out.println("total cost " + (System.currentTimeMillis() - startTime));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/