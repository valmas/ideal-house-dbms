package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class OwnersPhone.
 */
public class OwnersPhone {

	/** The afm. */
	private String afm;
	
	/** The afm op. */
	private String afmOp;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The phone number op. */
	private String phoneNumberOp;
	
	public OwnersPhone() {
		
	}

	public OwnersPhone(OwnersPhone ownPhone) {
		super();
		afm = ownPhone.getAfm();
		phoneNumber = ownPhone.getPhoneNumber();
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
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the phone number op.
	 *
	 * @return the phone number op
	 */
	public String getPhoneNumberOp() {
		return phoneNumberOp;
	}

	/**
	 * Sets the phone number op.
	 *
	 * @param phoneNumberOp the new phone number op
	 */
	public void setPhoneNumberOp(String phoneNumberOp) {
		this.phoneNumberOp = phoneNumberOp;
	}
	
	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO OwnerPhones VALUES(";
		query = query + (StringUtils.hasText(afm) ? "'" + afm + "'," : "null,");
		query = query + (StringUtils.hasText(phoneNumber) ? "'" + phoneNumber + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateAfm the update afm
	 * @return the string
	 */
	public String updateQuery(String updateAfm, String updatePhone){
		String query = "UPDATE OwnerPhones SET ";
		query = query + (StringUtils.hasText(afm) ? "afm='" + afm + "'," : "afm=null,");
		query = query + (StringUtils.hasText(phoneNumber) ? "PhoneNumber='" + phoneNumber + "'," : "PhoneNumber=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE afm='"+updateAfm+"' and PhoneNumber='" + updatePhone +"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM OwnerPhones o WHERE";
		query = query + Utils.constructSearchQuery(afmOp, afm, " o.AFM");
		query = query + Utils.constructSearchQuery(phoneNumberOp, phoneNumber, " o.PhoneNumber");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
}
