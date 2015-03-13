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
import com.ntua.db.jpa.Visit;

/**
 * The Class VisitBean.
 */
@ApplicationScoped
@Named("visitBean")
public class VisitBean {

	/** The visit. */
	private Visit visit;
	
	/** The search visit. */
	private Visit searchVisit;
	
	/** The update visit. */
	private Visit updateVisit;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updatePropRegNo;
	
	/** The update reg no. */
	private String updateClRegNo;
	
	/** The update reg no. */
	private Date updateDate;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Visit> results;
	
	/** The search results. */
	private List<Visit> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public void init(){
		visit = new Visit();
		updateVisit = new Visit();
		updatePropRegNo = null;
		updateClRegNo = null;
		updateDate = null;
		orderBy = "Date";
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
			String queryString = visit.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table Visits: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param visit the visit
	 */
	public void delete(Visit visit){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM Visits WHERE ClientRegistrationNo = " + visit.getClientRegNo()
					+" and PropertyRegistrationNo=" + visit.getPropertyRegNo()
					+" and Date='" + Utils.getSqlDate(visit.getDate()) +"'" ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(visit);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table Visits: " +e.getMessage(), null));
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
			for (Visit visit : searchResults) {
				String queryString =  "DELETE FROM Visits WHERE ClientRegistrationNo = " + visit.getClientRegNo()
						+" and PropertyRegistrationNo=" + visit.getPropertyRegNo()
						+" and Date='" + Utils.getSqlDate(visit.getDate()) +"'" ;
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table Visits: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param visit the visit
	 */
	private void removeFromList(Visit visit){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getClientRegNo().equals(visit.getClientRegNo())
					&& searchResults.get(i).getPropertyRegNo().equals(visit.getPropertyRegNo())
					&& searchResults.get(i).getDate().equals(visit.getDate())){
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
			String queryString = searchVisit.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Visit>();
			while (resultSet.next()) {
				searchResults.add(populateVisits(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Visits: " +e.getMessage(), null));
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
		searchVisit = new Visit();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateVisit = new Visit();
		updateClRegNo = null;
		updatePropRegNo = null;
		updateDate = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		visit = new Visit();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param visit the visit
	 */
	public void prepareUpdate(Visit visit){
		updateClRegNo = visit.getClientRegNo();
		updatePropRegNo = visit.getPropertyRegNo();
		updateDate = visit.getDate();
		updateVisit = new Visit(visit);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateVisit.updateQuery(updateClRegNo, updatePropRegNo, updateDate);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Client Registration No: "+updateClRegNo 
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
							"Error while updating data to table Visits: "+ e.getMessage(), null));
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
			String queryString = "Select * from Visits";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Visit>();
			while (resultSet.next()) {
				results.add(populateVisits(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Visits: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate visits.
	 *
	 * @param resultSet the result set
	 * @return the visit
	 * @throws SQLException the SQL exception
	 */
	private Visit populateVisits(ResultSet resultSet) throws SQLException{
		Visit visit = new Visit();
		visit.setClientRegNo(resultSet.getString("ClientRegistrationNo"));
		visit.setPropertyRegNo(resultSet.getString("PropertyRegistrationNo"));
		visit.setDate(resultSet.getDate("Date"));
		return visit;
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
	 * Gets the visit.
	 *
	 * @return the visit
	 */
	public Visit getVisit() {
		return visit;
	}


	/**
	 * Sets the visit.
	 *
	 * @param visit the new visit
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
	}


	/**
	 * Gets the search visit.
	 *
	 * @return the search visit
	 */
	public Visit getSearchVisit() {
		return searchVisit;
	}


	/**
	 * Sets the search visit.
	 *
	 * @param searchVisit the new search visit
	 */
	public void setSearchVisit(Visit searchVisit) {
		this.searchVisit = searchVisit;
	}


	/**
	 * Gets the update visit.
	 *
	 * @return the update visit
	 */
	public Visit getUpdateVisit() {
		return updateVisit;
	}


	/**
	 * Sets the update visit.
	 *
	 * @param updateVisit the new update visit
	 */
	public void setUpdateVisit(Visit updateVisit) {
		this.updateVisit = updateVisit;
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
	 * Gets the update cl reg no.
	 *
	 * @return the update cl reg no
	 */
	public String getUpdateClRegNo() {
		return updateClRegNo;
	}


	/**
	 * Sets the update cl reg no.
	 *
	 * @param updateClRegNo the new update cl reg no
	 */
	public void setUpdateClRegNo(String updateClRegNo) {
		this.updateClRegNo = updateClRegNo;
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
	public List<Visit> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Visit> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Visit> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Visit> searchResults) {
		this.searchResults = searchResults;
	}

}
