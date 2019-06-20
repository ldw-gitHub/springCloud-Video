package com.itcast.dw.test.model;

import java.io.Serializable;

public class People implements Serializable{

	private static final long serialVersionUID = 657266587749448506L;
	
	private String userName;
	private int age;
	private String gender;
	private String mobile;
	
	public People(){}
	
	public People(String userName, int age, String gender,String mobile) {
		super();
		this.userName = userName;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(obj instanceof People){
			People vo = (People) obj;
			if(vo.age == this.age && vo.gender.equals(this.gender) && vo.mobile.equals(this.mobile) && vo.userName.equals(this.userName)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return this.userName + this.age + this.mobile + this.gender;
	}
	
	@Override
	public int hashCode() {
		return userName.hashCode() + mobile.hashCode() + gender.hashCode() + age;
	}

}
