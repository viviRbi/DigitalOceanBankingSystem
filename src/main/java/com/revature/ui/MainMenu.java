package com.revature.ui;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.models.Employee;
import com.revature.services.UserService;

public class MainMenu implements Menu{
	
	User user;
	
	public MainMenu() {
		super();
	}

	@Override
	public void display() {

			int choice= 0; 
			
			do {
				
				System.out.println("-----------------------------------\n\n Please press: "
						+ "\n 1 If you want to log in "
						+ "\n 2 If you want to register"
						+ "\n 3 If you want to quit");
				System.out.println("-----------------------------");

				choice = Integer.parseInt(Menu.sc.nextLine());

				switch( choice) {
				//As a user, I can login. - 2 points
				case 1:
					System.out.println("----------");
					System.out.println("Log in");
					System.out.println("-----------");
					
					// Get username and password. Store user info to User obj
					System.out.println("Please type your username and password.");
					System.out.println("Username:");
					String username = Menu.sc.nextLine();
					System.out.println("Password:");
					String password = Menu.sc.nextLine();
					UserService u = new UserService();
					user = u.checkUser(username, password);
					this.user = user;
					
					// Check if the user object belong to Employee or Customer class or null
					if (user instanceof Customer) {
						Menu customerMenu = new CustomerMenu();
						customerMenu.setUser(user);
						customerMenu.display();
					}else if (user instanceof Employee) {
						Menu employeeMenu = new EmployeeMenu();
						employeeMenu.setUser(user);
						employeeMenu.display();
					}else {
						System.out.println("-------------------------------------");
						System.out.println("\u001B[31mIncorrect username or password. Please try again\u001B[0m");
					}
					break;
				// End ---------------------
				// As a user, I can register for a customer account. - 3 points
				case 2:
					System.out.println("-------------------------------------");
					System.out.println("Register for a new account");
					System.out.println("-------------------------------------");
					Menu registerMenu = new RegisterMenu();
					registerMenu.display();
					break;
				// End ---------------------
				case 3: 
					break;	
				default:
					System.out.println("\u001B[31m-------------------------------------");
					System.out.println("Invalid input. Please follow the menu.");
					System.out.println("-------------------------------------\u001B[0m");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.getMessage();
						this.display();
					}
					break;
				}
			} while ( choice != 3);
	}


	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
}

