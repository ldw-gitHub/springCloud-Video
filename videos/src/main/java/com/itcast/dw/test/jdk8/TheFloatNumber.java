package com.itcast.dw.test.jdk8;

public class TheFloatNumber {
	public static void main(String[] args) {
		float a = 1.0f - 0.9f;
		float b = 0.9f - 0.8f;
		System.out.println(a);
		System.out.println(b);
		if(a == b){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		Float c = Float.valueOf(1.0f-0.9f);
		Float d = Float.valueOf(0.9f-0.8f);
		System.out.println(c);
		System.out.println(d);
		if(c.equals(d)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		String param = null; //异常，switch先hashCode再equals比较
		switch(param){
		    case "null":
			    System.out.println("null");
			    break;
			default:
				System.out.println("default");
		}
	}

}
