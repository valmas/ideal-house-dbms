package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Owners.
 */
public class Owners {

	/** The afm. */
	private String afm;
	
	/** The afm op. */
	private String afmOp;
	
	/** The street name. */
	private String streetName;
	
	/** The street name op. */
	private String streetNameOp;
	
	/** The street no. */
	private String streetNo;
	
	/** The street no op. */
	private String streetNoOp;
	
	/** The postal code. */
	private String postalCode;
	
	/** The postal code op. */
	private String postalCodeOp;
	
	/**
	 * Instantiates a new owners.
	 */
	public Owners() {
		
	}
	
	/**
	 * Instantiates a new owners.
	 *
	 * @param owner the owner
	 */
	public Owners(Owners owner) {
		super();
		this.afm = owner.getAfm();
		this.streetName = owner.getStreetName();
		this.streetNo = owner.getStreetNo();
		this.postalCode = owner.getPostalCode();
	}

	/**
	 * Gets the afm.
	 *
	 * @return the afm
	 */
	public String getAfm() {
		return afm;
	}

	/**
	 * Sets the afm.
	 *
	 * @param afm the new afm
	 */
	public void setAfm(String afm) {
		this.afm = afm;
	}

	/**
	 * Gets the afm op.
	 *
	 * @return the afm op
	 */
	public String getAfmOp() {
		return afmOp;
	}

	/**
	 * Sets the afm op.
	 *
	 * @param afmOp the new afm op
	 */
	public void setAfmOp(String afmOp) {
		this.afmOp = afmOp;
	}

	/**
	 * Gets the street name.
	 *
	 * @return the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets the street name.
	 *
	 * @param streetName the new street name
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Gets the street name op.
	 *
	 * @return the street name op
	 */
	public String getStreetNameOp() {
		return streetNameOp;
	}

	/**
	 * Sets the street name op.
	 *
	 * @param streetNameOp the new street name op
	 */
	public void setStreetNameOp(String streetNameOp) {
		this.streetNameOp = streetNameOp;
	}

	/**
	 * Gets the street no.
	 *
	 * @return the street no
	 */
	public String getStreetNo() {
		return streetNo;
	}

	/**
	 * Sets the street no.
	 *
	 * @param streetNo the new street no
	 */
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	/**
	 * Gets the street no op.
	 *
	 * @return the street no op
	 */
	public String getStreetNoOp() {
		return streetNoOp;
	}

	/**
	 * Sets the street no op.
	 *
	 * @param streetNoOp the new street no op
	 */
	public void setStreetNoOp(String streetNoOp) {
		this.streetNoOp = streetNoOp;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets the postal code op.
	 *
	 * @return the postal code op
	 */
	public String getPostalCodeOp() {
		return postalCodeOp;
	}

	/**
	 * Sets the postal code op.
	 *
	 * @param postalCodeOp the new postal code op
	 */
	public void setPostalCodeOp(String postalCodeOp) {
		this.postalCodeOp = postalCodeOp;
	}
	
	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String parentInsertQuery(){
		String query = "INSERT INTO Owners VALUES(";
		query = query + (StringUtils.hasText(afm) ? "'" + afm + "'," : "null,");
		query = query + (StringUtils.hasText(streetName) ? "'" + streetName + "'," : "null,");
		query = query + (StringUtils.hasText(streetNo) ? "'" + streetNo + "'," : "null,");
		query = query + (StringUtils.hasText(postalCode) ? "'" + postalCode + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateAfm the update afm
	 * @return the string
	 */
	public String parentUpdateQuery(String updateAfm){
		String query = "UPDATE Owners SET ";
		query = query + (StringUtils.hasText(afm) ? "afm='" + afm + "'," : "afm=null,");
		query = query + (StringUtils.hasText(streetName) ? "Addr_StreetName='" + streetName + "'," : "Addr_StreetName=null,");
		query = query + (StringUtils.hasText(streetNo) ? "Addr_StreetNo='" + streetNo + "'," : "Addr_StreetNo=null,");
		query = query + (StringUtils.hasText(postalCode) ? "Addr_PostalCode='" + postalCode + "'," : "Addr_PostalCode=null,");
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
	public String parentSearchQuery(){
		String query = Utils.constructSearchQuery(getAfmOp(), getAfm(), " o.AFM");
		query = query + Utils.constructSearchQuery(streetNameOp, streetName, " o.Addr_StreetName");
		query = query + Utils.constructSearchQuery(streetNoOp, streetNo, " o.Addr_StreetNo");
		query = query + Utils.constructSearchQuery(postalCodeOp, postalCode, " o.Addr_PostalCode");
		return query;
	}
	
}
