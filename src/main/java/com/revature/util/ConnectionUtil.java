package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;



public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() {
		
		String url = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = "78ed5296e45e45ffb8842d45141a7e48";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException u) {
			logger.warn("Unable to obtain connection to database", u);
		}
		
		return conn;
	}

}
