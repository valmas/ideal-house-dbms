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
import com.ntua.db.jpa.Property;

/**
 * The Class PropertyBean.
 */
@ApplicationScoped
@Named("propertyBean")
public class PropertyBean {

	/** The property. */
	private Property property;
	
	/** The search property. */
	private Property searchProperty;
	
	/** The update property. */
	private Property updateProperty;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updateRegNo;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	@Inject
	private PropertyTypeBean propTypeBean;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<Property> results;
	
	/** The search results. */
	private List<Property> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		property = new Property();
		updateProperty = new Property();
		updateRegNo = null;
		orderBy = "PropertyRegistrationNo";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
		propTypeBean.init();
		return "/views/properties.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * Insert.
	 */
	public void insert(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = property.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while inserting data into table Properties: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param property the property
	 */
	public void delete(Property property){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM Properties WHERE PropertyRegistrationNo = " + property.getPropertyRegNo() ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(property);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data into table Properties: " +e.getMessage(), null));
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
			for (Property prop : searchResults) {
				String queryString = "DELETE FROM Properties WHERE PropertyRegistrationNo = " + prop.getPropertyRegNo();
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while deleting data from table Properties: " +e.getMessage(), null));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param property the property
	 */
	private void removeFromList(Property property){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getPropertyRegNo().equals(property.getPropertyRegNo())){
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
			String queryString = searchProperty.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<Property>();
			while (resultSet.next()) {
				searchResults.add(populateProperties(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Properties: " +e.getMessage(), null));
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
		searchProperty = new Property();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updateProperty = new Property();
		updateRegNo = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		property = new Property();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param property the property
	 */
	public void prepareUpdate(Property property){
		updateRegNo = property.getPropertyRegNo();
		updateProperty = new Property(property);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updateProperty.updateQuery(updateRegNo);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Property Registration No: "+updateRegNo+" exists", null));
			} else {
				selectAll();
				clearSearch();
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("The selected entry has been updated successfully"));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table Properties: "+ e.getMessage(), null));
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
			String queryString = "Select * from Properties";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<Property>();
			while (resultSet.next()) {
				results.add(populateProperties(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while retrieving data from table Properties: " +e.getMessage(), null));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate properties.
	 *
	 * @param resultSet the result set
	 * @return the property
	 * @throws SQLException the SQL exception
	 */
	private Property populateProperties(ResultSet resultSet) throws SQLException{
		Property property = new Property();
		property.setPropertyRegNo(resultSet.getString("PropertyRegistrationNo"));
		property.setSize(resultSet.getBigDecimal("Size"));
		String floor = resultSet.getString("Floor");
		property.setFloor(floor == null ? null : Integer.parseInt(floor));
		property.setRent(resultSet.getBigDecimal("Rent"));
		property.setStreetName(resultSet.getString("Addr_StreetName"));
		property.setStreetNo(resultSet.getString("Addr_StreetNo"));
		property.setPostalCode(resultSet.getString("Addr_PostalCode"));
		property.setPropertyTypeId(resultSet.getString("PropertyTypeId"));
		property.setOwnerAFM(resultSet.getString("OwnerAFM"));
		property.setManagerAFM(resultSet.getString("ManagerAFM"));
		return property;
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
	public List<Property> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Property> results) {
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
	public List<Property> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Property> searchResults) {
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
	 * Gets the property.
	 *
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}


	/**
	 * Sets the property.
	 *
	 * @param property the new property
	 */
	public void setProperty(Property property) {
		this.property = property;
	}


	/**
	 * Gets the search property.
	 *
	 * @return the search property
	 */
	public Property getSearchProperty() {
		return searchProperty;
	}


	/**
	 * Sets the search property.
	 *
	 * @param searchProperty the new search property
	 */
	public void setSearchProperty(Property searchProperty) {
		this.searchProperty = searchProperty;
	}


	/**
	 * Gets the update property.
	 *
	 * @return the update property
	 */
	public Property getUpdateProperty() {
		return updateProperty;
	}


	/**
	 * Sets the update property.
	 *
	 * @param updateProperty the new update property
	 */
	public void setUpdateProperty(Property updateProperty) {
		this.updateProperty = updateProperty;
	}
	
}
