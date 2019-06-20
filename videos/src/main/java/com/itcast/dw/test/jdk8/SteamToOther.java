package com.itcast.dw.test.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.itcast.dw.test.model.People;

public class SteamToOther {
	/**
	 * 
	 * list、map、set转换
	 * @param args 
	 * @date 2019年6月20日
	 * @author liudawei
	 */
	public static void main(String[] args) {
		People m = new People("ldw",18,"man","18674051234");
		People m2 = new People("lsk",18,"woman","18674054321");
		People m3 = new People("ldw",18,"man","18674051234");
		
		List<People> list = new ArrayList<People>();
		list.add(m);
		list.add(m2);
		list.add(m3);
		
		//1、list转map
	/*	Map<String,String> map = new HashMap<String,String>();
		list.stream().forEach((mobile) -> map.put(mobile.getMobile(), mobile.getMobile()));
		for(Map.Entry<String, String> mp:map.entrySet()){
			System.out.println(mp.getKey() + " : " + mp.getValue());
		}*/
		
		//list转map,key重复会报错
	/*	Map<String, People> collect = list.stream().collect(Collectors.toMap(People::getUserName, People -> People));
		Map<String, String> collect1 = list.stream().collect(Collectors.toMap(People::getUserName, People::getMobile));
		
		for(Entry<String, People> me:collect.entrySet()){
			System.out.println("collect = " + me.getKey());
		}*/
		
		//2、map转list,sorted排序，三种排序
	/*	List<People> collect2 = collect.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey())).map(e -> e.getValue()).collect(Collectors.toList());
		List<People> collect3 = collect.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).map(e -> e.getValue()).collect(Collectors.toList());
		List<People> collect4 = collect.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> e.getValue()).collect(Collectors.toList());
		List<People> collect5 = collect.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
		for(People me:collect2){
			System.out.println("collect2 = " + me.getMobile());
		}*/
		
		//3、list filter过滤和计数
	/*	long count = collect2.stream().filter(e -> e.getAge() > 1).count();
		System.out.println("count = " + count);
		
		List<People> collect6 = collect2.stream().filter(e -> e.getAge() > 1).collect(Collectors.toList());
		for(People me:collect6){
			System.out.println(me.getMobile());
		}*/
		
		//4、list转set，去重
		Set<People> collect7 = list.stream().collect(Collectors.toSet());
		System.out.println("collect7.size() = " + collect7.size());
		for(People g:collect7){
			System.out.println(g.getUserName());
		}
		
		//5、list遍历
		list.stream().forEach(e -> {e.setMobile(e.getMobile().substring(0, 3) + "****" + e.getMobile().substring(7));});
		list.stream().distinct().forEach(e -> {System.out.println(e.getMobile());});
	
	}

}
