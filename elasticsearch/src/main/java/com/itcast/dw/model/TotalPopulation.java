package com.itcast.dw.model;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName="totalpopulation",type="baotou")
public class TotalPopulation implements Serializable{
	
	private static final long serialVersionUID = -935745954001976836L;
	
	private String id;
	private String count;
	private String lat;
	private String lng;
	private String time;
	
	public TotalPopulation(){
		
	}
	
	
	public TotalPopulation(String id, String count, String lat, String lng, String time) {
		super();
		this.id = id;
		this.count = count;
		this.lat = lat;
		this.lng = lng;
		this.time = time;
	}


	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
