package com.itcast.dw.model;

import java.io.Serializable;
import java.util.Date;

public class VideoComments implements Serializable{
	
	private static final long serialVersionUID = 7763012346923180774L;
	
	private int id;
	private int commentuserid;
	private int videoid;
	private String msg;
	private String commentusername;
	private Date createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommentuserid() {
		return commentuserid;
	}
	public void setCommentuserid(int commentuserid) {
		this.commentuserid = commentuserid;
	}
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCommentusername() {
		return commentusername;
	}
	public void setCommentusername(String commentusername) {
		this.commentusername = commentusername;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
