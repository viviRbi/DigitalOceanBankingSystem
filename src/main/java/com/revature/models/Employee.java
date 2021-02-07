package com.revature.models;

public class Employee extends User{
	
	private int id;
	private String username;
	private String password;
	
	public Employee() {super();}
	
	public Employee(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return "User had the id of " + this.id + ". User username is " + this.username;
	}
}
