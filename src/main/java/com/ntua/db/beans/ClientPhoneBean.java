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
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.common.Queries;
import com.ntua.db.jdbc.JdbcBaseDao;
import com.ntua.db.jpa.ClientsPhone;

/**
 * The Class ClientPhoneBean.
 */
@ApplicationScoped
@Named("clientPhoneBean")
public class ClientPhoneBean {
	
	/** The cl phone. */
	private ClientsPhone clPhone;
	
	/** The search cl phone. */
	private ClientsPhone searchClPhone;
	
	/** The update cl phone. */
	private ClientsPhone updateClPhone;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updateRegNo;
	
	/** The update phone. */
	private String updatePhone;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<ClientsPhone> results;
	
	/** The search results. */
	private List<ClientsPhone> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public void init(){
		clPhone = new ClientsPhone();
		updateClPhone = new ClientsPhone();
		updateRegNo = null;
		updatePhone = null;
		orderBy = "ClientRegistrationNo";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
	}
	
	
	/**
	 * Insert.
	 */
	public void insert(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = clPhone.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table ClientPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param clPhone the cl phone
	 */
	public void delete(ClientsPhone clPhone){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM ClientPhones WHERE ClientRegistrationNo = '" + clPhone.getRegNo() 
					+"' and phoneNumber='" + clPhone.getPhoneNumber()+ "'";
			statement.executeUpdate(queryString);
			selectAll();
			removeFromList(clPhone);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table ClientPhones: " +e.getMessage(), null));
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
			for (ClientsPhone cl : searchResults) {
				String queryString = "DELETE FROM ClientPhones WHERE ClientRegistrationNo = '" + cl.getRegNo() 
						+"' and phoneNumber='" + cl.getPhoneNumber()+ "'";
				statement.executeUpdate(queryString);
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table ClientPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param clPhone the cl phone
	 */
	private void removeFromList(ClientsPhone clPhone){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getRegNo().equals(clPhone.getRegNo())
					&& searchResults.get(i).getPhoneNumber().equals(clPhone.getPhoneNumber())){
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
			String queryString = searchClPhone.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<ClientsPhone>();
			while (resultSet.next()) {
				searchResults.add(populateClientPhone(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table ClientPhones: " +e.getMessage(), null));
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
		searchClPhone = new ClientsPhone();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateClPhone = new ClientsPhone();
		updateRegNo = null;
		updatePhone = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		clPhone = new ClientsPhone();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param clPhone the cl phone
	 */
	public void prepareUpdate(ClientsPhone clPhone){
		updateRegNo = clPhone.getRegNo();
		updatePhone = clPhone.getPhoneNumber();
		updateClPhone = new ClientsPhone(clPhone);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateClPhone.updateQuery(updateRegNo, updatePhone);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the registration No: "+updateRegNo+
								" and phoneNumber: "+updatePhone+" exists", null));
			} else {
				selectAll();
				clearSearch();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("The selected entry has been updated successfully"));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table ClientPhones: "+ e.getMessage(), null));
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
			String queryString = Queries.SELECT_ALL_CLIENTS_PHONE;
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<ClientsPhone>();
			while (resultSet.next()) {
				results.add(populateClientPhone(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table ClientPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate client phone.
	 *
	 * @param resultSet the result set
	 * @return the clients phone
	 * @throws SQLException the SQL exception
	 */
	private ClientsPhone populateClientPhone(ResultSet resultSet) throws SQLException{
		ClientsPhone clPhone = new ClientsPhone();
		clPhone.setRegNo(resultSet.getString("ClientRegistrationNo"));
		clPhone.setPhoneNumber(resultSet.getString("PhoneNumber"));
		return clPhone;
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
	 * Gets the update phone.
	 *
	 * @return the update phone
	 */
	public String getUpdatePhone() {
		return updatePhone;
	}

	/**
	 * Sets the update phone.
	 *
	 * @param updatePhone the new update phone
	 */
	public void setUpdatePhone(String updatePhone) {
		this.updatePhone = updatePhone;
	}


	/**
	 * Gets the cl phone.
	 *
	 * @return the cl phone
	 */
	public ClientsPhone getClPhone() {
		return clPhone;
	}


	/**
	 * Sets the cl phone.
	 *
	 * @param clPhone the new cl phone
	 */
	public void setClPhone(ClientsPhone clPhone) {
		this.clPhone = clPhone;
	}


	/**
	 * Gets the search cl phone.
	 *
	 * @return the search cl phone
	 */
	public ClientsPhone getSearchClPhone() {
		return searchClPhone;
	}


	/**
	 * Sets the search cl phone.
	 *
	 * @param searchClPhone the new search cl phone
	 */
	public void setSearchClPhone(ClientsPhone searchClPhone) {
		this.searchClPhone = searchClPhone;
	}


	/**
	 * Gets the update cl phone.
	 *
	 * @return the update cl phone
	 */
	public ClientsPhone getUpdateClPhone() {
		return updateClPhone;
	}


	/**
	 * Sets the update cl phone.
	 *
	 * @param updateClPhone the new update cl phone
	 */
	public void setUpdateClPhone(ClientsPhone updateClPhone) {
		this.updateClPhone = updateClPhone;
	}


	/**
	 * Gets the update reg no.
	 *
	 * @return the update reg no
	 */
	public String getUpdateRegNo() {
		return updateRegNo;
	}


	/**
	 * Sets the update reg no.
	 *
	 * @param updateRegNo the new update reg no
	 */
	public void setUpdateRegNo(String updateRegNo) {
		this.updateRegNo = updateRegNo;
	}


	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<ClientsPhone> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<ClientsPhone> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<ClientsPhone> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<ClientsPhone> searchResults) {
		this.searchResults = searchResults;
	}
	
}
