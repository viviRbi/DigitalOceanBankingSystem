package com.revature.models;

public class BankAccount {
//	rounting number = bank account id
	private int rounting_number;
//	account number = customer id
	private int account_number;
	private double balance;
	private String modifiedDate;
	
	public BankAccount(int rounting_number, int account_number, double balance, String modifiedDate) {
		this.rounting_number = rounting_number;
		this.account_number = account_number;
		this.balance = balance;
		this.modifiedDate = modifiedDate;
	}
	public BankAccount() {
		super();
	}
	// Method
	public int getRounting_number() {
		return rounting_number;
	}
	public void setRounting_number(int rounting_number) {
		this.rounting_number = rounting_number;
	}
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getModifiedDate() {
		return  modifiedDate;
	}
	public void setModifiedDate(String  modifiedDate) {
		this. modifiedDate =  modifiedDate;
	}
}
