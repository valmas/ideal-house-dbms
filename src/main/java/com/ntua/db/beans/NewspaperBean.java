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
import com.ntua.db.jpa.Newspaper;

/**
 * The Class NewspaperBean.
 */
@ApplicationScoped
@Named("newspaperBean")
public class NewspaperBean {
	
	/** The Constant TABLE_NAME. */
	private static final String TABLE_NAME="Newspapers";

	/** The declaration. */
	private Newspaper declaration;
	
	/** The search. */
	private Newspaper search;
	
	/** The update. */
	private Newspaper update;
	
	/** The order by. */
	private String orderBy;
	
	/** The update reg no. */
	private String updateNewsId;
	
	/** The jdbc. */
	@Inject
	private JdbcBaseDao jdbc;
	
	/** The connection. */
	private Connection connection;
	
	/** The ad bean. */
	@Inject
	private AdvertismentBean adBean;
	
	/** The results. */
	private List<Newspaper> results;
	
	/** The search results. */
	private List<Newspaper> searchResults;
	
	/**
	 * Inits the.
	 *
	 * @return the string
	 */
	public String init(){
		declaration = new Newspaper();
		update = new Newspaper();
		updateNewsId = null;
		orderBy = "NewspaperID";
		clearSearch();
		selectAll();
		jdbc.closeConnection(connection);
		adBean.init();
		return "/views/ads.xhtml?faces-redirect=true";
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
	 * @param news the news
	 */
	public void delete(Newspaper news){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = "DELETE FROM "+TABLE_NAME+" WHERE NewspaperID='" + news.getNewspaperId() +"'";
			statement.executeUpdate(queryString);		
			selectAll();
			removeFromList(news);
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
			for (Newspaper news : searchResults) {
				String queryString =  "DELETE FROM "+TABLE_NAME+" WHERE NewspaperID='" + news.getNewspaperId() +"'";
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
	 * @param news the news
	 */
	private void removeFromList(Newspaper news){
		for (int i=0; i<searchResults.size(); i++) {
			if(searchResults.get(i).getNewspaperId().equals(news.getNewspaperId())){
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
			searchResults = new ArrayList<Newspaper>();
			while (resultSet.next()) {
				searchResults.add(populateNews(resultSet));
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
		search = new Newspaper();
		searchResults = null;
	}
	
	/**
	 * Clear update.
	 */
	public void clearUpdate(){
		update = new Newspaper();
		updateNewsId = null;
	}
	
	/**
	 * Clear add.
	 */
	public void clearAdd(){
		declaration = new Newspaper();
	}
	
	/**
	 * Prepare update.
	 *
	 * @param news the news
	 */
	public void prepareUpdate(Newspaper news){
		updateNewsId = news.getNewspaperId();
		update = new Newspaper(news);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		connection = jdbc.getJdbcConnection();
		Statement statement = null;
		try{
			statement = connection.createStatement();
			String queryString = update.updateQuery(updateNewsId);
			if(statement.executeUpdate(queryString) == 0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No entries have been updated. Please check if the Newspapar Id: "+updateNewsId+
								" exists", null));
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
			results = new ArrayList<Newspaper>();
			while (resultSet.next()) {
				results.add(populateNews(resultSet));
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
	 * Populate news.
	 *
	 * @param resultSet the result set
	 * @return the newspaper
	 * @throws SQLException the SQL exception
	 */
	private Newspaper populateNews(ResultSet resultSet) throws SQLException{
		Newspaper news = new Newspaper();
		news.setNewspaperId(resultSet.getString("NewspaperID"));
		news.setNewspaperName(resultSet.getString("NewspaperName"));
		return news;
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
	public Newspaper getDeclaration() {
		return declaration;
	}


	/**
	 * Sets the declaration.
	 *
	 * @param declaration the new declaration
	 */
	public void setDeclaration(Newspaper declaration) {
		this.declaration = declaration;
	}


	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public Newspaper getSearch() {
		return search;
	}


	/**
	 * Sets the search.
	 *
	 * @param search the new search
	 */
	public void setSearch(Newspaper search) {
		this.search = search;
	}


	/**
	 * Gets the update.
	 *
	 * @return the update
	 */
	public Newspaper getUpdate() {
		return update;
	}


	/**
	 * Sets the update.
	 *
	 * @param update the new update
	 */
	public void setUpdate(Newspaper update) {
		this.update = update;
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
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<Newspaper> getResults() {
		return results;
	}


	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(List<Newspaper> results) {
		this.results = results;
	}


	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public List<Newspaper> getSearchResults() {
		return searchResults;
	}


	/**
	 * Sets the search results.
	 *
	 * @param searchResults the new search results
	 */
	public void setSearchResults(List<Newspaper> searchResults) {
		this.searchResults = searchResults;
	}

}
