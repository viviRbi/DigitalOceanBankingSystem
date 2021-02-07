package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.models.BankAccount;

public class EmployeeDaoImpl implements EmployeeDao{
	
	// move from applied_bank_account table to bank_account table. 
	//stored data from  applied_bank_account, then delete row in applied_bank_account 
	//then insert to bank account table
	public boolean approveBankAccount(int rounting_number, int account_number, double balance) {
		// move to bank_account
		// auto delete applied account in database
		return true;
	}
	
	// delete bank account
	public boolean rejectBankAccount(int account_number) {
		
		return true;
	}
	
	public String viewAllTransactionLogs() {
		// Access the transaction table
		// concat all the log from all row using for loop
		return "AA";
	}
	
	public BankAccount viewBankAccountUsingAccountNumber(int account_number) {
		BankAccount b = null;
		return b;
	}
	
	// can try to view bank account using other method later
}

