package com.core;

import java.sql.*;

public class DB {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/miniboard";
		String user = "root";
		String password = "aA!12345";
		Connection conn = DriverManager.getConnection(url,user,password);
		
		return conn;
	}
}
