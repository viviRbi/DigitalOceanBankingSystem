package com.revature.services;

import java.sql.Date;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;


public class CustomerService {
	private CustomerDao c;
	
	public CustomerService() {
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
}
