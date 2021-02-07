package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DatabaseConnectivityException;
import com.revature.models.BankAccount;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{
	
	public boolean approveBankAccount(BankAccount b) {
		boolean inserted = true;
		// auto delete pending_bank_account in database as soon as this sql insert in Bank_Account table
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_account(rounting_number, account_number,balance) VALUES (?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, b.getRounting_number());
			pstmt.setInt(2, b.getAccount_number());
			pstmt.setDouble(3, b.getBalance());
			if (pstmt.executeUpdate() != 0) inserted = true; 
			else System.out.println("\u001B[31m Bank account not inserted\u001B[0m ");
		} catch (DatabaseConnectivityException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return inserted;
	}
	
	// delete bank account
	public boolean rejectBankAccount(int rounting_number) {
		boolean deleted = true;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM applied_bank_account WHERE rounting_number = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, rounting_number);
			if (pstmt.executeUpdate() != 0) deleted = true; 
			else System.out.println("\u001B[31m BCannot delete this pending account in the database \u001B[0m ");
		} catch (DatabaseConnectivityException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return deleted;
	}

	@Override
	public List<BankAccount> viewAllPendingBankAccounts() {
		List<BankAccount> banks = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM applied_bank_account";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BankAccount bank = new BankAccount();
				bank.setAccount_number(rs.getInt("account_number"));
				bank.setRounting_number(rs.getInt("rounting_number"));
				bank.setBalance(rs.getDouble("balance"));
				bank.setModifiedDate(rs.getString("modified_date"));
				banks.add(bank);
			}
		}catch (DatabaseConnectivityException | SQLException e){
			System.out.println(e.getMessage());
		}
		return banks;
	}
	
}

