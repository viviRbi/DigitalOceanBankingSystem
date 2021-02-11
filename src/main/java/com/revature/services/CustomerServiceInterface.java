package com.revature.services;

import java.util.List;

import com.revature.models.Transaction;

public interface CustomerServiceInterface {
	public void applyNewAccount(int account_number, double balance) ;
	public int registerNewAccount(String username, String password, java.util.Date birthday, String phone, String address, String zipcode, String state, double balance);
	public List<Transaction> getTransferTransaction(int userId);
	public void acceptTransaction(int id);
	public void rejectTransaction(int id);
}
