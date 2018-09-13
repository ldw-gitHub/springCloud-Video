package com.itcast.dw.test.thread;

import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueue {
	
	public static LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<Integer>(2);
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0 ; i < 10; i++){
			Thread td = new Thread(new doRuning(),i + "");
			linkedBlockingDeque.put(i);
			td.start();
		}
		
	}
	
	

}


class doRuning implements Runnable{

	@Override
	public void run() {
		try {
			//Thread.sleep(10000);
			Integer take = BlockingQueue.linkedBlockingDeque.take();
			System.out.println(take);
			//System.out.println(Thread.currentThread().getName() + "执行完毕！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}


class consumer implements Runnable{
	@Override
	public void run() {
		try {
			Thread.sleep(10000);
			
			System.out.println(Thread.currentThread().getName() + "执行完毕！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}