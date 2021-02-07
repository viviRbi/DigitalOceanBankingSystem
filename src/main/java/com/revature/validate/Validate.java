package com.revature.validate;

public interface Validate {
	public String validateUsername(String username);
	public String validatePassword(String pass);
	public String validateBirthday(String birth);
	public String validatePhone(String phone);
	public String validateAddress(String addr);
	public String validateZipCode(String zip);
	public String validateState(String state);
}
