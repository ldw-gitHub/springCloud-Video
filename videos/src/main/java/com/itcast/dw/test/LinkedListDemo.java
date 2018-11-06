package com.itcast.dw.test;

import java.util.LinkedList;

public class LinkedListDemo {
	
	private static LinkedList<Integer> ld = new LinkedList<Integer>();
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			ld.add(i);//Appends the specified element to the end of this list. This method is equivalent to

			//ld.addLast(i);//Appends the specified element to the end of this list. This method is equivalent to
			
			System.out.println(ld.removeFirst());
		}
		System.out.println(ld.removeFirst());
		
		/*System.out.println(ld.getFirst());
		System.out.println(ld.getLast());*/
		
	}

}
