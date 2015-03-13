package com.ntua.db.jpa;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Visit.
 */
public class Visit {

	/** The date. */
	private Date date;
	
	/** The date op. */
	private String dateOp;
	
	/** The client reg no. */
	private String clientRegNo;
	
	/** The client reg no op. */
	private String clientRegNoOp;
	
	/** The property reg no. */
	private String propertyRegNo;
	
	/** The property reg no op. */
	private String propertyRegNoOp;
	
	/**
	 * Instantiates a new visit.
	 */
	public Visit(){
		
	}
	
	/**
	 * Instantiates a new visit.
	 *
	 * @param visit the visit
	 */
	public Visit(Visit visit){
		date = visit.getDate();
		clientRegNo = visit.getClientRegNo();
		propertyRegNo = visit.getPropertyRegNo();
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the date op.
	 *
	 * @return the date op
	 */
	public String getDateOp() {
		return dateOp;
	}

	/**
	 * Sets the date op.
	 *
	 * @param dateOp the new date op
	 */
	public void setDateOp(String dateOp) {
		this.dateOp = dateOp;
	}

	/**
	 * Gets the client reg no.
	 *
	 * @return the client reg no
	 */
	public String getClientRegNo() {
		return clientRegNo;
	}

	/**
	 * Sets the client reg no.
	 *
	 * @param clientRegNo the new client reg no
	 */
	public void setClientRegNo(String clientRegNo) {
		this.clientRegNo = clientRegNo;
	}

	/**
	 * Gets the client reg no op.
	 *
	 * @return the client reg no op
	 */
	public String getClientRegNoOp() {
		return clientRegNoOp;
	}

	/**
	 * Sets the client reg no op.
	 *
	 * @param clientRegNoOp the new client reg no op
	 */
	public void setClientRegNoOp(String clientRegNoOp) {
		this.clientRegNoOp = clientRegNoOp;
	}

	/**
	 * Gets the property reg no.
	 *
	 * @return the property reg no
	 */
	public String getPropertyRegNo() {
		return propertyRegNo;
	}

	/**
	 * Sets the property reg no.
	 *
	 * @param propertyRegNo the new property reg no
	 */
	public void setPropertyRegNo(String propertyRegNo) {
		this.propertyRegNo = propertyRegNo;
	}

	/**
	 * Gets the property reg no op.
	 *
	 * @return the property reg no op
	 */
	public String getPropertyRegNoOp() {
		return propertyRegNoOp;
	}

	/**
	 * Sets the property reg no op.
	 *
	 * @param propertyRegNoOp the new property reg no op
	 */
	public void setPropertyRegNoOp(String propertyRegNoOp) {
		this.propertyRegNoOp = propertyRegNoOp;
	}
	
	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO Visits VALUES(";
		query = query + (date !=null ? "'" + Utils.getSqlDate(date) + "'," : "null,");
		query = query + (StringUtils.hasText(clientRegNo) ? "'" + clientRegNo + "'," : "null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? "'" + propertyRegNo + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Parent update query.
	 *
	 * @param updateclRegNO the updatecl reg no
	 * @param updatePropRegNo the update prop reg no
	 * @param updateDate the update date
	 * @return the string
	 */
	public String updateQuery(String updateClRegNo, String updatePropRegNo, Date updateDate){
		String query = "UPDATE Visits SET ";
		query = query + (date !=null ? "Date=" + "'" +  Utils.getSqlDate(date) + "'," : "Date=null,");
		query = query + (StringUtils.hasText(clientRegNo) ? "ClientRegistrationNo='" + clientRegNo + "'," : "ClientRegistrationNo=null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? "PropertyRegistrationNo='" + propertyRegNo + "'," : "PropertyRegistrationNo=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE ClientRegistrationNo='"+updateClRegNo+"' and PropertyRegistrationNo='" + updatePropRegNo 
				+"' and Date='" + Utils.getSqlDate(updateDate) + "'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Visits WHERE";
		query = query + (StringUtils.hasText(clientRegNo) ? " ClientRegistrationNo"+ clientRegNoOp+"" + clientRegNo + " and" : "");
		query = query + (StringUtils.hasText(propertyRegNo) ? " PropertyRegistrationNo"+ propertyRegNoOp+"" + propertyRegNo + " and" : "");
		query = query + (date != null ? " Date"+ dateOp+"'" + Utils.getSqlDate(date) + "' and" : "");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-6);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
}
