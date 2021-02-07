package com.revature.ui;

import java.util.Scanner;

import com.revature.models.User;

public interface Menu {

	public static Scanner sc = new Scanner(System.in);
	
	public void display();
	public void setUser(User user);
	
}

