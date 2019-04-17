package com.itcast.dw.model;

import java.io.Serializable;
import java.util.Date;

public class FileModel implements Serializable{
	private static final long serialVersionUID = -4665685146809881874L;
	
	private int id;
	private String filename;
	private double filesize;
	private int createuserid;
	private int download;
	private int downloadnumber;
	private String filepath;
	private Date createtime;
	private Date updatetime;
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public double getFilesize() {
		return filesize;
	}
	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public int getDownloadnumber() {
		return downloadnumber;
	}
	public void setDownloadnumber(int downloadnumber) {
		this.downloadnumber = downloadnumber;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	

}
