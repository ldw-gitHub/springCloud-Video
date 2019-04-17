package com.itcast.dw.model;

import java.io.Serializable;
import java.util.Date;

public class UserSession implements Serializable{
	
	private static final long serialVersionUID = -7274328032586125127L;
	
	private String sessiontoken;
	private int userid;
	private Date createtime;
	private Date updatetime;
	private int remenberme;
	private int isonline;
	public String getSessiontoken() {
		return sessiontoken;
	}
	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getRemenberme() {
		return remenberme;
	}
	public void setRemenberme(int remenberme) {
		this.remenberme = remenberme;
	}
	public int getIsonline() {
		return isonline;
	}
	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
