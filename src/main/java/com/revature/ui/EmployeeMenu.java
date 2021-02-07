package com.revature.ui;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.services.EmployeeService;
import com.revature.services.UserService;

public class EmployeeMenu implements Menu{
	private Employee employee;
	private EmployeeService e;
	private UserService u;
	
	public EmployeeMenu() {
		this.u = new  UserService();
		this.e =new EmployeeService();
	}
	
	@Override
	public void setUser(User user) {
		this.employee  = (Employee) user;
		
	}
	
	@Override
	public void display() {
		int choice = 0;
		do {
			System.out.println("===========");
			System.out.println("Hi " + employee.getUsername() +", Welcome to the Employee Menu");
			System.out.println("===========");
			System.out.println("Please press:");

			System.out.println("1 List all pending account rounting numbers");
			System.out.println("2 Approve or reject applied acount base on rounting number");
			System.out.println("3 View bank accounts using customer id (account_number)");
			System.out.println("4 View a log of all transactions");
			System.out.println("5 Log out");
			
			if (Menu.sc.hasNextInt()) {
				choice = Integer.parseInt(Menu.sc.nextLine());
			}
			
			switch (choice) {
				//As an employee, I can approve or reject an account - 2 points
				case 1:
					
					break;
				case 2:
					
					break;
				// End ------------------------
				// As an employee, I can view a customer's bank accounts - 1 point
				case 3:
					System.out.println("------------------------------------------------");
					System.out.println("View all bank accounts of a customer by their id (account number)");
					System.out.println("------------------------------------------------");
					System.out.println("Please type out his/her account number");
					int account_number = Integer.parseInt(Menu.sc.nextLine());
					e.getAllBankAccountByCustomerId(account_number);
					break;
				// End ------------------------
				// As an employee, I can view a log of all transactions - 2 points
				case 4:
					e.viewAllLogs();
					break;
				// End ------------------------
				case 5:
					break;
				default:
					System.out.println("No valid choice entered, please try again");
			}
			
		} while (choice != 7);
	}

}
