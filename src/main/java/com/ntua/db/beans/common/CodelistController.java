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
	
	public List<String> getDateOperators(){
		List<String> list = new ArrayList<String>();
		list.add("=");
		list.add(">");
		list.add("<");
		list.add("not null");
		list.add("null");
		return list;
	}
	
	public List<CodeListItem> getPaymentType(){
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		list.add(new CodeListItem("cash", "cash", false));
		list.add(new CodeListItem("cheque", "cheque", false));
		list.add(new CodeListItem("visa", "visa", false));
		return list;
	}
	
	public List<CodeListItem> getBoolean(){
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		list.add(new CodeListItem("true", "YES", false));
		list.add(new CodeListItem("false", "NO", false));
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
				list.add(new CodeListItem(value, value, false));
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
	
	public List<CodeListItem> getClientsRegNo(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.CLIENTS_REG_NO);
			while (resultSet.next()) {
				String value = resultSet.getString("ClientRegistrationNo");
				String description = resultSet.getString("LastName");
				list.add(new CodeListItem(value, description, true));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.CLIENTS_REG_NO +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
	
	public List<CodeListItem> getPropertiesRegNo(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.PROPERTY_REG_NO);
			while (resultSet.next()) {
				String value = resultSet.getString("PropertyRegistrationNo");
				list.add(new CodeListItem(value, value, false));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.PROPERTY_REG_NO +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
	
	public List<CodeListItem> getNewspapersIds(){
		Connection connection = jdbc.getJdbcConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		List<CodeListItem> list = new ArrayList<CodeListItem>();
		list.add(empty);
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Queries.NEWS_IDS);
			while (resultSet.next()) {
				String value = resultSet.getString("NewspaperID");
				String description = resultSet.getString("NewspaperName");
				list.add(new CodeListItem(value, description, true));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error while executing: "+ Queries.NEWS_IDS +" " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
		return list;
	}
}
