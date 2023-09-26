package com.plantnursery.dao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;

public class RetrieveConnection {
	public Connection getConnection() {
		 Connection conn = null;
		 try {
			 Class.forName("oracle.jdbc.OracleDriver"); // Registration
		        System.out.println("Inside try after class.forname");
		        conn = DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl", "scott", "tiger"); // Connection

		        if (conn != null) {
		            System.out.println("Connected to the MySQL database!");
		        } else {
		            System.out.println("Failed to connect to the database.");
		        }
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
		 return conn;

	}
	}
