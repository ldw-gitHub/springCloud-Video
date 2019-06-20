package com.itcast.dw.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendMsgPool {
	
	private static int thread_number = 300;
	
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(thread_number);
		long currentTimeMillis = System.currentTimeMillis();
		for(int i = 0;i < 10000 ;i++){
			newFixedThreadPool.submit(new job(i));
		}
		
		newFixedThreadPool.shutdown();
		
		while(true){
			if(newFixedThreadPool.isTerminated()){
				System.out.println("执行完成 ：" + (System.currentTimeMillis() - currentTimeMillis));
				newFixedThreadPool.shutdown();
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}


class job implements Runnable {
	int phoneNumber;
	public job(){};
	public job(int phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	@Override
	public void run() {
		long currentTimeMillis = System.currentTimeMillis();
		String name = Thread.currentThread().getName();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " do send msg = " + phoneNumber + " ; wast time = " + (System.currentTimeMillis() - currentTimeMillis));
	}
}