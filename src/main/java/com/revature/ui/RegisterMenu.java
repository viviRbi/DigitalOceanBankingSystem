package com.revature.ui;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.revature.models.User;
import com.revature.services.CustomerService;
import com.revature.validate.Validate;
import com.revature.validate.ValidateImpl;

public class RegisterMenu implements Menu{
	private CustomerService c = new CustomerService();
	private User user = new User();
	private Validate v = new ValidateImpl();
	
	public void display() {
		int choice= 0; 
		String validateErr = "";
		do {

			System.out.println("============================================");
			System.out.println("Please type out some of your information \n");
			
			System.out.println("Username (5 or more characters)");
			String username = Menu.sc.nextLine().trim();
			validateErr += v.validateUsername(username);
			
			System.out.println("Password (5 or more characters)");
			String pass = Menu.sc.nextLine().trim();
			validateErr += v.validatePassword(pass);
			
			System.out.println("Birthday (format: 01/01/18699)");
			String birth = Menu.sc.nextLine().trim();
			validateErr += v.validateBirthday(birth);
			
			System.out.println("Phone (format: 000-000-0000 or 0000000000 or (000) 000-0000");
			String phone = Menu.sc.nextLine().trim();
			validateErr += v.validatePhone(phone);
			
			System.out.println("Address");
			String addr = Menu.sc.nextLine().trim();
			validateErr += v.validateAddress(addr);
			
			System.out.println("Zipcode");
			String zip = Menu.sc.nextLine().trim();
			validateErr += v.validateZipCode(zip);
			
			System.out.println("State (format: TX or tx)");
			String state = Menu.sc.nextLine().trim().toUpperCase();
			validateErr += v.validateState(state);
			
			System.out.println("Your starting balance");
			Double balance = Double.parseDouble(Menu.sc.nextLine());

			if (validateErr.trim() == "") {
				System.out.println(validateErr);
			} else {
				LocalDate birthday=LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				System.out.println("\u001B[34m Saving your infomation to our database... \u001B[0m");
				choice = c.registerNewAccount(username, pass, (java.sql.Date) Date.valueOf(birthday), phone, addr, zip, state, balance);
			}
		} while(choice != 1);
	}

	@Override
	public void setUser(User user) {	
		this.user = user;
	}
}
