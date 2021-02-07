package com.revature.ui;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.services.CustomerService;
import com.revature.services.UserService;

public class CustomerMenu implements Menu{
	private Customer user;
	private CustomerService c ;
	private UserService u;

	public CustomerMenu() {
		this.c = new CustomerService();
		this.u = new UserService();
	}
	// Save all the user data like birthday, address in user variable. When build up, we might need them.
	public void setUser(User user) {
		this.user = (Customer) user;
	}

	@Override
	public void display() {
		int choice = 0;
		do {
			System.out.println("===========");
			System.out.println("Hi "+ user.getUsername() + ", Welcome to the Customer Menu");
			System.out.println("===========");
			System.out.println("Please press:");
			
			System.out.println("1 Apply for a new bank account");
			//accept a money transfer from another account - 2 points (auto accept if same customer, different bank account)
			System.out.println("2 Deposit, Withdraw, or Transfer Money");
			System.out.println("3 View balance of an account using rounting number");
			System.out.println("4 Log out");
			
			choice = Integer.parseInt(Menu.sc.nextLine());
			
			switch (choice) {
				//Apply for a new bank account with a starting balance - 3 points
				case 1:
					System.out.println("-------------------------------------");
					System.out.println("Apply for a new bank account");
					System.out.println("-------------------------------------");
					System.out.println("How much money you want to put in?");
					Double balance = Double.parseDouble(Menu.sc.nextLine()) ;
					c.applyNewAccount(user.getId(),balance);
					break;
				//End -------------------
				//make a withdrawal or deposit to a specific account - 2 points
				//post a money transfer to another account - 3 points
				//reject invalid transactions.(Ex: A withdrawal that would result in a negative balance.) 2 points
				case 2:
				int action;
				do {
					System.out.println("-------------------------------------");
					System.out.println("Deposit, Transfer, or Withdraw money");
					System.out.println("-------------------------------------");
					System.out.println("What do you want to do?");
					System.out.println("1 Deposit (Add money into your account)");
					System.out.println("2 Withdraw (Get your money out)");
					System.out.println("3 Transfer");
					System.out.println("4 Back");
					action = Integer.parseInt(Menu.sc.nextLine());
					int my_account_number = user.getId();
					double amount;
					switch (action) {
					case 1:
						System.out.println("-------------------------------------");
						System.out.println("Deposit");
						System.out.println("-------------------------------------");
						
						System.out.println("What is your account rounting number?");
						int deposit_my_rounting_number = Integer.parseInt(Menu.sc.nextLine());
						System.out.println("How much do you want to deposit?");
						amount = Double.parseDouble(Menu.sc.nextLine()) ;
						
						u.depositTransferWithdraw(0, 0, deposit_my_rounting_number, my_account_number,amount);
						break;
					case 2:
						System.out.println("---------");
						System.out.println("Withdraw");
						System.out.println("----------");
						System.out.println("What is your account rounting number?");
						int withdraw_my_rounting_number = Integer.parseInt(Menu.sc.nextLine());
						System.out.println("How much do you want to withdraw?");
						amount = Double.parseDouble(Menu.sc.nextLine()) ;
						
						u.depositTransferWithdraw(withdraw_my_rounting_number, my_account_number, 0, 0 ,amount);
						break;
					case 3:
						System.out.println("---------");
						System.out.println("Transfer");
						System.out.println("----------");
						
						System.out.println("What is your account rounting number?");
						int transfer_transfer_rounting_number = Integer.parseInt(Menu.sc.nextLine());
						System.out.println("What is your receiver's rounting number?");
						int transfer_transfered_rounting_number = Integer.parseInt(Menu.sc.nextLine());
						System.out.println("What is your receiver's account number?");
						int transfer_transfered_account_number = Integer.parseInt(Menu.sc.nextLine());
						System.out.println("How much do you want to deposit?");
						amount = Double.parseDouble(Menu.sc.nextLine()) ;
						
						u.depositTransferWithdraw(transfer_transfer_rounting_number, my_account_number, transfer_transfered_rounting_number, transfer_transfered_account_number, amount);
						break;
					//End -------------------
					case 4:
						break;
					default:
						System.out.println("-------------------------------------");
						System.out.println("Invalid input. Please follow the menu.");
						System.out.println("-------------------------------------");
					}
				} while (action !=4);
				break;
				// View the balance of a specific account - 1 point
				case 3:
					System.out.println("------------------------------------------------");
					System.out.println("View balance of an account using rounting number");
					System.out.println("------------------------------------------------");
					System.out.println("Please type the rounting number of the account you want to view?");
					int someone_rounting_number = Integer.parseInt(Menu.sc.nextLine());
					u.getBankAccountByRountingNumber(someone_rounting_number);
					break;
				//End -------------------
				case 4:
					break;
				default:
					System.out.println("\u001B[31m-----------------------------------------");
					System.out.println("No valid choice entered, please try again");
					System.out.println("-----------------------------------------\u001B[0m");
			}
		} while (choice != 4);
	}
}
