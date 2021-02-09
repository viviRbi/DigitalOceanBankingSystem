package com.revature.ui;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.models.Transaction;
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
		System.out.println("===========");
		System.out.println("Hi "+ user.getUsername() + ", Welcome to the Customer Menu");
		System.out.println("===========");
		//accept a money transfer from another account - 2 points (auto accept if same customer, different bank account)
		List<Transaction> transactions = c.getTransferTransaction(user.getId());
		if (transactions != null && transactions.size() > 0) { 
			System.out.println("\u001B[35m You got money! \u001B[0m");
			for (int i = 0; i < transactions.size(); i++) {
				Customer customer = c.getCustomerInfoByAccountNumber(transactions.get(i).getTransfer_customer_id());
				System.out.println(" -- \u001B[34m Pending Transaction id:\u001B[0m \u001B[31m "+transactions.get(i).getId()+" \u001B[0m -- ");
				System.out.println("\u001B[34m User "+customer.getUsername()+" transfer " + transactions.get(i).getTransfer_amount() + " amount of money to you \u001B[0m ");
			}
			
			int newChoice = 0;
			do {
				System.out.println("Please choose");
				System.out.println("1 to accept the chosen transaction");
				System.out.println("2 to reject the chosen transaction ");
				System.out.println("3 to go Back ");
				System.out.println("----------------------------------------------------");
				newChoice = Integer.parseInt(Menu.sc.nextLine());
				switch (newChoice) {
					case 1:
						System.out.println("Please type the transaction id of transaction you want to accept");
						int acceptId = Integer.parseInt(Menu.sc.nextLine());
						c.acceptTransaction(acceptId);
						break;
					case 2:
						System.out.println("Please type the transaction id of transaction you want to reject");
						int rejectId = Integer.parseInt(Menu.sc.nextLine());
						c.rejectTransaction(rejectId);
						break;
					case 3:
						break;
					default:
						System.out.println("\u001B[31m Please choose 1, 2, or 3 only\u001B[0m");
				}
			} while (newChoice != 3);
		}
		// get transfered_customer_id in pending_transaction table by customer id
		// get a list transfered routing, transfered username, amount. Tell user they have some money transfer from username to their bank with id of .. with the amount of ..
		// ask if they want 1.accept  2.reject 3.Back
		
		do {
			System.out.println("Please press:");
			
			System.out.println("1 Apply for a new bank account");
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
