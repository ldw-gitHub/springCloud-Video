package com.itcast.dw.test.sourceCode;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainTest {

	public static void main(String[] args) throws UnknownHostException {
		CloneTest cloneTest = new CloneTest(1,"大伟");

		CloneTest clone = cloneTest.clone();

		cloneTest.setAge(2);
		cloneTest.setName("海洋");
		//cloneTest.setDate(DateUtils.string2Date("2017-07-16", "yyyy-MM-dd"));

		System.out.println(cloneTest.getAge());
		System.out.println(cloneTest.getName());
		System.out.println(cloneTest.getDate());

		System.out.println(clone.getAge());
		System.out.println(clone.getName());
		System.out.println(clone.getDate());

		InetAddress[] allByName = InetAddress.getAllByName("localhost");

		for(InetAddress e:allByName){
			System.out.println(e.getHostAddress());
		}

		InetAddress localHost = InetAddress.getLocalHost();

		System.out.println(localHost.getHostAddress());

		InetAddress[] copyArray = new InetAddress[allByName.length];

		System.arraycopy(allByName, 0, copyArray, 0, allByName.length);

		for(InetAddress e:copyArray){
			System.out.println(e.getHostAddress());
		}


	}

}
