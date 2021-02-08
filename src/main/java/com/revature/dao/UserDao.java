package com.revature.dao;

import java.util.List;

import com.revature.exceptions.NotEnoughBalanceException;
import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.models.User;

public interface UserDao {
	public User checkUser(String username, String password);
	public boolean deposit(int transfered_bank_rounting, int transfered_account_number, double amount);
	public boolean withdraw(int transfer_bank_rounting, int transfer_account_number, double amount);
	public boolean transfer(int transfer_bank_rounting, int transfer_account_number, int transfered_bank_rounting, int transfered_account_number, double amount);
	public BankAccount getBankAccountByRountingNumber(int rounting_number, String table);
	public List<BankAccount> getAllBankAccountByCustomerId(int account_number);
	public boolean checkIfBankAccountExist(List<BankAccount> b, int rounting_number);
	public Customer getCustomerInfoByAccountNumber(int id);
}
