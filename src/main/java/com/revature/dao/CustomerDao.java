package com.revature.dao;

import java.sql.Date;

import com.revature.models.BankAccount;

public interface CustomerDao {

	public boolean applyNewAccount(int account_number, double balance);
	public int registerNewAccount(String username, String password, java.sql.Date birthday, String phone, String address, String zipcode, String state) ;
	
}
