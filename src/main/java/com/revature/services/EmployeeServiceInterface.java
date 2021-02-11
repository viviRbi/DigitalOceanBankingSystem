package com.revature.services;

public interface EmployeeServiceInterface {
	public void getAllBankAccountByCustomerId(int account_number);
	public void viewAllLogs() ;
	public void viewAllPendingBankAccounts();
	public void approveBankAccount(int rounting_number);
	public void rejectBankAccount(int rounting_number);
}

