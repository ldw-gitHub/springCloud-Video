package com.itcast.dw.model;

/**
 * 后台登录用户
 */
public class AdminUser {

	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 姓名
	 */
	private String userName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
