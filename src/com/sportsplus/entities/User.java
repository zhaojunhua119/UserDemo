package com.sportsplus.entities;

public class User {
	//unique android id
	private String id;
	private String Email;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public  String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
