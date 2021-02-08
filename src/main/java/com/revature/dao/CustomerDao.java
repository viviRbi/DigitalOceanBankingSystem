package com.revature.dao;

import java.sql.Date;

import com.revature.models.BankAccount;
import com.revature.models.Transaction;
import java.util.List;

public interface CustomerDao {

	public boolean applyNewAccount(int account_number, double balance);
	public int registerNewAccount(String username, String password, java.sql.Date birthday, String phone, String address, String zipcode, String state) ;
	public List<Transaction> getTransferTransaction(int userId);
	public boolean acceptTransaction(int id);
	public boolean rejectTransaction(int id);
}
