package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BankAccountCrudDaoImpl {
	Connection connection = null;
	PreparedStatement stmt = null;
	
	// deposit, withdraw, transfer. Logic for them stored in postgresql function
	public boolean depositeWithdrawTransfer(int transfer_bank_rounting,int transfer_customer_account,int transfered_bank_rounting,int transfered_customer_account, double transfer_money) {
		// Insert to transaction table
		return true;
	}
}
