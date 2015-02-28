package com.ntua.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/MyDB","root", "12345ab6789");
			Statement statement = connection.createStatement();
			String queryString = "Select * from Persons";
			ResultSet resultSet = statement.executeQuery(queryString);
			
			while (resultSet.next()) {
				System.out.println("Person Name:" + resultSet.getString("FirstName") );
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	
	
}
