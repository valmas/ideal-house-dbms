package com.ntua.db.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.ntua.db.common.Utils;
import com.ntua.db.jdbc.JdbcBaseDao;
import com.ntua.db.jpa.Advertisment;

/**
 * The Class AdvertismentBean.
 */
@ApplicationScoped
@Named("advertismentBean")
public class AdvertismentBean {
	
	/** The Constant TABLE_NAME. */
	private static final String TABLE_NAME="Advertisements";

	/** The declaration. */
	private Advertisment declaration;
	
	/** The search. */
	private Advertisment search;
	
	/** The update. */
	private Advertisment update;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updatePropRegNo;
	
	/** The update reg no. */
	private String updateNewsId;
	
	/** The update reg no. */
	private Date updateDate;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Advertisment> results;
	
	/** The search results. */
	private List<Advertisment> searchResults;
	
	/**
	 * Inits the.
	 */
	public void init(){
		declaration = new Advertisment();
		update = new Advertisment();
		updatePropRegNo = null;
		updateNewsId = null;
		updateDate = null;
		orderBy = "DateOfPublish";
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
			String queryString = declaration.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table "+TABLE_NAME+": " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param ad the ad
	 */
	public void delete(Advertisment ad){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM "+TABLE_NAME+" WHERE NewspaperID='" + ad.getNewspaperId() +"'"
					+" and PropertyRegistrationNo=" + ad.getPropertyRegNo()
					+" and DateOfPublish='" + Utils.getSqlDate(ad.getPublishDate()) +"'" ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(ad);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table "+TABLE_NAME+": " +e.getMessage(), null));
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
			for (Advertisment ad : searchResults) {
				String queryString =  "DELETE FROM "+TABLE_NAME+" WHERE NewspaperID='" + ad.getNewspaperId() +"'"
					+" and PropertyRegistrationNo=" + ad.getPropertyRegNo()
					+" and DateOfPublish='" + Utils.getSqlDate(ad.getPublishDate()) +"'" ;
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table "+TABLE_NAME+": " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param ad the ad
	 */
	private void removeFromList(Advertisment ad){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getNewspaperId().equals(ad.getNewspaperId())
					&& searchResults.get(i).getPropertyRegNo().equals(ad.getPropertyRegNo())
					&& searchResults.get(i).getPublishDate().equals(ad.getPublishDate())){
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
			String queryString = search.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Advertisment>();
			while (resultSet.next()) {
				searchResults.add(populateAds(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table "+TABLE_NAME+": " +e.getMessage(), null));
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
		search = new Advertisment();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		update = new Advertisment();
		updateNewsId = null;
		updatePropRegNo = null;
		updateDate = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		declaration = new Advertisment();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param ad the ad
	 */
	public void prepareUpdate(Advertisment ad){
		updateNewsId = ad.getNewspaperId();
		updatePropRegNo = ad.getPropertyRegNo();
		updateDate = ad.getPublishDate();
		update = new Advertisment(ad);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = update.updateQuery(updateNewsId, updatePropRegNo, updateDate);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Newspapar Id: "+updateNewsId 
								+",the Property Registration No: " +updatePropRegNo 
								+" and the date: " + updateDate + " exists", null));
			} else {
				selectAll();
				clearSearch();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("The selected entry has been updated successfully"));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table "+TABLE_NAME+": "+ e.getMessage(), null));
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
			String queryString = "Select * from "+TABLE_NAME;
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Advertisment>();
			while (resultSet.next()) {
				results.add(populateAds(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table "+TABLE_NAME+": " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate ads.
	 *
	 * @param resultSet the result set
	 * @return the advertisment
	 * @throws SQLException the SQL exception
	 */
	private Advertisment populateAds(ResultSet resultSet) throws SQLException{
		Advertisment ad = new Advertisment();
		ad.setNewspaperId(resultSet.getString("NewspaperID"));
		ad.setPropertyRegNo(resultSet.getString("PropertyRegistrationNo"));
		ad.setPublishDate(resultSet.getDate("DateOfPublish"));
		ad.setCost(resultSet.getBigDecimal("Cost"));
		ad.setDuration(resultSet.getInt("Duration"));
		return ad;
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
	 * Gets the declaration.
	 *
	 * @return the declaration
	 */
	public Advertisment getDeclaration() {
		return declaration;
	}


	/**
	 * Sets the declaration.
	 *
	 * @param declaration the new declaration
	 */
	public void setDeclaration(Advertisment declaration) {
		this.declaration = declaration;
	}


	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public Advertisment getSearch() {
		return search;
	}


	/**
	 * Sets the search.
	 *
	 * @param search the new search
	 */
	public void setSearch(Advertisment search) {
		this.search = search;
	}


	/**
	 * Gets the update.
	 *
	 * @return the update
	 */
	public Advertisment getUpdate() {
		return update;
	}


	/**
	 * Sets the update.
	 *
	 * @param update the new update
	 */
	public void setUpdate(Advertisment update) {
		this.update = update;
	}


	/**
	 * Gets the update prop reg no.
	 *
	 * @return the update prop reg no
	 */
	public String getUpdatePropRegNo() {
		return updatePropRegNo;
	}


	/**
	 * Sets the update prop reg no.
	 *
	 * @param updatePropRegNo the new update prop reg no
	 */
	public void setUpdatePropRegNo(String updatePropRegNo) {
		this.updatePropRegNo = updatePropRegNo;
	}


	/**
	 * Gets the update news id.
	 *
	 * @return the update news id
	 */
	public String getUpdateNewsId() {
		return updateNewsId;
	}


	/**
	 * Sets the update news id.
	 *
	 * @param updateNewsId the new update news id
	 */
	public void setUpdateNewsId(String updateNewsId) {
		this.updateNewsId = updateNewsId;
	}


	/**
	 * Gets the update date.
	 *
	 * @return the update date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}


	/**
	 * Sets the update date.
	 *
	 * @param updateDate the new update date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Advertisment> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Advertisment> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Advertisment> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Advertisment> searchResults) {
		this.searchResults = searchResults;
	}

}
