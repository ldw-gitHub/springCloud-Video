package com.itcast.dw.vo;

import java.io.Serializable;

public class VideoVo implements Serializable{
	
	private static final long serialVersionUID = -9025272499994575599L;
	
	private int id;
	private String title;
	private String description;
	private int click;
	private String imgpath;
	private String videopath;
	
	public VideoVo(){
		
	}
	
	
	public VideoVo(int id, String title, String description, int click, String imgpath, String videopath) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.click = click;
		this.imgpath = imgpath;
		this.videopath = videopath;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getVideopath() {
		return videopath;
	}
	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
