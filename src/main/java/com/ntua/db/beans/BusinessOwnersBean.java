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
import com.ntua.db.jpa.BusinessOwners;

/**
 * The Class BusinessOwnersBean.
 */
@ApplicationScoped
@Named("bOwnersBean")
public class BusinessOwnersBean {
	
	/** The owner. */
	private BusinessOwners owner;
	
	/** The search owner. */
	private BusinessOwners searchOwner;
	
	/** The update owner. */
	private BusinessOwners updateOwner;
	
	/** The order by. */
	private String orderBy;
	
	/** The update afm. */
	private String updateAfm;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The p owners bean. */
	@Inject
	private PrivateOwnersBean pOwnersBean;
	
	/** The p owners bean. */
	@Inject
	private OwnerPhoneBean ownerPhoneBean;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<BusinessOwners> results;
	
	/** The search results. */
	private List<BusinessOwners> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		owner = new BusinessOwners();
		updateOwner = new BusinessOwners();
		updateAfm = null;
		orderBy = "AFM";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
		pOwnersBean.init();
		ownerPhoneBean.init();
		return "/views/owners.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * Insert.
	 */
	public void insert(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = owner.parentInsertQuery();
			statement.executeUpdate(queryString);
			queryString = owner.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table Owners: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param bOwner the b owner
	 */
	public void delete(BusinessOwners bOwner){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM BusinessOwners WHERE afm = '" + bOwner.getAfm() +"'";
			statement.executeUpdate(queryString);
			queryString = "DELETE FROM Owners WHERE afm = '" + bOwner.getAfm() +"'";
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(bOwner);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table Owners: " +e.getMessage(), null));
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
			for (BusinessOwners own : searchResults) {
				String queryString = "DELETE FROM BusinessOwners WHERE afm = '" + own.getAfm() +"'";
				statement.executeUpdate(queryString);
				queryString = "DELETE FROM Owners WHERE afm = '" + own.getAfm() +"'";
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table Owners: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param bOwner the b owner
	 */
	private void removeFromList(BusinessOwners bOwner){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getAfm().equals(bOwner.getAfm())){
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
			String queryString = searchOwner.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<BusinessOwners>();
			while (resultSet.next()) {
				searchResults.add(populateOwner(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Owners: " +e.getMessage(), null));
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
		searchOwner = new BusinessOwners();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateOwner = new BusinessOwners();
		updateAfm = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		owner = new BusinessOwners();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param bOwner the b owner
	 */
	public void prepareUpdate(BusinessOwners bOwner){
		updateAfm = bOwner.getAfm();
		updateOwner = new BusinessOwners(bOwner);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateOwner.parentUpdateQuery(updateAfm);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the afm: "+updateAfm+" exists", null));
			}
			queryString = updateOwner.updateQuery(updateAfm);
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
							"Error while updating data to table Owners: "+ e.getMessage(), null));
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
			String queryString = Queries.SELECT_ALL_BUSINESS_OWNERS;
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<BusinessOwners>();
			while (resultSet.next()) {
				results.add(populateOwner(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Owners: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate owner.
	 *
	 * @param resultSet the result set
	 * @return the business owners
	 * @throws SQLException the SQL exception
	 */
	private BusinessOwners populateOwner(ResultSet resultSet) throws SQLException{
		BusinessOwners bOwner = new BusinessOwners();
		bOwner.setAfm(resultSet.getString("AFM"));
		bOwner.setStreetName(resultSet.getString("Addr_StreetName"));
		bOwner.setStreetNo(resultSet.getString("Addr_StreetNo"));
		bOwner.setPostalCode(resultSet.getString("Addr_PostalCode"));
		bOwner.setBusinessName(resultSet.getString("BusinessName"));
		bOwner.setBusinessType(resultSet.getString("BusinessType"));
		bOwner.setContactFirstName(resultSet.getString("ContactFirstName"));
		bOwner.setContactLastName(resultSet.getString("ContactLastName"));
		return bOwner;
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
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public BusinessOwners getOwner() {
		return owner;
	}


	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(BusinessOwners owner) {
		this.owner = owner;
	}


	/**
	 * Gets the search owner.
	 *
	 * @return the search owner
	 */
	public BusinessOwners getSearchOwner() {
		return searchOwner;
	}


	/**
	 * Sets the search owner.
	 *
	 * @param searchOwner the new search owner
	 */
	public void setSearchOwner(BusinessOwners searchOwner) {
		this.searchOwner = searchOwner;
	}


	/**
	 * Gets the update owner.
	 *
	 * @return the update owner
	 */
	public BusinessOwners getUpdateOwner() {
		return updateOwner;
	}


	/**
	 * Sets the update owner.
	 *
	 * @param updateOwner the new update owner
	 */
	public void setUpdateOwner(BusinessOwners updateOwner) {
		this.updateOwner = updateOwner;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<BusinessOwners> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<BusinessOwners> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<BusinessOwners> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<BusinessOwners> searchResults) {
		this.searchResults = searchResults;
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
