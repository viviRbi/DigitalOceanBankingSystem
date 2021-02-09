package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.exceptions.DatabaseConnectivityException;
import com.revature.main.Application;
import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;


public class UserDaoImpl implements UserDao{
	Connection connection = null;
	
	private User u;
	public static Logger log=Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		super();
	}

	// Check to see if this is an employee or a customer or a new user
	@Override
	public User checkUser(String username, String password) {
		String role;
		try (Connection connection = ConnectionUtil.getConnection()){
			// Use set operation to merge rows of 2 table into one 
			//and search for employee or customer
				
			String sql = "SELECT 'Customer' AS role, id, username, password"
					+ " FROM customer"
					+ " WHERE username=? AND password = ?"
					+ " UNION"
					+ " SELECT 'Employee', id, username,password"
					+ " FROM employee"
					+ " WHERE username=? AND password = ?";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2, password);
			pstmt.setString(3,username);
			pstmt.setString(4, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				role = rs.getString("role");
				int id = rs.getInt("id");
				switch (role) {
				case "Customer":
					sql = "Select * from customer where id = ?";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1,id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						String birthday = rs.getString("birthday");
						String phone = rs.getString("phone");
						String address = rs.getString("address");
						String zipcode = rs.getString("zipcode");
						String state = rs.getString("state");
						String modified_date= rs.getString("modified_date");
						this.u = new Customer(id, username, password, birthday, address, zipcode, modified_date);
					}
					break;
				case "Employee":
					this.u = new Employee(id, username, password);
					break;
				default:
					System.out.println("null");
					this.u = null;
				}
			}
			
		}catch (DatabaseConnectivityException | SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
		return this.u;
	}

	@Override
	// Deposit is from real money transfer to bank account. So the transfered bank account is the user bank
	public boolean deposit(int transfered_bank_rounting, int transfered_account_number, double amount){
		boolean deposited = false;
		boolean bankAccountExists = checkIfBankAccountExist(getAllBankAccountByCustomerId (transfered_account_number), transfered_bank_rounting);
		if (bankAccountExists == true) {
			BankAccount account = this.getBankAccountByRountingNumber(transfered_bank_rounting, "bank_account");
			double account_balance =account.getBalance();
			if (amount < 0) {
				System.out.println("\u001B[31m---------------------------------------");
				System.out.println("Invalid transaction");
				System.out.println("You cannot deposit a negative amount");
				System.out.println("---------------------------------------\u001B[0m");
			} else {
				try (Connection connection = ConnectionUtil.getConnection()){
					String sql = "INSERT INTO transaction (transfer_amount, transaction_name_id, transfer_customer_id, transfered_customer_id, transfer_bank_rounting, transfered_bank_rounting) VALUES (?,?,NULL,?,NULL,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setDouble(1, amount);
					pstmt.setInt(2, 1);
					pstmt.setInt(3, transfered_account_number);
					pstmt.setInt(4, transfered_bank_rounting);

					if (pstmt.executeUpdate() != 0) {
						deposited = true;
						System.out.println("\u001B[34m"+amount + " had been sucessfully deposit to your bank account. Please check your account using a rounting number to make sure.\u001B[0m");
						log.info("Customer with Rounting number "+ transfered_bank_rounting + " and account number "+ transfered_account_number + 
						 " had deposit "+ amount );
					}
				}catch(DatabaseConnectivityException | SQLException e) {
					e.getMessage();
				}
			}
		} else {
			System.out.println("\033[31;1m---------------------------------------------------");
			System.out.println("This bank account does not exist. Please check your rounting number");	
			System.out.println("---------------------------------------------------\033[0m");
		}
		return deposited;
	}

	
	@Override
	// Withdraw is from bank account transfer to real money. So the transfer bank account is the user bank
	public boolean withdraw(int transfer_bank_rounting, int transfer_account_number, double amount) {
		boolean withdraw = false;
		boolean bankAccountExists = checkIfBankAccountExist(getAllBankAccountByCustomerId (transfer_account_number), transfer_bank_rounting);
		if (bankAccountExists) {
			BankAccount account = this.getBankAccountByRountingNumber(transfer_bank_rounting, "bank_account");
			double account_balance =account.getBalance();
			if (amount > account.getBalance()) {
				System.out.println("\u001B[31m---------------------------------------");
				System.out.println("You cannot withdraw this much");
				System.out.println("Your balance is only "+ account_balance);
				System.out.println("---------------------------------------\u001B[0m");
			} else if (amount < 0){
				System.out.println("\u001B[31m---------------------------------------");
				System.out.println("Invalid transaction");
				System.out.println("You cannot withdraw a negative amount");
				System.out.println("---------------------------------------\u001B[0m");
			} else {
				try (Connection connection = ConnectionUtil.getConnection()){
					String sql = "INSERT INTO transaction (transfer_amount, transaction_name_id, transfer_customer_id, transfered_customer_id, transfer_bank_rounting, transfered_bank_rounting) VALUES (?,?,?,NULL,?,NULL)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setDouble(1, amount);
					pstmt.setInt(2, 2);
					pstmt.setInt(3, transfer_account_number);
					pstmt.setInt(4, transfer_bank_rounting);

					if (pstmt.executeUpdate() != 0) {
						withdraw = true;
						System.out.println("\u001B[34m"+amount + " had been sucessfully withdraw from your bank account. Please check your account using a rounting number to make sure.\u001B[0m");
						log.info("Customer with Rounting number "+ transfer_bank_rounting + " and account number "+ transfer_account_number + 
						 " had withdraw "+ amount );
					}
				}catch(DatabaseConnectivityException | SQLException e) {
					e.getMessage();
				}
			}
		} else {
			System.out.println("\033[31;1m---------------------------------------------------");
			System.out.println("This bank account does not exist. Please check your rounting number");	
			System.out.println("---------------------------------------------------\033[0m");
		}
		
		return withdraw;
	}
	
	@Override
	public boolean transfer(int transfer_bank_rounting, int transfer_account_number,int transfered_bank_rounting, int transfered_account_number, double amount) {
		boolean transfered = false;
		boolean giverBankAccountExists = checkIfBankAccountExist(getAllBankAccountByCustomerId (transfer_account_number), transfer_bank_rounting);
		boolean receiverBankAccountExists = checkIfBankAccountExist(getAllBankAccountByCustomerId (transfered_account_number),  transfered_bank_rounting);
		
		if (giverBankAccountExists == false) System.out.println("\u001B[31m------------------------\nYour bank rounting number is wrong\n------------------------\u001B[0m");
		else if (receiverBankAccountExists == false) System.out.println("\u001B[34m------------------------\nYour receiver bank rounting number or account number is wrong\n------------------------\u001B[0m");
		else {
			if (transfer_bank_rounting == transfered_bank_rounting && transfer_account_number==transfered_account_number) {
				System.out.println("\u001B[31m---------------------------------------------------");
				System.out.println("You cannot transfer money to your same bank account");	
				System.out.println("---------------------------------------------------\u001B[0m");
			}else {
				BankAccount account = this.getBankAccountByRountingNumber(transfer_bank_rounting, "bank_account");
				double account_balance =account.getBalance();

				if (amount > account_balance) {
					System.out.println("\u001B[31m---------------------------------------");
					System.out.println("You cannot perform this transaction");
					System.out.println("Your balance is only "+ account_balance);
					System.out.println("---------------------------------------\u001B[0m");
				} else if (amount <= 0){
					System.out.println("\u001B[31m---------------------------------------");
					System.out.println("Invalid transaction");
					System.out.println("You cannot transfer a negative or null amount");
					System.out.println("---------------------------------------\u001B[0m");
				} else {
					try (Connection connection = ConnectionUtil.getConnection()){
						if (transfer_account_number == transfered_account_number) {
							String sql = "INSERT INTO transaction (transfer_amount, transaction_name_id, transfer_customer_id, transfered_customer_id, transfer_bank_rounting, transfered_bank_rounting) VALUES (?,?,?,?,?,?)";
							PreparedStatement pstmt = connection.prepareStatement(sql);
							pstmt.setDouble(1,amount);
							pstmt.setInt(2,3);
							pstmt.setInt(3,transfer_account_number);
							pstmt.setInt(4, transfered_account_number);
							pstmt.setInt(5, transfer_bank_rounting);
							pstmt.setInt(6, transfered_bank_rounting);
							if (pstmt.executeUpdate() != 0) {
								transfered = true;
								System.out.println("\u001B[34m"+amount + " had been sucessfully transfered. Please check your account using a rounting number to make sure.\u001B[0m");
								log.info("Customer with Rounting number "+ transfer_bank_rounting + " and account number "+ transfer_account_number + 
								 " make an auto transfer "+ amount + " to his/her account with rounting number of "+ transfered_bank_rounting );
							} else {
								transfered = false; 
							}
							
						}else {
							String sql = "INSERT INTO pending_transaction (transfer_amount, transaction_name_id, transfer_customer_id, transfered_customer_id, transfer_bank_rounting, transfered_bank_rounting) VALUES (?,?,?,?,?,?)";
							PreparedStatement pstmt = connection.prepareStatement(sql);
							pstmt.setDouble(1,amount);
							// 3 is transaction_name_id for transfer. If wrongly type, it cannot auto delete when the receiver accept the money (database trigger function condition) 
							pstmt.setInt(2,3);
							pstmt.setInt(3,transfer_account_number);
							pstmt.setInt(4, transfered_account_number);
							pstmt.setInt(5, transfer_bank_rounting);
							pstmt.setInt(6, transfered_bank_rounting);
							if (pstmt.executeUpdate() != 0) {
								transfered = true;
								sql = "SELECT username from Customer where id = ?";
								pstmt = connection.prepareStatement(sql);
								pstmt.setInt(1,transfered_account_number);
								ResultSet rs = pstmt.executeQuery();
								
								if (rs.next()) {
									String otherUser =  rs.getString("username");
									System.out.println("\u001B[34m"+amount + " had been sucessfully transfer to user '" + otherUser + "'. It is still in pending until user "+ otherUser + " accept\u001B[0m");
									}
								log.info("Bank account with Rounting number "+ transfer_bank_rounting + " and account number "+ transfer_account_number + 
										" make a pending transfer "+ amount + " to the account with rounting number of "+ transfered_bank_rounting + " and account number of "+ transfered_account_number);
							} else System.out.println("\033[31;Something went wrong. Please check all the rounting and account number\033[0m");
						} 
					}catch (DatabaseConnectivityException | SQLException e){
						System.out.println("\033[31;1mSomething went wrong. Please check all the rounting and account number\033[0m");
						e.getMessage();
						System.out.println("---------------------------------------");
					} 
				}
			}
		}
		return transfered;
	}
	
	
	@Override
	public BankAccount getBankAccountByRountingNumber(int rounting_number,String table) {
		BankAccount account = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM "+table+" WHERE rounting_number = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,rounting_number);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				account= new BankAccount();
				account.setRounting_number(rounting_number);
				account.setAccount_number(rs.getInt("account_number"));
				account.setBalance(rs.getDouble("balance"));
				account.setModifiedDate(rs.getString("modified_date"));
			}
		}catch (DatabaseConnectivityException | SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
		return account;
	}
	
	@Override
	// For employee to view
	public List<BankAccount> getAllBankAccountByCustomerId (int account_number) {
		List<BankAccount> bank_accounts = new ArrayList<BankAccount>();
		boolean existed = false; 
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_account WHERE account_number = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,account_number);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				existed = true;
				BankAccount bank = new BankAccount();
				bank.setAccount_number(account_number);
				bank.setBalance(rs.getDouble("balance"));
				bank.setRounting_number(rs.getInt("rounting_number"));
				bank.setModifiedDate(rs.getString("modified_date"));
				bank_accounts.add(bank);
			}
			if (existed == false) System.out.println("\033[31;1m This account number does not exists\033[0m");
		} catch (DatabaseConnectivityException | SQLException e){
			System.out.println("\033[31;1mSomething went wrong\033[0m");
			System.out.println(e.getMessage());
			System.out.println("---------------------------------------");
		} 
		return bank_accounts;
	}
	
	@Override
	public boolean checkIfBankAccountExist(List<BankAccount> b, int rounting_number) {
		boolean exists = false;
		for (int i=0; i<b.size(); i++){
			if (b.get(i).getRounting_number() == rounting_number) exists = true;
		}
		return exists;
	}

	@Override
	public Customer getCustomerInfoByAccountNumber(int id) {
		Customer c = null;
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer WHERE id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				c= new Customer();
				c.setId(id);
				c.setUsername(rs.getString("username"));
				c.setPassword(rs.getString("password"));
				c.setAddress(rs.getString("address"));
				c.setBirthday(rs.getString("birthday"));
				c.setModifiedDate(rs.getString("modified_date"));
				c.setZipcode(rs.getString("zipcode"));
				c.setState(rs.getString("state"));
				c.setPhone(rs.getString("phone"));
			}
		}catch (DatabaseConnectivityException | SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
		return c;
	}
	
	
}
