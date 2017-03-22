package com.neu.msd.DBLPXMLParser.config;

import java.sql.*;

public class DBConnection {
	// Change the parameters accordingly.
	//private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/dblp?useUnicode=true&characterEncoding=utf-8";
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_dblp";

	private static String user = "root";
	private static String password = "Abhinav007!";

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: "
								+ e.getMessage());
			return null;
		}
	}
}
