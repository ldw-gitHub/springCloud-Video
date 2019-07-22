package com.itcast.dw.test.sourceCode;

public enum Labels {
	
	TRAFFIC("交通","traffic"),PHONE("手机","phone");
	
	private String name;
	private String value;
	
	private Labels(String name,String value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public static String getName(String value){
		Labels[] types = Labels.values();
		for(Labels e:types){
			if(e.getValue().equals(value)){
				return e.getName();
			}
		}
		return "";
	}
	
	public static boolean isContain(String value){
		Labels[] types = Labels.values();
		for(Labels e:types){
			if(e.getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	

}
