package com.revature.dao;

import com.revature.models.BankAccount;

public interface EmployeeDao {
	public boolean approveBankAccount(int rounting_number, int account_number, double balance);
	public boolean rejectBankAccount(int rounting_number);
	public String viewAllTransactionLogs();
	public BankAccount viewBankAccountUsingAccountNumber(int account_number);
}
