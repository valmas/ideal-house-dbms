package com.ntua.db.beans.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.common.CodeListItem;
import com.ntua.db.common.Queries;
import com.ntua.db.jdbc.JdbcBaseDao;

@ApplicationScoped
@Named("codelistController")
public class CodelistController {
	
	private static final CodeListItem empty = new CodeListItem("", "", false);
	
	@Inject
	private JdbcBaseDao jdbc;

	public List<String> getOperators(){
		List<String> list = new ArrayList<String>();
		list.add("=");
		list.add("<");
		list.add(">");
		return list;
	}
	
	public List<String> getStrOperators(){
		List<String> list = new ArrayList<String>();
		list.add("=");
		list.add("like");
		list.add("not null");
		list.add("null");
		return list;
	}
	
	public List<CodeListItem> getEmployeesAfm(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.EMPLOYEE_AFM);
			while (resultSet.next()) {
				String value = resultSet.getString("AFM");
				String description = resultSet.getString("lastName");
				list.add(new CodeListItem(value, description, true));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.EMPLOYEE_AFM +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
	
	public List<CodeListItem> getOwnersAfm(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.B_OWNER_AFM);
			while (resultSet.next()) {
				String value = resultSet.getString("AFM");
				String description = resultSet.getString("BusinessName");
				list.add(new CodeListItem(value, description, true));
			}
			jdbc.freeResultSet(resultSet);
			resultSet = statement.executeQuery(Queries.P_OWNER_AFM);
			while (resultSet.next()) {
				String value = resultSet.getString("AFM");
				String description = resultSet.getString("LastName");
				list.add(new CodeListItem(value, description, true));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.EMPLOYEE_AFM +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
	
	public List<CodeListItem> getPropTypeIDs(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.PROPERTY_TYPE_ID);
			while (resultSet.next()) {
				String value = resultSet.getString("PropertyTypeID");
				list.add(new CodeListItem(value, "", false));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.EMPLOYEE_AFM +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
}
