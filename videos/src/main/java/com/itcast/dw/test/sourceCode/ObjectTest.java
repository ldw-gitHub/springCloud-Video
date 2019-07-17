package com.itcast.dw.test.sourceCode;

import java.lang.reflect.Method;

public class ObjectTest {
	
	/**
	 * 
	 * object 类 equals 等
	 * 
	 */
	
	public static void test1(){
		System.out.println("test1");
	}
	
	public void test2(Integer a){
		System.out.println("test2" + a);
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = ObjectTest.class.getMethod("test2", Integer.class);
		System.out.println(method.getParameterCount());
		
		ObjectTest o1 = new ObjectTest();
		ObjectTest o2 = new ObjectTest();
		System.out.println(o1.equals(o2));
		
		System.out.println(o1.toString());
		
		System.exit(0);
		System.out.println(new Integer("1"));
		
		
	}

}
