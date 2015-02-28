package com.ntua.db.jpa;

import java.math.BigDecimal;

public class Property {

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
	
	private BigDecimal size;
	
	private String sizeOp;
	
	private int floor;
	
	private String floorOp;
	
	private BigDecimal rent;
	
	private String rentOp;
	
	private String propertyTypeId;
	
	private String propertyTypeIdOp;
	
	private String ownerAFM;
	
	private String ownerAFMOp;
	
	private String managerAFM;
	
	private String managerAFMOp;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNameOp() {
		return streetNameOp;
	}

	public void setStreetNameOp(String streetNameOp) {
		this.streetNameOp = streetNameOp;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getStreetNoOp() {
		return streetNoOp;
	}

	public void setStreetNoOp(String streetNoOp) {
		this.streetNoOp = streetNoOp;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCodeOp() {
		return postalCodeOp;
	}

	public void setPostalCodeOp(String postalCodeOp) {
		this.postalCodeOp = postalCodeOp;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public String getSizeOp() {
		return sizeOp;
	}

	public void setSizeOp(String sizeOp) {
		this.sizeOp = sizeOp;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getFloorOp() {
		return floorOp;
	}

	public void setFloorOp(String floorOp) {
		this.floorOp = floorOp;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public String getRentOp() {
		return rentOp;
	}

	public void setRentOp(String rentOp) {
		this.rentOp = rentOp;
	}

	public String getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public String getPropertyTypeIdOp() {
		return propertyTypeIdOp;
	}

	public void setPropertyTypeIdOp(String propertyTypeIdOp) {
		this.propertyTypeIdOp = propertyTypeIdOp;
	}

	public String getOwnerAFM() {
		return ownerAFM;
	}

	public void setOwnerAFM(String ownerAFM) {
		this.ownerAFM = ownerAFM;
	}

	public String getOwnerAFMOp() {
		return ownerAFMOp;
	}

	public void setOwnerAFMOp(String ownerAFMOp) {
		this.ownerAFMOp = ownerAFMOp;
	}

	public String getManagerAFM() {
		return managerAFM;
	}

	public void setManagerAFM(String managerAFM) {
		this.managerAFM = managerAFM;
	}

	public String getManagerAFMOp() {
		return managerAFMOp;
	}

	public void setManagerAFMOp(String managerAFMOp) {
		this.managerAFMOp = managerAFMOp;
	}
	
}
