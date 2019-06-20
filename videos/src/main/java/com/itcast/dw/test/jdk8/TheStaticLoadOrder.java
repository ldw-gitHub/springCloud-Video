package com.itcast.dw.test.jdk8;

public class TheStaticLoadOrder {
	
	static{
		c = 5;
		b = 5;
		System.out.println(TheStaticLoadOrder.c);
		System.out.println(TheStaticLoadOrder.b);
	}

	private static int b = 1;
	private static int c;
	
	public static void main(String[] args) { 
		System.out.println(c);
		System.out.println(b);
	}
	
}
