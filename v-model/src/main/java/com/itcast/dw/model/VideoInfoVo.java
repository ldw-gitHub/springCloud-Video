package com.itcast.dw.model;

import java.io.Serializable;
import java.util.Date;

public class VideoInfoVo implements Serializable{
	
	private static final long serialVersionUID = -2639829286599829185L;
	
	private int id;
	private String title;
	private String description;
	private int createuserid;
	private int click;
	private String videoType;
	private String imgpath;
	private String videopath;
	private Date createtime;
	private Date updatetime;
	private String isown;
	private int commentsnumber;
	public int getId() {
		return id;
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
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getIsown() {
		return isown;
	}
	public void setIsown(String isown) {
		this.isown = isown;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getCommentsnumber() {
		return commentsnumber;
	}
	public void setCommentsnumber(int commentsnumber) {
		this.commentsnumber = commentsnumber;
	}
	

}
