package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

import com.revature.exceptions.DatabaseConnectivityException;

public class ConnectionUtil {
	
	// No object should be instantiated using this class
	private ConnectionUtil() {
		super();
	}
	
//	Using PC environment variable
	public static Connection getConnection() throws DatabaseConnectivityException{
		Connection connection = null;
		
		try {
			DriverManager.registerDriver(new Driver());
			
			String url = System.getenv("db_url");
			String username = System.getenv("db_username");
			String password = System.getenv("db_password");
			
			connection = DriverManager.getConnection(url, username, password);
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Thaovy95*");
		} catch (SQLException e){
			throw new DatabaseConnectivityException("An issue occurred when trying to connect to the database");
		} 


		return connection;
	}
	
}
