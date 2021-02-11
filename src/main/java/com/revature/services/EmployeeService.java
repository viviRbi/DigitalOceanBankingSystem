package com.revature.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.BankAccount;

public class EmployeeService extends UserService implements EmployeeServiceInterface{
	private UserDao u = new UserDaoImpl();
	private EmployeeDao e = new EmployeeDaoImpl();
	
	
	public EmployeeService() {
		super();
	}
	
	//  view a customer's bank accounts
	public void getAllBankAccountByCustomerId(int account_number){
		List<BankAccount> b = new ArrayList<>();
		b = u.getAllBankAccountByCustomerId(account_number);
		if (b != null) {
			for (int i=0; i<b.size(); i++){
				System.out.println("\u001B[34m |-- Customer id: " + account_number + " -- Rounting number: " 
			+b.get(i).getRounting_number() + " -- Balance: " +b.get(i).getBalance() +" -- Modified Date: " + b.get(i).getModifiedDate() + "  \u001B[0m");
			}
		} else {
			System.out.println("\u001B[31m------------------------------------------------");
			System.out.println("Unable to fetch bank accounts from this account number");
			System.out.println("------------------------------------------------\u001B[0m");
		}
		
	}
	// view a log of all transactions
	public void viewAllLogs() {
		String fileName = "src/log.out";
        String line = null;
		  try {
	            FileReader fileReader = new FileReader(fileName);

	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                System.out.println(line );
	            }   

	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("\u001B[31m Unable to open file '" + fileName + "'\u001B[0m");                
	        }
	        catch(IOException ex) {
	            System.out.println("\u001B[31m Error reading file '" + fileName + "'\u001B[0m");                  
	        }
	}
	
	public void viewAllPendingBankAccounts() {
		List<BankAccount> b = new ArrayList<>();
		b = e.viewAllPendingBankAccounts();
		if (b != null) {
			if (b.size() != 0) {
				System.out.println("All pending bank account: " + b.size());
				for (int i=0; i<b.size(); i++){
					System.out.println("\u001B[34m|-- Customer id: " + b.get(i).getAccount_number() + " -- Rounting number: " 
							+b.get(i).getRounting_number() + " -- Balance: " +b.get(i).getBalance() +" -- Modified Date: " + b.get(i).getModifiedDate() + "  \u001B[0m");
				}
			}else {
				System.out.println("\u001B[34m  There is no pending bank account right now \u001B[0m");
			}
		} else {
			System.out.println("\u001B[31m------------------------------------------------");
			System.out.println("Unable to fetch all pending bank accountsr");
			System.out.println("------------------------------------------------\u001B[0m");
		}
	}
	
	public void approveBankAccount(int rounting_number) {
		BankAccount pending_account = u.getBankAccountByRountingNumber(rounting_number, "applied_bank_account");
		if (pending_account == null) System.out.println("\u001B[31m There is no such rounting number in pending bank account table. It does not exists \u001B[0m \n");
		else {
			boolean inserted = e.approveBankAccount(pending_account);
			if (inserted) System.out.println("\u001B[34m Bank account with the id of "+pending_account.getRounting_number()+" had been inserted into the database "
					+ "\n Its information had been removed from the pending bank account table \u001B[0m ");
			
			else System.out.println("\u001B[31m This bank account cannot be insert into the datbase\u001B[0m ");
		}
	}
	
	// delete bank account
	public void rejectBankAccount(int rounting_number) {
		boolean deleted = e.rejectBankAccount(rounting_number);
		if (deleted == true) {
			System.out.println("\u001B[34m Bank account with the id of "+rounting_number+" had been delete from the database \u001B[0m ");
		} else System.out.println("\u001B[31m Cannot find bank account with the id of "+rounting_number+" from the database \u001B[0m ");
	}
}
