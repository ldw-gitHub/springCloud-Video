package com.itcast.dw.test.model;

import java.io.Serializable;

public class People implements Serializable{

	private static final long serialVersionUID = 657266587749448506L;
	
	private String userName;
	private int age;
	private String gender;
	
	public People(){}
	
	public People(String userName, int age, String gender) {
		super();
		this.userName = userName;
		this.age = age;
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
