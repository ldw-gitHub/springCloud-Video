package com.itcast.dw.page;

import java.io.Serializable;

public class PageModel implements Serializable{
	
	private static final long serialVersionUID = 8312469357159276326L;
	private int pageNum;   		 //第几页
	private int pageSize;    	 //每页记录数
	
	private Long totals;      	 //总记录数
	private Integer pages;       //总页数
	
	

    public PageModel(){
    
    }
	
    public PageModel(int pageNum, int pageSize){
    	this.setPageNum(pageNum);
    	this.setPageSize(pageSize);
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Long getTotals() {
		return totals;
	}

	public void setTotals(Long totals) {
		this.totals = totals;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

}
