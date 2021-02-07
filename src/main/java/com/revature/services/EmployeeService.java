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

public class EmployeeService {
	private UserDao u = new UserDaoImpl();
	private EmployeeDao e = new EmployeeDaoImpl();
	
	
	public EmployeeService() {
		super();
	}
	// approve or reject an account
	
	//  view a customer's bank accounts
	public void getAllBankAccountByCustomerId(int account_number){
		List<BankAccount> b = new ArrayList<>();
		b = u.getAllBankAccountByCustomerId(account_number);
		if (b != null) {
			for (int i=0; i<b.size(); i++){
				System.out.println("\u001B[34m |  Customer id: " + account_number + " -- Rounting number: " 
			+b.get(i).getRounting_number() + " -- Balance: " +b.get(i).getBalance() +" -- Modified Date: " + b.get(i).getModifiedDate() + "  |\u001B[0m");
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
}
