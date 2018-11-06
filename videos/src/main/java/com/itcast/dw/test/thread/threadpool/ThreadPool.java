package com.itcast.dw.test.thread.threadpool;

public interface ThreadPool<Job extends Runnable> {
	//执行一个job，这个job需要实现一个runnable
	void execute(Job job);
	//关闭线程
	void shutdown();
	//增加工作者线程
	void addWorkers(int num);
	//减少工作者线程
	void removeWorker(int num);
	//得到正在等待执行线程的数量
    int getJobSize();	
}
