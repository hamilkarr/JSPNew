package com.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.FilterConfig;

public class DB {
	private static Connection conn = null;
	
	private static String Driver;
	private static String Url;
	private static String User;
	private static String Pass;

	public static void init(FilterConfig config) {
		init(
			config.	getInitParameter("Driver"),
			config.	getInitParameter("Url"),
			config.	getInitParameter("User"),
			config.	getInitParameter("Pass")
			);
	}
	
	public static void init(String Driver, String Url, String User, String Pass) {
		DB.Driver = Driver;
		DB.Url = Url;
		DB.User = User;
		DB.Pass = Pass;
	}
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException {
		Class.forName(Driver);
		
		conn = DriverManager.getConnection(Url, User, Pass);
		
		return conn;
		
	}






}
