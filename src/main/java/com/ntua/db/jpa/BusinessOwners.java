package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class BusinessOwners.
 */
public class BusinessOwners extends Owners {

	/** The business name. */
	private String businessName;
	
	/** The business name op. */
	private String businessNameOp;
	
	/** The business type. */
	private String businessType;
	
	/** The business type op. */
	private String businessTypeOp;
	
	/** The contact first name. */
	private String contactFirstName;
	
	/** The contact first name op. */
	private String contactFirstNameOp;
	
	/** The contact last name. */
	private String contactLastName;
	
	/** The contact last name op. */
	private String contactLastNameOp;
	
	/**
	 * Instantiates a new business owners.
	 */
	public BusinessOwners() {
		
	}
	
	/**
	 * Instantiates a new business owners.
	 *
	 * @param owner the owner
	 */
	public BusinessOwners(BusinessOwners owner) {
		super(owner);
		this.businessName = owner.getBusinessName();
		this.businessType = owner.getBusinessType();
		this.contactFirstName = owner.getContactFirstName();
		this.contactLastName = owner.getContactLastName();
	}

	/**
	 * Gets the business name.
	 *
	 * @return the business name
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * Sets the business name.
	 *
	 * @param businessName the new business name
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * Gets the business type.
	 *
	 * @return the business type
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * Sets the business type.
	 *
	 * @param businessType the new business type
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * Gets the contact first name.
	 *
	 * @return the contact first name
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}

	/**
	 * Sets the contact first name.
	 *
	 * @param contactFirstName the new contact first name
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	/**
	 * Gets the contact last name.
	 *
	 * @return the contact last name
	 */
	public String getContactLastName() {
		return contactLastName;
	}

	/**
	 * Sets the contact last name.
	 *
	 * @param contactLastName the new contact last name
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	
	/**
	 * Gets the business name op.
	 *
	 * @return the business name op
	 */
	public String getBusinessNameOp() {
		return businessNameOp;
	}

	/**
	 * Sets the business name op.
	 *
	 * @param businessNameOp the new business name op
	 */
	public void setBusinessNameOp(String businessNameOp) {
		this.businessNameOp = businessNameOp;
	}

	/**
	 * Gets the business type op.
	 *
	 * @return the business type op
	 */
	public String getBusinessTypeOp() {
		return businessTypeOp;
	}

	/**
	 * Sets the business type op.
	 *
	 * @param businessTypeOp the new business type op
	 */
	public void setBusinessTypeOp(String businessTypeOp) {
		this.businessTypeOp = businessTypeOp;
	}

	/**
	 * Gets the contact first name op.
	 *
	 * @return the contact first name op
	 */
	public String getContactFirstNameOp() {
		return contactFirstNameOp;
	}

	/**
	 * Sets the contact first name op.
	 *
	 * @param contactFirstNameOp the new contact first name op
	 */
	public void setContactFirstNameOp(String contactFirstNameOp) {
		this.contactFirstNameOp = contactFirstNameOp;
	}

	/**
	 * Gets the contact last name op.
	 *
	 * @return the contact last name op
	 */
	public String getContactLastNameOp() {
		return contactLastNameOp;
	}

	/**
	 * Sets the contact last name op.
	 *
	 * @param contactLastNameOp the new contact last name op
	 */
	public void setContactLastNameOp(String contactLastNameOp) {
		this.contactLastNameOp = contactLastNameOp;
	}

	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO BusinessOwners VALUES(";
		query = query + (StringUtils.hasText(getAfm()) ? "'" + getAfm() + "'," : "null,");
		query = query + (StringUtils.hasText(businessName) ? "'" + businessName + "'," : "null,");
		query = query + (StringUtils.hasText(businessType) ? "'" + businessType + "'," : "null,");
		query = query + (StringUtils.hasText(contactFirstName) ? "'" + contactFirstName + "'," : "null,");
		query = query + (StringUtils.hasText(contactLastName) ? "'" + contactLastName + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateAfm the update afm
	 * @return the string
	 */
	public String updateQuery(String updateAfm){
		String query = "UPDATE BusinessOwners SET ";
		query = query + (StringUtils.hasText(getAfm()) ? "afm='" + getAfm() + "'," : "afm=null,");
		query = query + (StringUtils.hasText(businessName) ? "BusinessName='"+ businessName+"'," : "BusinessName=null,");
		query = query + (StringUtils.hasText(businessType) ? "BusinessType='" + businessType + "'," : "BusinessType=null,");
		query = query + (StringUtils.hasText(contactFirstName) ? "ContactFirstName='" + contactFirstName + "'," : "ContactFirstName=null,");
		query = query + (StringUtils.hasText(contactLastName) ? "ContactLastName='" + contactLastName + "'," : "ContactLastName=null,");
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
		String query = "SELECT * FROM Owners o, BusinessOwners b where o.afm = b.afm and";
		query = query + parentSearchQuery();
		query = query + Utils.constructSearchQuery(businessNameOp, businessName, " BusinessName");
		query = query + Utils.constructSearchQuery(businessTypeOp, businessType, " BusinessType");
		query = query + Utils.constructSearchQuery(contactFirstNameOp, contactFirstName, " ContactFirstName");
		query = query + Utils.constructSearchQuery(contactLastNameOp, contactLastName, " ContactLastName");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
}
