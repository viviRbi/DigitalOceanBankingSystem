package com.revature.main;

import com.revature.exceptions.DatabaseConnectivityException;
import com.revature.ui.MainMenu;
import com.revature.ui.Menu;
import com.revature.util.ConnectionUtil;

public class Application {
	
	public static void main (String args[]) {
		Menu m = new MainMenu();
		System.out.println("\033[31;1mWelcome to Digital Ocean Banking System!\033[0m");
		System.out.println("-----------------------------------");
		
		m.display();
		
		Menu.sc.close();
		System.out.println("Goodbye!");
	}
}
