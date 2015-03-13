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
import com.ntua.db.jpa.Contract;

/**
 * The Class ContractBean.
 */
@ApplicationScoped
@Named("contractBean")
public class ContractBean {

	/** The contract. */
	private Contract contract;
	
	/** The search contract. */
	private Contract searchContract;
	
	/** The update contract. */
	private Contract updateContract;
	
	/** The order by. */
	private String orderBy;
	
	/** The update contract no. */
	private String updateContractNo;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Contract> results;
	
	/** The search results. */
	private List<Contract> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		contract = new Contract();
		updateContract = new Contract();
		updateContractNo = null;
		orderBy = "ContractsNo";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
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
			String queryString = contract.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table Contracts: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param contract the contract
	 */
	public void delete(Contract contract){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM Contracts WHERE ContractsNo = " + contract.getContractsNo() ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(contract);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table Contracts: " +e.getMessage(), null));
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
			for (Contract contract : searchResults) {
				String queryString = "DELETE FROM Contracts WHERE ContractsNo = " + contract.getContractsNo();
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table Contracts: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param contract the contract
	 */
	private void removeFromList(Contract contract){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getContractsNo().equals(contract.getContractsNo())){
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
			String queryString = searchContract.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Contract>();
			while (resultSet.next()) {
				searchResults.add(populateContracts(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Contracts: " +e.getMessage(), null));
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
		searchContract = new Contract();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateContract = new Contract();
		updateContractNo = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		contract = new Contract();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param contract the contract
	 */
	public void prepareUpdate(Contract contract){
		updateContractNo = contract.getContractsNo();
		updateContract = new Contract(contract);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateContract.updateQuery(updateContractNo);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Contract No: "+updateContractNo+" exists", null));
			} else {
				selectAll();
				clearSearch();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("The selected entry has been updated successfully"));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table Contracts: "+ e.getMessage(), null));
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
			String queryString = "Select * from Contracts";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Contract>();
			while (resultSet.next()) {
				results.add(populateContracts(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Contracts: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate contracts.
	 *
	 * @param resultSet the result set
	 * @return the contract
	 * @throws SQLException the SQL exception
	 */
	private Contract populateContracts(ResultSet resultSet) throws SQLException{
		Contract contract = new Contract();
		contract.setClientRegNo(resultSet.getString("ClientRegistrationNo"));
		contract.setPropertyRegNo(resultSet.getString("PropertyRegistrationNo"));
		contract.setContractsNo(resultSet.getString("ContractsNo"));
		contract.setRentStart(resultSet.getDate("RentStart"));
		contract.setRentFinish(resultSet.getDate("RentFinish"));
		contract.setRent(resultSet.getBigDecimal("Rent"));
		contract.setPaymentType(resultSet.getString("PaymentType"));
		return contract;
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
	 * Gets the contract.
	 *
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}


	/**
	 * Sets the contract.
	 *
	 * @param contract the new contract
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}


	/**
	 * Gets the search contract.
	 *
	 * @return the search contract
	 */
	public Contract getSearchContract() {
		return searchContract;
	}


	/**
	 * Sets the search contract.
	 *
	 * @param searchContract the new search contract
	 */
	public void setSearchContract(Contract searchContract) {
		this.searchContract = searchContract;
	}


	/**
	 * Gets the update contract.
	 *
	 * @return the update contract
	 */
	public Contract getUpdateContract() {
		return updateContract;
	}


	/**
	 * Sets the update contract.
	 *
	 * @param updateContract the new update contract
	 */
	public void setUpdateContract(Contract updateContract) {
		this.updateContract = updateContract;
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
	 * Gets the update contract no.
	 *
	 * @return the update contract no
	 */
	public String getUpdateContractNo() {
		return updateContractNo;
	}


	/**
	 * Sets the update contract no.
	 *
	 * @param updateContractNo the new update contract no
	 */
	public void setUpdateContractNo(String updateContractNo) {
		this.updateContractNo = updateContractNo;
	}


	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Contract> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Contract> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Contract> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Contract> searchResults) {
		this.searchResults = searchResults;
	}

}
