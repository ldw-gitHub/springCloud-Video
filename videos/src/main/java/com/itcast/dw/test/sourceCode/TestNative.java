package com.itcast.dw.test.sourceCode;

public class TestNative {
	
	/**
	 * 
	 * native java调用本地方法
	 * 
	 */
	
	public native static void Hello();
	
	static{
		//System.load("D:" + File.separator + "test" + File.separator + "Hello.dll");
		Hello();
	}
	
	
	public static void main(String[] args) {
		System.out.println("开始");
	}

}
