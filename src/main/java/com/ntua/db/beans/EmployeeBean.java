package com.ntua.db.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.Person;
import com.ntua.db.jdbc.JdbcBaseDao;
import com.ntua.db.jpa.Employee;

/**
 * The Class EmployeeBean.
 */
@ApplicationScoped
@Named("employeeBean")
public class EmployeeBean {

	/** The employee. */
	private Employee employee;
	
	/** The search employee. */
	private Employee searchEmployee;
	
	/** The update employee. */
	private Employee updateEmployee;
	
	/** The order by. */
	private String orderBy;
	
	/** The update afm. */
	private String updateAfm;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Employee> results;
	
	/** The search results. */
	private List<Employee> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		employee = new Employee();
		updateEmployee = new Employee();
		updateAfm = null;
		orderBy = "AFM";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
		return "/views/employees.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * Insert.
	 */
	public void insert(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = employee.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while inserting data into table Employees: " +e.getMessage()));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param employee the employee
	 */
	public void delete(Employee employee){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM Employees WHERE afm = '" + employee.getAfm() +"'";
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(employee);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while inserting data into table Employees: " +e.getMessage()));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete all.
	 */
	public void deleteAll(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			for (Employee emp : searchResults) {
				String queryString = "DELETE FROM Employees WHERE afm = '" + emp.getAfm() +"'";
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while deleting data from table Employees: " +e.getMessage()));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param employee the employee
	 */
	private void removeFromList(Employee employee){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getAfm().equals(employee.getAfm())){
				searchResults.remove(i);
				return;
			}
				
		}
	}
	
	/**
	 * Search.
	 */
	public void search(){
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			if (connection == null || connection.isClosed())
				connection = jdbc.getJdbcConnection();
			statement = connection.createStatement();
			String queryString = searchEmployee.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Employee>();
			while (resultSet.next()) {
				searchResults.add(populateEmployee(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while retrieving data from table Employees: " +e.getMessage()));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Clear search.
	 */
	public void clearSearch(){
		searchEmployee = new Employee();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateEmployee = new Employee();
		updateAfm = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		employee = new Employee();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param employee the employee
	 */
	public void prepareUpdate(Employee employee){
		updateAfm = employee.getAfm();
		updateEmployee = new Employee(employee);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateEmployee.updateQuery(updateAfm);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the afm: "+updateAfm+" exists", null));
			}
			selectAll();
			clearSearch();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("The selected entry has been updated successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table Employees: "+ e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Select all.
	 */
	public void selectAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			if (connection == null || connection.isClosed())
				connection = jdbc.getJdbcConnection();
			statement = connection.createStatement();
			String queryString = "Select * from Employees";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Employee>();
			while (resultSet.next()) {
				results.add(populateEmployee(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while retrieving data from table Employees: " +e.getMessage()));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate employee.
	 *
	 * @param resultSet the result set
	 * @return the employee
	 * @throws SQLException the SQL exception
	 */
	private Employee populateEmployee(ResultSet resultSet) throws SQLException{
		Employee employee = new Employee();
		employee.setAfm(resultSet.getString("AFM"));
		employee.setEmployeeNo(resultSet.getString("EmployeeNo"));
		employee.setLastName(resultSet.getString("LastName"));
		employee.setFirstName(resultSet.getString("FirstName"));
		employee.setStreetName(resultSet.getString("Addr_StreetName"));
		employee.setStreetNo(resultSet.getString("Addr_StreetNo"));
		employee.setPostalCode(resultSet.getString("Addr_PostalCode"));
		employee.setSalary(resultSet.getBigDecimal("Salary"));
		employee.setMobilePhoneNumber(resultSet.getString("MobilePhoneNumber"));
		employee.setWorkPhoneNumber(resultSet.getString("WorkPhoneNumber"));
		employee.setSupervisorAFM(resultSet.getString("SupervisorAFM"));
		return employee;
	}
	
	/**
	 * Order results.
	 *
	 * @param event the event
	 */
	public void orderResults(AjaxBehaviorEvent event) {
        selectAll();
        jdbc.closeConnection(connection);
    }

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee the new employee
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Employee> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Employee> results) {
		this.results = results;
	}


	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}


	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	/**
	 * Gets the search employee.
	 *
	 * @return the search employee
	 */
	public Employee getSearchEmployee() {
		return searchEmployee;
	}


	/**
	 * Sets the search employee.
	 *
	 * @param searchEmployee the new search employee
	 */
	public void setSearchEmployee(Employee searchEmployee) {
		this.searchEmployee = searchEmployee;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Employee> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Employee> searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * Gets the update employee.
	 *
	 * @return the update employee
	 */
	public Employee getUpdateEmployee() {
		return updateEmployee;
	}


	/**
	 * Sets the update employee.
	 *
	 * @param updateEmployee the new update employee
	 */
	public void setUpdateEmployee(Employee updateEmployee) {
		this.updateEmployee = updateEmployee;
	}


	/**
	 * Gets the update afm.
	 *
	 * @return the update afm
	 */
	public String getUpdateAfm() {
		return updateAfm;
	}


	/**
	 * Sets the update afm.
	 *
	 * @param updateAfm the new update afm
	 */
	public void setUpdateAfm(String updateAfm) {
		this.updateAfm = updateAfm;
	}
	
}
