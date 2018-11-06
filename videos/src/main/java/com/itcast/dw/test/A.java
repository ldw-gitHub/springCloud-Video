package com.itcast.dw.test;

public class A {
	
	static{
		c = 5;
		b = 5;
		System.out.println(A.c);
		System.out.println(A.b);
	}

	private static int b = 1;
	private static int c;
	
	public static void main(String[] args) { 
		System.out.println(c);
		System.out.println(b);
	}
	
}
