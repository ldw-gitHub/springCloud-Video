package com.itcast.dw.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.itcast.dw.test.model.People;

public class JDK8Test {
	
	public static void main(String[] args) {
		JDK8Test jt = new JDK8Test();
		List<String> a = new ArrayList<String>();
		
		a.add("111");
		a.add("3");
		a.add("222");
		
		jt.sortUsingJava7(a);
		jt.sortUsringJava8(a);
		
		for(String s:a){
			System.out.println(s);
		}
		
		jt.OptionalTest();
		
	}
	
	private void stream(){
		
	}
	
	private void localDate(){
		LocalDateTime date = LocalDateTime.now();
		
	}
	
	private void OptionalTest(){
		Optional<People> op = Optional.of(new People("ldw",18,"man"));
		System.out.println(op.get());
		Optional<People> op1 = Optional.ofNullable(null);
		System.out.println(op1);
	}
	
	private void sortUsingJava7(List<String> a){
		Collections.sort(a, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
	}
	
	private void sortUsringJava8(List<String> a){
		Collections.sort(a, (s1,s2) -> s1.compareTo(s2) );
	}

}
