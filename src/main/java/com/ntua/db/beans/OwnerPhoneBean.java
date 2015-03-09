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
import com.ntua.db.jpa.OwnersPhone;

/**
 * The Class OwnerPhoneBean.
 */
@ApplicationScoped
@Named("ownerPhoneBean")
public class OwnerPhoneBean {
	
	/** The owner. */
	private OwnersPhone owner;
	
	/** The search owner. */
	private OwnersPhone searchOwner;
	
	/** The update owner. */
	private OwnersPhone updateOwner;
	
	/** The order by. */
	private String orderBy;
	
	/** The update afm. */
	private String updateAfm;
	
	/** The update afm. */
	private String updatePhone;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<OwnersPhone> results;
	
	/** The search results. */
	private List<OwnersPhone> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public void init(){
		owner = new OwnersPhone();
		updateOwner = new OwnersPhone();
		updateAfm = null;
		updatePhone = null;
		orderBy = "AFM";
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
			String queryString = owner.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table OwnerPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param ownPhone the own phone
	 */
	public void delete(OwnersPhone ownPhone){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM OwnerPhones WHERE afm = '" + ownPhone.getAfm() 
					+"' and phoneNumber='" + ownPhone.getPhoneNumber()+ "'";
			statement.executeUpdate(queryString);
			selectAll();
			removeFromList(ownPhone);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table OwnerPhones: " +e.getMessage(), null));
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
			for (OwnersPhone own : searchResults) {
				String queryString = "DELETE FROM OwnerPhones WHERE afm = '" + own.getAfm() 
						+"' and phoneNumber='" + own.getPhoneNumber()+ "'";
				statement.executeUpdate(queryString);
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table OwnerPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param ownPhone the own phone
	 */
	private void removeFromList(OwnersPhone ownPhone){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getAfm().equals(ownPhone.getAfm())){
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
			searchResults = new ArrayList<OwnersPhone>();
			while (resultSet.next()) {
				searchResults.add(populateOwnerPhone(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table OwnerPhones: " +e.getMessage(), null));
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
		searchOwner = new OwnersPhone();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateOwner = new OwnersPhone();
		updateAfm = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		owner = new OwnersPhone();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param ownPhone the own phone
	 */
	public void prepareUpdate(OwnersPhone ownPhone){
		updateAfm = ownPhone.getAfm();
		updatePhone = ownPhone.getPhoneNumber();
		updateOwner = new OwnersPhone(ownPhone);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateOwner.updateQuery(updateAfm, updatePhone);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the afm: "+updateAfm+
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
							"Error while updating data to table OwnerPhones: "+ e.getMessage(), null));
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
			String queryString = Queries.SELECT_ALL_OWNERS_PHONE;
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<OwnersPhone>();
			while (resultSet.next()) {
				results.add(populateOwnerPhone(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table OwnerPhones: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate owner phone.
	 *
	 * @param resultSet the result set
	 * @return the owners phone
	 * @throws SQLException the SQL exception
	 */
	private OwnersPhone populateOwnerPhone(ResultSet resultSet) throws SQLException{
		OwnersPhone ownPhone = new OwnersPhone();
		ownPhone.setAfm(resultSet.getString("AFM"));
		ownPhone.setPhoneNumber(resultSet.getString("PhoneNumber"));
		return ownPhone;
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
	public OwnersPhone getOwner() {
		return owner;
	}


	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(OwnersPhone owner) {
		this.owner = owner;
	}


	/**
	 * Gets the search owner.
	 *
	 * @return the search owner
	 */
	public OwnersPhone getSearchOwner() {
		return searchOwner;
	}


	/**
	 * Sets the search owner.
	 *
	 * @param searchOwner the new search owner
	 */
	public void setSearchOwner(OwnersPhone searchOwner) {
		this.searchOwner = searchOwner;
	}


	/**
	 * Gets the update owner.
	 *
	 * @return the update owner
	 */
	public OwnersPhone getUpdateOwner() {
		return updateOwner;
	}


	/**
	 * Sets the update owner.
	 *
	 * @param updateOwner the new update owner
	 */
	public void setUpdateOwner(OwnersPhone updateOwner) {
		this.updateOwner = updateOwner;
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<OwnersPhone> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<OwnersPhone> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<OwnersPhone> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<OwnersPhone> searchResults) {
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

}
