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
import com.ntua.db.jpa.PropertyType;

/**
 * The Class PropertyBean.
 */
@ApplicationScoped
@Named("propTypeBean")
public class PropertyTypeBean {

	/** The prop type. */
	private PropertyType propType;
	
	/** The search prop type. */
	private PropertyType searchPropType;
	
	/** The update prop type. */
	private PropertyType updatePropType;
	
	/** The order by. */
	private String orderBy;
	
	/** The update type id. */
	private String updateTypeId;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The results. */
	private List<PropertyType> results;
	
	/** The search results. */
	private List<PropertyType> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		propType = new PropertyType();
		updatePropType = new PropertyType();
		updateTypeId = null;
		orderBy = "PropertyTypeID";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
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
			String queryString = propType.insertQuery();
			statement.executeUpdate(queryString);		
			selectAll();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been inserted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while inserting data into table PropertyTypes: " +e.getMessage()));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param propType the prop type
	 */
	public void delete(PropertyType propType){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM PropertyTypes WHERE PropertyTypeID = " + propType.getPropertyTypeId() ;
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(propType);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Selected entry has been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while inserting data into table PropertyTypes: " +e.getMessage()));
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
			for (PropertyType prop : searchResults) {
				String queryString = "DELETE FROM PropertyTypes WHERE PropertyTypeID = " + prop.getPropertyTypeId();
				statement.executeUpdate(queryString);		
			}
			selectAll();
			clearSearch();
		    FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("All selected entries have been deleted successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while deleting data from table PropertyTypes: " +e.getMessage()));
		} finally {
			jdbc.freeStatement(statement);
			jdbc.closeConnection(connection);
		}
	}
	
	/**
	 * Removes the from list.
	 *
	 * @param propType the prop type
	 */
	private void removeFromList(PropertyType propType){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getPropertyTypeId().equals(propType.getPropertyTypeId())){
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
			String queryString = searchPropType.searchQuery();
			resultSet = statement.executeQuery(queryString);
			searchResults = new ArrayList<PropertyType>();
			while (resultSet.next()) {
				searchResults.add(populatePropTypes(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while retrieving data from table PropertyTypes: " +e.getMessage()));
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
		searchPropType = new PropertyType();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		updatePropType = new PropertyType();
		updateTypeId = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		propType = new PropertyType();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param propType the prop type
	 */
	public void prepareUpdate(PropertyType propType){
		updateTypeId = propType.getPropertyTypeId();
		updatePropType = new PropertyType(propType);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = updatePropType.updateQuery(updateTypeId);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Property Registration No: "+updateTypeId+" exists", null));
			}
			selectAll();
			clearSearch();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("The selected entry has been updated successfully"));
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error while updating data to table PropertyTypes: "+ e.getMessage(), null));
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
			String queryString = "Select * from PropertyTypes";
			if(orderBy != null)
				queryString += " order by "+orderBy;
			resultSet = statement.executeQuery(queryString);
			results = new ArrayList<PropertyType>();
			while (resultSet.next()) {
				results.add(populatePropTypes(resultSet));
			}
			
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Error while retrieving data from table PropertyTypes: " +e.getMessage()));
		} finally {
			jdbc.freeResultSet(resultSet);
			jdbc.freeStatement(statement);
		}
	}
	
	/**
	 * Populate prop types.
	 *
	 * @param resultSet the result set
	 * @return the property type
	 * @throws SQLException the SQL exception
	 */
	private PropertyType populatePropTypes(ResultSet resultSet) throws SQLException{
		PropertyType propTypes = new PropertyType();
		propTypes.setPropertyTypeId(resultSet.getString("PropertyTypeID"));
		String rooms = resultSet.getString("Rooms");
		propTypes.setRooms(rooms == null ? null : Integer.parseInt(rooms));
		propTypes.setDescription(resultSet.getString("Description"));
		return propTypes;
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
	public List<PropertyType> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<PropertyType> results) {
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
	public List<PropertyType> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<PropertyType> searchResults) {
		this.searchResults = searchResults;
	}


	/**
	 * Gets the prop type.
	 *
	 * @return the prop type
	 */
	public PropertyType getPropType() {
		return propType;
	}


	/**
	 * Sets the prop type.
	 *
	 * @param propType the new prop type
	 */
	public void setPropType(PropertyType propType) {
		this.propType = propType;
	}


	/**
	 * Gets the search prop type.
	 *
	 * @return the search prop type
	 */
	public PropertyType getSearchPropType() {
		return searchPropType;
	}


	/**
	 * Sets the search prop type.
	 *
	 * @param searchPropType the new search prop type
	 */
	public void setSearchPropType(PropertyType searchPropType) {
		this.searchPropType = searchPropType;
	}


	/**
	 * Gets the update prop type.
	 *
	 * @return the update prop type
	 */
	public PropertyType getUpdatePropType() {
		return updatePropType;
	}


	/**
	 * Sets the update prop type.
	 *
	 * @param updatePropType the new update prop type
	 */
	public void setUpdatePropType(PropertyType updatePropType) {
		this.updatePropType = updatePropType;
	}


	/**
	 * Gets the update type id.
	 *
	 * @return the update type id
	 */
	public String getUpdateTypeId() {
		return updateTypeId;
	}


	/**
	 * Sets the update type id.
	 *
	 * @param updateTypeId the new update type id
	 */
	public void setUpdateTypeId(String updateTypeId) {
		this.updateTypeId = updateTypeId;
	}

}
