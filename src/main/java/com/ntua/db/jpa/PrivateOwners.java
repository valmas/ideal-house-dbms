package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class PrivateOwners.
 */
public class PrivateOwners extends Owners {
	
	/** The first name. */
	private String firstName;
	
	/** The first name op. */
	private String firstNameOp;
	
	/** The last name. */
	private String lastName;
	
	/** The last name op. */
	private String lastNameOp;
	
	/**
	 * Instantiates a new private owners.
	 */
	public PrivateOwners() {
		
	}
	
	/**
	 * Instantiates a new private owners.
	 *
	 * @param owner the owner
	 */
	public PrivateOwners(PrivateOwners owner) {
		super(owner);
		this.firstName = owner.getFirstName();
		this.lastName = owner.getLastName();
	}

	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	/**
	 * Gets the first name op.
	 *
	 * @return the first name op
	 */
	public String getFirstNameOp() {
		return firstNameOp;
	}

	/**
	 * Sets the first name op.
	 *
	 * @param firstNameOp the new first name op
	 */
	public void setFirstNameOp(String firstNameOp) {
		this.firstNameOp = firstNameOp;
	}

	/**
	 * Gets the last name op.
	 *
	 * @return the last name op
	 */
	public String getLastNameOp() {
		return lastNameOp;
	}

	/**
	 * Sets the last name op.
	 *
	 * @param lastNameOp the new last name op
	 */
	public void setLastNameOp(String lastNameOp) {
		this.lastNameOp = lastNameOp;
	}

	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO PrivateOwners VALUES(";
		query = query + (StringUtils.hasText(getAfm()) ? "'" + getAfm() + "'," : "null,");
		query = query + (StringUtils.hasText(firstName) ? "'" + firstName + "'," : "null,");
		query = query + (StringUtils.hasText(lastName) ? "'" + lastName + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateAfm the update afm
	 * @return the string
	 */
	public String updateQuery(String updateAfm){
		String query = "UPDATE PrivateOwners SET ";
		query = query + (StringUtils.hasText(getAfm()) ? "afm='" + getAfm() + "'," : "afm=null,");
		query = query + (StringUtils.hasText(firstName) ? "firstName='" + firstName + "'," : "firstName=null,");
		query = query + (StringUtils.hasText(lastName) ? "lastName='" + lastName + "'," : "lastName=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE afm='"+updateAfm+"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Owners o, PrivateOwners p where o.afm = p.afm and";
		query = query + parentSearchQuery();
		query = query + Utils.constructSearchQuery(firstNameOp, firstName, " p.FirstName");
		query = query + Utils.constructSearchQuery(lastNameOp, lastName, " p.LastName");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
