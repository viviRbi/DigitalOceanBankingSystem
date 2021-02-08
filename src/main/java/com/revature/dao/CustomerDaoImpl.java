package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.exceptions.DatabaseConnectivityException;
import com.revature.models.Transaction;
import com.revature.util.ConnectionUtil;

// Interaction with the databaseInteraction with the database
public class CustomerDaoImpl implements CustomerDao {
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

	@Override
	public List<Transaction> getTransferTransaction(int userId) {
		List<Transaction> transactions = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM pending_transaction WHERE transfered_customer_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getInt("id"));
				transaction.setCreate_at(rs.getString("create_at"));
				transaction.setTransfer_amount(rs.getDouble("transfer_amount"));
				transaction.setTransaction_name_id(rs.getInt("transaction_name_id"));
				transaction.setTransfer_customer_id(rs.getInt("transfer_customer_id"));
				transaction.setTransfered_customer_id(rs.getInt("transfered_customer_id"));
				transaction.setTransfer_bank_rounting(rs.getInt("transfer_bank_rounting"));  
				transaction.setTransfered_bank_rounting(rs.getInt("transfered_bank_rounting")); 
				transactions.add(transaction);
			}
		} catch (DatabaseConnectivityException | SQLException e) {
			e.getMessage();
		}
		return transactions;
	}

	@Override
	public boolean acceptTransaction(int id) {
		boolean accepted = false;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM pending_transaction WHERE id= ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				double amount =rs.getDouble("transfer_amount");
				int transfer_customer_id = rs.getInt("transfer_customer_id");
				int transfered_customer_id = rs.getInt("transfered_customer_id");
				int transfer_bank_rounting = rs.getInt("transfer_bank_rounting");  
				int transfered_bank_rounting = rs.getInt("transfered_bank_rounting"); 
				sql = "INSERT INTO transaction (transfer_amount, transaction_name_id, transfer_customer_id, transfered_customer_id, transfer_bank_rounting, transfered_bank_rounting) VALUES (?,?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setDouble(1,amount);
				pstmt.setInt(2,3);
				pstmt.setInt(3,transfer_customer_id);
				pstmt.setInt(4, transfered_customer_id);
				pstmt.setInt(5, transfer_bank_rounting);
				pstmt.setInt(6, transfered_bank_rounting);
				if (pstmt.executeUpdate() != 0) {
					accepted = true;
					System.out.println("\u001B[34m"+amount + " had been sucessfully transfered to your account. Please check your account using a rounting number to make sure.\u001B[0m");
					UserDaoImpl.log.info("Customer with Rounting number "+ transfer_bank_rounting + " and account number "+ transfered_customer_id + 
					 " had accept a transfer of"+ amount + " from other account with account number of "+ transfered_bank_rounting +" and rounting number of "+ transfered_bank_rounting );
				} 
			} else System.out.println("\u001B[31mCannot find pending transaction id. Please check again \u001B[0m");
		} catch (DatabaseConnectivityException | SQLException e) {
			e.getMessage();
		}
		return accepted;
	}

	@Override
	public boolean rejectTransaction(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
