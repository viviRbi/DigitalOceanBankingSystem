package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.models.User;

// Customer and employee can all use these method
public class UserService implements UserServiceInterface{
	private User user;
	private UserDao u;
	
	public UserService() {
		this.u = new UserDaoImpl();
	}
	
	public User checkUser(String username, String password){
		this.user = u.checkUser(username, password);
		return this.user;
	}
	
	// This function is in UserService because when build up, Employees should be able to do these transaction as well
	public void depositTransferWithdraw(int transfer_bank_rounting, int transfer_account_number,int transfered_bank_rounting, int transfered_account_number, double amount) {
		if(transfer_bank_rounting == 0 && transfer_account_number == 0) {
			u.deposit(transfered_bank_rounting, transfered_account_number, amount);
		} else if (transfered_bank_rounting == 0 && transfered_account_number == 0) {
			u.withdraw(transfer_bank_rounting, transfer_account_number, amount);
		} else {
			u.transfer(transfer_bank_rounting, transfer_account_number, transfered_bank_rounting, transfered_account_number, amount);
		}
	}
	
	// Get a bank account using Rounting number
	public void getBankAccountByRountingNumber(int someone_rounting_number) {
		BankAccount b = u.getBankAccountByRountingNumber(someone_rounting_number, "bank_account");
		System.out.println("\u001B[34mThis account balance is " + b.getBalance() + "\u001B[0m");
	}
	
	public Customer getCustomerInfoByAccountNumber(int id) {
		Customer c = u.getCustomerInfoByAccountNumber(id);
		return c;
	}
}
