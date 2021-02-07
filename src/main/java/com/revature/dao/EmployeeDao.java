package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface EmployeeDao {
	public boolean approveBankAccount(BankAccount b);
	public boolean rejectBankAccount(int rounting_number);
	public List<BankAccount> viewAllPendingBankAccounts();
}
