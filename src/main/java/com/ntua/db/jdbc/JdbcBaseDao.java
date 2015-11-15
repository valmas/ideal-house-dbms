package com.ntua.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBaseDao {

	private static final String SQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JUNIT_CONNECTION_URL = "jdbc:mysql://localhost:3306/ideal_house";
	private static final String JUNIT_CONNECTION_USER = "root";
	private static final String JUNIT_CONNECTION_PASSWORD = "1234";
	
	
	public Connection getJdbcConnection() {
		Connection conn;
		try {
			Class.forName(SQL_JDBC_DRIVER);
			conn = DriverManager.getConnection(JUNIT_CONNECTION_URL,
					JUNIT_CONNECTION_USER, JUNIT_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}
	
	public void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void freeResultSet(ResultSet resultSet){		
		try {
			if (resultSet!=null)
				resultSet.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeStatement(Statement stm){
		try{
			if (stm!=null)
				stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
