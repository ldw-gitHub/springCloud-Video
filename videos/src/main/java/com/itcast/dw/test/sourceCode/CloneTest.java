package com.itcast.dw.test.sourceCode;

import java.util.Date;

public class CloneTest implements Cloneable{
	
	private int age;
	private String name;
	private Date date;
	
	public CloneTest(int age, String name) {
		super();
		this.age = age;
		this.name = name;
		this.date = new Date();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	protected CloneTest clone(){
		CloneTest c = null;
		try {
			c = (CloneTest) super.clone();
			//深度拷贝，引用类型也要拷贝一份
			c.date = (Date) this.date.clone();
			
			//序列号复制
			
		} catch (CloneNotSupportedException e) {
		}
		return c;
	}
	
	

}
