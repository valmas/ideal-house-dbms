package com.ntua.db.jpa;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Client.
 */
public class Client {

	/** The client reg no. */
	private String clientRegNo;
	
	/** The client reg no op. */
	private String clientRegNoOp;
	
	/** The first name. */
	private String firstName;
	
	/** The first name op. */
	private String firstNameOp;
	
	/** The last name. */
	private String lastName;
	
	/** The last name op. */
	private String lastNameOp;
	
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
	
	/** The reg date. */
	private Date regDate;
	
	/** The reg date op. */
	private String regDateOp;
	
	/** The max rent. */
	private BigDecimal maxRent;
	
	/** The max rent op. */
	private String maxRentOp;
	
	/** The active. */
	private Boolean active;
	
	/** The registered by. */
	private String registeredBy;
	
	/** The registered by op. */
	private String registeredByOp;
	
	/** The prefered type id. */
	private String preferedTypeId;
	
	/** The prefered type id op. */
	private String preferedTypeIdOp;
	
	/**
	 * Instantiates a new client.
	 */
	public Client(){
		
	}
	
	/**
	 * Instantiates a new client.
	 *
	 * @param client the client
	 */
	public Client(Client client){
		clientRegNo = client.getClientRegNo();
		firstName = client.getFirstName();
		lastName = client.getLastName();
		streetName = client.getStreetName();
		streetNo = client.getStreetNo();
		postalCode = client.getPostalCode();
		regDate = client.getRegDate();
		maxRent = client.getMaxRent();
		active = client.getActive();
		registeredBy = client.getRegisteredBy();
		preferedTypeId = client.getPreferedTypeId();
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
	 * Gets the reg date.
	 *
	 * @return the reg date
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * Sets the reg date.
	 *
	 * @param regDate the new reg date
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * Gets the reg date op.
	 *
	 * @return the reg date op
	 */
	public String getRegDateOp() {
		return regDateOp;
	}

	/**
	 * Sets the reg date op.
	 *
	 * @param regDateOp the new reg date op
	 */
	public void setRegDateOp(String regDateOp) {
		this.regDateOp = regDateOp;
	}

	/**
	 * Gets the max rent.
	 *
	 * @return the max rent
	 */
	public BigDecimal getMaxRent() {
		return maxRent;
	}

	/**
	 * Sets the max rent.
	 *
	 * @param maxRent the new max rent
	 */
	public void setMaxRent(BigDecimal maxRent) {
		this.maxRent = maxRent;
	}

	/**
	 * Gets the max rent op.
	 *
	 * @return the max rent op
	 */
	public String getMaxRentOp() {
		return maxRentOp;
	}

	/**
	 * Sets the max rent op.
	 *
	 * @param maxRentOp the new max rent op
	 */
	public void setMaxRentOp(String maxRentOp) {
		this.maxRentOp = maxRentOp;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Gets the registered by.
	 *
	 * @return the registered by
	 */
	public String getRegisteredBy() {
		return registeredBy;
	}

	/**
	 * Sets the registered by.
	 *
	 * @param registeredBy the new registered by
	 */
	public void setRegisteredBy(String registeredBy) {
		this.registeredBy = registeredBy;
	}

	/**
	 * Gets the registered by op.
	 *
	 * @return the registered by op
	 */
	public String getRegisteredByOp() {
		return registeredByOp;
	}

	/**
	 * Sets the registered by op.
	 *
	 * @param registeredByOp the new registered by op
	 */
	public void setRegisteredByOp(String registeredByOp) {
		this.registeredByOp = registeredByOp;
	}

	/**
	 * Gets the prefered type id.
	 *
	 * @return the prefered type id
	 */
	public String getPreferedTypeId() {
		return preferedTypeId;
	}

	/**
	 * Sets the prefered type id.
	 *
	 * @param preferedTypeId the new prefered type id
	 */
	public void setPreferedTypeId(String preferedTypeId) {
		this.preferedTypeId = preferedTypeId;
	}

	/**
	 * Gets the prefered type id op.
	 *
	 * @return the prefered type id op
	 */
	public String getPreferedTypeIdOp() {
		return preferedTypeIdOp;
	}

	/**
	 * Sets the prefered type id op.
	 *
	 * @param preferedTypeIdOp the new prefered type id op
	 */
	public void setPreferedTypeIdOp(String preferedTypeIdOp) {
		this.preferedTypeIdOp = preferedTypeIdOp;
	}
	
	/**
	 * Insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO Clients VALUES(null,";
		query = query + (StringUtils.hasText(firstName) ? "'" + firstName + "'," : "null,");
		query = query + (StringUtils.hasText(lastName) ? "'" + lastName + "'," : "null,");
		query = query + (StringUtils.hasText(streetName) ? "'" + streetName + "'," : "null,");
		query = query + (StringUtils.hasText(streetNo) ? "'" + streetNo + "'," : "null,");
		query = query + (StringUtils.hasText(postalCode) ? "'" + postalCode + "'," : "null,");
		query = query + (regDate !=null ? "'" + Utils.getSqlDate(regDate) + "'," : "null,");
		query = query + maxRent + ",";
		query = query + 1 + ",";
		query = query + (StringUtils.hasText(registeredBy) ? "'" + registeredBy + "'," : "null,");
		query = query + (StringUtils.hasText(preferedTypeId) ? "'" + preferedTypeId + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateRegNo the update reg no
	 * @return the string
	 */
	public String updateQuery(String updateRegNo){
		String query = "UPDATE Clients SET ";
		query = query + (StringUtils.hasText(clientRegNo) ? "ClientRegistrationNo=" + clientRegNo + "," : "ClientRegistrationNo=null,");
		query = query + (StringUtils.hasText(firstName) ? "FirstName='" + firstName + "'," : "FirstName=null,");
		query = query + (StringUtils.hasText(lastName) ? "LastName='" + lastName + "'," : "LastName=null,");
		query = query + (StringUtils.hasText(streetName) ? "Addr_StreetName='" + streetName + "'," : "Addr_StreetName=null,");
		query = query + (StringUtils.hasText(streetNo) ? "Addr_StreetNo='" + streetNo + "'," : "Addr_StreetNo=null,");
		query = query + (StringUtils.hasText(postalCode) ? "Addr_PostalCode='" + postalCode + "'," : "Addr_PostalCode=null,");
		query = query + (regDate !=null ? "RegistrationDate=" + "'" +  Utils.getSqlDate(regDate) + "'," : "RegistrationDate=null,");
		query = query + "MaxRent=" + maxRent + ",";
		query = query + "Active=" + active + ",";
		query = query + (StringUtils.hasText(registeredBy) ? "RegisteredBy='" + registeredBy + "'," : "RegisteredBy=null,");
		query = query + (StringUtils.hasText(preferedTypeId) ? "PreferedTypeId='" + preferedTypeId + "'" : "PreferedTypeId=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE ClientRegistrationNo='"+updateRegNo+"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Clients p WHERE";
		query = query + (StringUtils.hasText(clientRegNo) ? " p.ClientRegistrationNo"+ clientRegNoOp+"" + clientRegNo + " and" : "");
		query = query + Utils.constructSearchQuery(firstNameOp, firstName, " p.FirstName");
		query = query + Utils.constructSearchQuery(lastNameOp, lastName, " p.LastName");
		query = query + Utils.constructSearchQuery(streetNameOp, streetName, " p.Addr_StreetName");
		query = query + Utils.constructSearchQuery(streetNoOp, streetNo, " p.Addr_StreetNo");
		query = query + Utils.constructSearchQuery(postalCodeOp, postalCode, " p.Addr_PostalCode");
		query = query + (regDate != null ? " p.RegistrationDate"+ regDateOp+"'" + Utils.getSqlDate(regDate) + "' and" : "");
		query = query + (maxRent != null ? " p.MaxRent"+ maxRentOp+"" + maxRent + " and" : "");
		query = query + (active != null ? " p.Active"+ "=" + active + " and" : "");
		query = query + Utils.constructSearchQuery(registeredByOp, registeredBy, " p.RegisteredBy");
		query = query + Utils.constructSearchQuery(preferedTypeIdOp, preferedTypeId, " p.PreferedTypeId");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
