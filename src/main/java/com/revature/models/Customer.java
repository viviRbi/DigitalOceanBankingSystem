package com.revature.models;

public class Customer extends User{
	
	private int id;
	private String username;
	private String password;
	private String birthday;
	private String address;
	private String zipcode;
	private String modifiedDate;
	
	public Customer() {super();}

	public Customer(int id, String username, String password, String birthday, String address, String zipcode, String modifiedDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.address = address;
		this.zipcode = zipcode;
		this.modifiedDate = modifiedDate;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String toString() {
		return "User had the id of " + this.id ;
	}
	
}
