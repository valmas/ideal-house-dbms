package com.ntua.db.jpa;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Contracts.
 */
public class Contract {

	/** The contracts no. */
	private String contractsNo;
	
	/** The contracts no op. */
	private String contractsNoOp;
	
	/** The rent. */
	private BigDecimal rent;
	
	/** The rent op. */
	private String rentOp;
	
	/** The payment type. */
	private String paymentType;
	
	/** The rent start. */
	private Date rentStart;
	
	/** The rent start op. */
	private String rentStartOp;
	
	/** The rent finish. */
	private Date rentFinish;
	
	/** The rent finish op. */
	private String rentFinishOp;
	
	/** The client reg no. */
	private String clientRegNo;
	
	/** The client reg no op. */
	private String clientRegNoOp;
	
	/** The property reg no. */
	private String propertyRegNo;
	
	/** The property reg no op. */
	private String propertyRegNoOp;
	
	/**
	 * Instantiates a new contracts.
	 */
	public Contract() {
		
	}
	
	/**
	 * Instantiates a new contracts.
	 *
	 * @param contracts the contracts
	 */
	public Contract(Contract contracts) {
		contractsNo = contracts.getContractsNo();
		rent = contracts.getRent();
		paymentType = contracts.getPaymentType();
		rentStart = contracts.getRentStart();
		rentFinish = contracts.getRentFinish();
		clientRegNo = contracts.getClientRegNo();
		propertyRegNo = contracts.getPropertyRegNo();
	}

	/**
	 * Gets the contracts no.
	 *
	 * @return the contracts no
	 */
	public String getContractsNo() {
		return contractsNo;
	}

	/**
	 * Sets the contracts no.
	 *
	 * @param contractsNo the new contracts no
	 */
	public void setContractsNo(String contractsNo) {
		this.contractsNo = contractsNo;
	}

	/**
	 * Gets the contracts no op.
	 *
	 * @return the contracts no op
	 */
	public String getContractsNoOp() {
		return contractsNoOp;
	}

	/**
	 * Sets the contracts no op.
	 *
	 * @param contractsNoOp the new contracts no op
	 */
	public void setContractsNoOp(String contractsNoOp) {
		this.contractsNoOp = contractsNoOp;
	}

	/**
	 * Gets the rent.
	 *
	 * @return the rent
	 */
	public BigDecimal getRent() {
		return rent;
	}

	/**
	 * Sets the rent.
	 *
	 * @param rent the new rent
	 */
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	/**
	 * Gets the rent op.
	 *
	 * @return the rent op
	 */
	public String getRentOp() {
		return rentOp;
	}

	/**
	 * Sets the rent op.
	 *
	 * @param rentOp the new rent op
	 */
	public void setRentOp(String rentOp) {
		this.rentOp = rentOp;
	}

	/**
	 * Gets the payment type.
	 *
	 * @return the payment type
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * Sets the payment type.
	 *
	 * @param paymentType the new payment type
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * Gets the rent start.
	 *
	 * @return the rent start
	 */
	public Date getRentStart() {
		return rentStart;
	}

	/**
	 * Sets the rent start.
	 *
	 * @param rentStart the new rent start
	 */
	public void setRentStart(Date rentStart) {
		this.rentStart = rentStart;
	}

	/**
	 * Gets the rent start op.
	 *
	 * @return the rent start op
	 */
	public String getRentStartOp() {
		return rentStartOp;
	}

	/**
	 * Sets the rent start op.
	 *
	 * @param rentStartOp the new rent start op
	 */
	public void setRentStartOp(String rentStartOp) {
		this.rentStartOp = rentStartOp;
	}

	/**
	 * Gets the rent finish.
	 *
	 * @return the rent finish
	 */
	public Date getRentFinish() {
		return rentFinish;
	}

	/**
	 * Sets the rent finish.
	 *
	 * @param rentFinish the new rent finish
	 */
	public void setRentFinish(Date rentFinish) {
		this.rentFinish = rentFinish;
	}

	/**
	 * Gets the rent finish op.
	 *
	 * @return the rent finish op
	 */
	public String getRentFinishOp() {
		return rentFinishOp;
	}

	/**
	 * Sets the rent finish op.
	 *
	 * @param rentFinishOp the new rent finish op
	 */
	public void setRentFinishOp(String rentFinishOp) {
		this.rentFinishOp = rentFinishOp;
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
		String query = "INSERT INTO Contracts VALUES(";
		query = query + (StringUtils.hasText(contractsNo) ? "'" + contractsNo + "'," : "null,");
		query = query + rent + ",";
		query = query + (StringUtils.hasText(paymentType) ? "'" + paymentType + "'," : "null,");
		query = query + (rentStart !=null ? "'" + Utils.getSqlDate(rentStart) + "'," : "null,");
		query = query + (rentFinish !=null ? "'" + Utils.getSqlDate(rentFinish) + "'," : "null,");
		query = query + (StringUtils.hasText(clientRegNo) ? "'" + clientRegNo + "'," : "null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? "'" + propertyRegNo + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateContractNo the update contract no
	 * @return the string
	 */
	public String updateQuery(String updateContractNo){
		String query = "UPDATE Contracts SET ";
		query = query + (StringUtils.hasText(contractsNo) ? "ContractsNo=" + contractsNo + "," : "ContractsNo=null,");
		query = query + "Rent=" + rent + ",";
		query = query + (StringUtils.hasText(paymentType) ? "PaymentType='" + paymentType + "'," : "PaymentType=null,");
		query = query + (rentStart !=null ? "RentStart=" + "'" +  Utils.getSqlDate(rentStart) + "'," : "RentStart=null,");
		query = query + (rentFinish !=null ? "RentFinish=" + "'" +  Utils.getSqlDate(rentFinish) + "'," : "RentFinish=null,");
		query = query + (StringUtils.hasText(clientRegNo) ? "ClientRegistrationNo=" + clientRegNo + "," : "ClientRegistrationNo=null,");
		query = query + (StringUtils.hasText(propertyRegNo) ? "PropertyRegistrationNo=" + propertyRegNo + "," : "PropertyRegistrationNo=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE ContractsNo="+updateContractNo;
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Contracts WHERE";
		query = query + (StringUtils.hasText(contractsNo) ? " ContractsNo"+ contractsNoOp+"" + contractsNo + " and" : "");
		query = query + (rent != null ? " Rent"+ rentOp+"" + rent + " and" : "");
		query = query + (StringUtils.hasText(paymentType) ? " PaymentType="+ paymentType + " and" : "");
		query = query + (rentStart != null ? " RentStart"+ rentStartOp+"'" + Utils.getSqlDate(rentStart) + "' and" : "");
		query = query + (rentFinish != null ? " RentFinish"+ rentFinishOp+"'" + Utils.getSqlDate(rentFinish) + "' and" : "");
		query = query + (StringUtils.hasText(clientRegNo) ? " ClientRegistrationNo"+ clientRegNoOp+"" + clientRegNo + " and" : "");
		query = query + (StringUtils.hasText(propertyRegNo) ? " PropertyRegistrationNo"+ propertyRegNoOp+"" + propertyRegNo + " and" : "");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-6);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
