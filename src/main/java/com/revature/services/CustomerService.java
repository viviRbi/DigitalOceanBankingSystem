package com.revature.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Transaction;
import com.revature.ui.Menu;


public class CustomerService extends UserService{
	private CustomerDao c;
	
	public CustomerService() {
		super();
		 this.c = new CustomerDaoImpl();
	}
	
	public void applyNewAccount(int account_number, double balance) {
		boolean inserted = c.applyNewAccount(account_number,balance);
		if (inserted == true) {
			System.out.println("\u001B[34mA new account was created with a balance of "+balance+".");
			System.out.println("Please wait for 2-5 business days for our employee to approved.\u001B[0m");
		} else {
			System.out.println("\u001B[32mThere is some problems inserting your account.\u001B[0m");
		}
	}
	
	public int registerNewAccount(String username, String password, java.util.Date birthday, String phone, String address, String zipcode, String state, double balance) {
		int choice =0;
		boolean bankAccountCreated = false;
		int account_number = c.registerNewAccount(username, password, (Date) birthday, phone, address, zipcode, state);
		if (account_number > 0) {
			bankAccountCreated = c.applyNewAccount(account_number, balance);
			if (bankAccountCreated == true) {
				System.out.println("\u001B[34m Your balance is: "+balance+".");
				System.out.println("Please wait for 2-5 business days for our employee to approved.\u001B[0m");
				choice =1;
			}else {
				System.out.println("\u001B[31mThere is some problems inserting your account.\u001B[0m");
			}
		}
		return choice;
	}
	
	public List<Transaction> getTransferTransaction(int userId) {
		List<Transaction> transferTransacions = c.getTransferTransaction(userId);
		return transferTransacions;
	}
	
	public void acceptTransaction(int id) {
		boolean accepted = c.acceptTransaction(id);
		if (accepted == true) System.out.println("\u001B[34m Thank you for using our service \u001B[0m \n");
		else System.out.println("\u001B[31m Cannot insert into database \u001B[0m \n");
		
	}
	
	public void rejectTransaction(int id) {
		boolean deleted = c.rejectTransaction(id);
		if (deleted == true) {
			System.out.println("\u001B[34m You have refuse. The transaction is cancel \u001B[0m \n");
		} else System.out.println("\u001B[31m Cannot cancel this pending transaction \u001B[0m \n");
	}
}
