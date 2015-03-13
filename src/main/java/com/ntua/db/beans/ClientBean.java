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

import com.ntua.db.jdbc.JdbcBaseDao;
import com.ntua.db.jpa.Client;

/**
 * The Class ClientBean.
 */
@ApplicationScoped
@Named("clientBean")
public class ClientBean {

	/** The client. */
	private Client client;
	
	/** The search client. */
	private Client searchClient;
	
	/** The update client. */
	private Client updateClient;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updateRegNo;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The client phone bean. */
	@Inject
	private ClientPhoneBean clientPhoneBean;
	
	/** The visit bean. */
	@Inject
	private VisitBean visitBean;
	
	/** The contract bean. */
	@Inject
	private ContractBean contractBean;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Client> results;
	
	/** The search results. */
	private List<Client> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		client = new Client();
		updateClient = new Client();
		updateRegNo = null;
		orderBy = "ClientRegistrationNo";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
		clientPhoneBean.init();
		visitBean.init();
		contractBean.init();
		return "/views/clients.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * Insert.
	 */
	public void insert(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = client.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table Clients: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param client the client
	 */
	public void delete(Client client){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM Clients WHERE ClientRegistrationNo = " + client.getClientRegNo() ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(client);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table Clients: " +e.getMessage(), null));
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
			for (Client client : searchResults) {
				String queryString = "DELETE FROM Clients WHERE ClientRegistrationNo = " + client.getClientRegNo();
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table Clients: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param client the client
	 */
	private void removeFromList(Client client){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getClientRegNo().equals(client.getClientRegNo())){
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
			String queryString = searchClient.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Client>();
			while (resultSet.next()) {
				searchResults.add(populateClients(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Clients: " +e.getMessage(), null));
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
		searchClient = new Client();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateClient = new Client();
		updateRegNo = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		client = new Client();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param client the client
	 */
	public void prepareUpdate(Client client){
		updateRegNo = client.getClientRegNo();
		updateClient = new Client(client);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateClient.updateQuery(updateRegNo);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Client Registration No: "+updateRegNo+" exists", null));
			} else {
				selectAll();
				clearSearch();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("The selected entry has been updated successfully"));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table Clients: "+ e.getMessage(), null));
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
			String queryString = "Select * from Clients";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Client>();
			while (resultSet.next()) {
				results.add(populateClients(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Clients: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate clients.
	 *
	 * @param resultSet the result set
	 * @return the client
	 * @throws SQLException the SQL exception
	 */
	private Client populateClients(ResultSet resultSet) throws SQLException{
		Client client = new Client();
		client.setClientRegNo(resultSet.getString("ClientRegistrationNo"));
		client.setLastName(resultSet.getString("LastName"));
		client.setFirstName(resultSet.getString("FirstName"));
		client.setStreetName(resultSet.getString("Addr_StreetName"));
		client.setStreetNo(resultSet.getString("Addr_StreetNo"));
		client.setPostalCode(resultSet.getString("Addr_PostalCode"));
		client.setRegDate(resultSet.getDate("RegistrationDate"));
		client.setMaxRent(resultSet.getBigDecimal("MaxRent"));
		client.setActive(resultSet.getBoolean("Active"));
		client.setRegisteredBy(resultSet.getString("RegisteredBy"));
		client.setPreferedTypeId(resultSet.getString("PreferedTypeId"));
		return client;
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
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Client> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Client> results) {
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
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Client> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Client> searchResults) {
		this.searchResults = searchResults;
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
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}


	/**
	 * Sets the client.
	 *
	 * @param client the new client
	 */
	public void setClient(Client client) {
		this.client = client;
	}


	/**
	 * Gets the search client.
	 *
	 * @return the search client
	 */
	public Client getSearchClient() {
		return searchClient;
	}


	/**
	 * Sets the search client.
	 *
	 * @param searchClient the new search client
	 */
	public void setSearchClient(Client searchClient) {
		this.searchClient = searchClient;
	}


	/**
	 * Gets the update client.
	 *
	 * @return the update client
	 */
	public Client getUpdateClient() {
		return updateClient;
	}


	/**
	 * Sets the update client.
	 *
	 * @param updateClient the new update client
	 */
	public void setUpdateClient(Client updateClient) {
		this.updateClient = updateClient;
	}
	
}
