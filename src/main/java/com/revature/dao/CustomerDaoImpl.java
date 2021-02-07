package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.revature.exceptions.DatabaseConnectivityException;
import com.revature.util.ConnectionUtil;

// Interaction with the databaseInteraction with the database
public class CustomerDaoImpl implements CustomerDao{
	Connection connection = null;
	
	// apply for a new account. This will insert info to the applied_bank_account table
	@Override
	public boolean applyNewAccount(int account_number, double balance) {
		boolean inserted = false;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO applied_bank_account(account_number,balance) values (?,?)";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, account_number);
			pstmt.setDouble(2, balance);
			
			if (pstmt.executeUpdate() != 0)
				inserted = true;
			else
				inserted = false;
			
		}catch (DatabaseConnectivityException | SQLException e) {
			e.getMessage();
		}
		return inserted;
	}
	
	// register to become a customer

	@Override
	public int registerNewAccount(String username, String password, java.sql.Date birthday, String phone, String address, String zipcode, String state) {
		int account_number = 0;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO customer(username, password, birthday, phone, address, zipcode, state) VALUES (?,?,?,?,?,?,?) RETURNING id";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setDate(3, birthday);
			pstmt.setString(4, phone);
			pstmt.setString(5, address);
			pstmt.setString(6, zipcode);
			pstmt.setString(7, state);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				account_number = rs.getInt(1);
			}else {
				System.out.println("\n \u001B[31m There is a problem saving your data to the database \u001B[0m ");
			}
			
		}catch (DatabaseConnectivityException | SQLException e) {
			e.getMessage();
			e.printStackTrace();
			e.getCause();
		}
		return account_number;
	}
}
