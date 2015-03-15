package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Newspaper.
 */
public class Newspaper {

	/** The newspaper id. */
	private String newspaperId;
	
	/** The newspaper id op. */
	private String newspaperIdOp;
	
	/** The newspaper name. */
	private String newspaperName;
	
	/** The newspaper name op. */
	private String newspaperNameOp;
	
	/**
	 * Instantiates a new newspaper.
	 */
	public Newspaper(){
		
	}
	
	/**
	 * Instantiates a new newspaper.
	 *
	 * @param client the client
	 */
	public Newspaper(Newspaper client){
		newspaperId = client.getNewspaperId();
		newspaperName = client.getNewspaperName();
	}

	/**
	 * Gets the newspaper id.
	 *
	 * @return the newspaper id
	 */
	public String getNewspaperId() {
		return newspaperId;
	}

	/**
	 * Sets the newspaper id.
	 *
	 * @param newspaperId the new newspaper id
	 */
	public void setNewspaperId(String newspaperId) {
		this.newspaperId = newspaperId;
	}

	/**
	 * Gets the newspaper id op.
	 *
	 * @return the newspaper id op
	 */
	public String getNewspaperIdOp() {
		return newspaperIdOp;
	}

	/**
	 * Sets the newspaper id op.
	 *
	 * @param newspaperIdOp the new newspaper id op
	 */
	public void setNewspaperIdOp(String newspaperIdOp) {
		this.newspaperIdOp = newspaperIdOp;
	}

	/**
	 * Gets the newspaper name.
	 *
	 * @return the newspaper name
	 */
	public String getNewspaperName() {
		return newspaperName;
	}

	/**
	 * Sets the newspaper name.
	 *
	 * @param newspaperName the new newspaper name
	 */
	public void setNewspaperName(String newspaperName) {
		this.newspaperName = newspaperName;
	}

	/**
	 * Gets the newspaper name op.
	 *
	 * @return the newspaper name op
	 */
	public String getNewspaperNameOp() {
		return newspaperNameOp;
	}

	/**
	 * Sets the newspaper name op.
	 *
	 * @param newspaperNameOp the new newspaper name op
	 */
	public void setNewspaperNameOp(String newspaperNameOp) {
		this.newspaperNameOp = newspaperNameOp;
	}

	/**
	 * Insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO Newspapers VALUES(";
		query = query + (StringUtils.hasText(newspaperId) ? "'" + newspaperId + "'," : "null,");
		query = query + (StringUtils.hasText(newspaperName) ? "'" + newspaperName + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateNewsId the update news id
	 * @return the string
	 */
	public String updateQuery(String updateNewsId){
		String query = "UPDATE Newspapers SET ";
		query = query + (StringUtils.hasText(newspaperId) ? "NewspaperID='" + newspaperId + "'," : "NewspaperID=null,");
		query = query + (StringUtils.hasText(newspaperName) ? "NewspaperName='" + newspaperName + "'" : "NewspaperName=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE NewspaperID='"+updateNewsId+"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Newspapers WHERE";
		query = query + Utils.constructSearchQuery(newspaperIdOp, newspaperId, " NewspaperID");
		query = query + Utils.constructSearchQuery(newspaperNameOp, newspaperName, " NewspaperName");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-6);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
