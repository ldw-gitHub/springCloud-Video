package com.itcast.dw.test.jdk8;

import java.util.Optional;
import java.util.function.Consumer;

import com.itcast.dw.test.model.People;

public class OptionTest {
	
	/**
	 * 
	 * 判断对象是否为null
	 * @param args 
	 * @date 2019年6月20日
	 * @author liudawei
	 */
	public static void main(String[] args) {
		Optional<People> op = Optional.of(new People("ldw",18,"man","18674051111"));
		Optional<People> op1 = Optional.ofNullable(null);
		Optional<People> op2 = Optional.ofNullable(new People("ldw",18,"man","18674051111"));
		Optional<People> op3 = Optional.ofNullable(new People(null,18,"man","18674051111"));
		
		//判断值是否存在
		System.out.println(op.isPresent());
		System.out.println(op1.isPresent());
		System.out.println(op2.isPresent());
		
		//null则调用new ，非null使用原对象
		System.out.println(op.orElse(new People("lsk",18,"women","18674061111")).getUserName());
		System.out.println(op1.orElse(new People("lsk",18,"women","18674061111")).getUserName());
		System.out.println(op2.orElse(new People("lsk",18,"women","18674061111")).getUserName());
		
		//判断是否null，执行
		op.ifPresent(new Consumer<People>() {
			@Override
			public void accept(People p) {
				System.out.println(p.getUserName() + " : " + p.getMobile());
			}
		});
		op1.ifPresent(new Consumer<People>() {
			@Override
			public void accept(People p) {
				System.out.println(p.getUserName() + " : " + p.getMobile());
			}
		});
		
		//to map 操作元素,可以判断多层是否为null
		System.out.println(op3.map(a -> a.getAge()).get());
		op3.map(a -> a.getUserName())
		   .orElseThrow(() -> new IllegalArgumentException(" the param is error !"));
		
		//抛异常
		op.orElseThrow(() -> {throw new IllegalStateException();});
		op1.orElseThrow(() -> {throw new IllegalStateException();});
		
		
		
	}

}
