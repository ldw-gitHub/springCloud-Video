package com.itcast.dw.test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerCancel {
	
	public static void main(String[] args) {
		/*Timer timer = new Timer();
		Task task = new Task(timer,1);
		timer.schedule(task,3000);*/
		for(int i = 0;i < 1000000;i++){
			System.out.println(i/100000);
		}
	}

}


class Task extends TimerTask{
	
	private Timer timer;
	private int smsTaskId;
	
	public Task(){};
	
	public Task(Timer timer,int smsTaskId){
		this.timer = timer;
		this.smsTaskId = smsTaskId;
	}

	@Override
	public void run() {
		//判断是否取消
		System.out.println("do this : " + smsTaskId);
		timer.cancel();
	}
	
}