package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class OwnersPhone.
 */
public class ClientsPhone {

	/** The reg no. */
	private String regNo;
	
	/** The reg no op. */
	private String regNoOp;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The phone number op. */
	private String phoneNumberOp;
	
	/**
	 * Instantiates a new clients phone.
	 */
	public ClientsPhone() {
		
	}

	/**
	 * Instantiates a new clients phone.
	 *
	 * @param clientPhone the client phone
	 */
	public ClientsPhone(ClientsPhone clientPhone) {
		super();
		regNo = clientPhone.getRegNo();
		phoneNumber = clientPhone.getPhoneNumber();
	}

	
	/**
	 * Gets the reg no.
	 *
	 * @return the reg no
	 */
	public String getRegNo() {
		return regNo;
	}

	/**
	 * Sets the reg no.
	 *
	 * @param regNo the new reg no
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	/**
	 * Gets the reg no op.
	 *
	 * @return the reg no op
	 */
	public String getRegNoOp() {
		return regNoOp;
	}

	/**
	 * Sets the reg no op.
	 *
	 * @param regNoOp the new reg no op
	 */
	public void setRegNoOp(String regNoOp) {
		this.regNoOp = regNoOp;
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
		String query = "INSERT INTO ClientPhones VALUES(";
		query = query + (StringUtils.hasText(phoneNumber) ? "'" + phoneNumber + "'," : "null,");
		query = query + (StringUtils.hasText(regNo) ?  regNo : "null") + ")";
		return query;
	}
	
	public String updateQuery(String updateRegNo, String updatePhone){
		String query = "UPDATE ClientPhones SET ";
		query = query + (StringUtils.hasText(regNo) ? "ClientRegistrationNo=" + regNo + "," : "ClientRegistrationNo=null,");
		query = query + (StringUtils.hasText(phoneNumber) ? "PhoneNumber='" + phoneNumber + "'," : "PhoneNumber=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE ClientRegistrationNo='"+updateRegNo+"' and PhoneNumber='" + updatePhone +"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM ClientPhones WHERE";
		query = query + (StringUtils.hasText(regNo) ? " ClientRegistrationNo"+ regNoOp+"" + regNo + " and" : "");
		query = query + Utils.constructSearchQuery(phoneNumberOp, phoneNumber, " PhoneNumber");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-6);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
}
