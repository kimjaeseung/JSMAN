package com.newha.vo;

public class User {
	private String userNo;
	private String name;
	private String password;
	private String thumbnail_path;
	private String id;
	
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", name=" + name + ", password=" + password + ", thumbnail_path="
				+ thumbnail_path + ", id=" + id + "]";
	}

	public User() {}
	
	public User(String userNo, String name, String password, String thumbnail_path, String id) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.password = password;
		this.thumbnail_path = thumbnail_path;
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getThumbnail_path() {
		return thumbnail_path;
	}

	public void setThumbnail_path(String thumbnail_path) {
		this.thumbnail_path = thumbnail_path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
