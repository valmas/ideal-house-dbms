package com.ntua.db.jpa;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Property.
 */
public class Property {
	
	/** The property reg no. */
	private String propertyRegNo;
	
	/** The property reg no op. */
	private String propertyRegNoOp;

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
	
	/** The size. */
	private BigDecimal size;
	
	/** The size op. */
	private String sizeOp;
	
	/** The floor. */
	private Integer floor;
	
	/** The floor op. */
	private String floorOp;
	
	/** The rent. */
	private BigDecimal rent;
	
	/** The rent op. */
	private String rentOp;
	
	/** The property type id. */
	private String propertyTypeId;
	
	/** The property type id op. */
	private String propertyTypeIdOp;
	
	/** The owner afm. */
	private String ownerAFM;
	
	/** The owner afm op. */
	private String ownerAFMOp;
	
	/** The manager afm. */
	private String managerAFM;
	
	/** The manager afm op. */
	private String managerAFMOp;
	
	
	/**
	 * Instantiates a new property.
	 */
	public Property() {
	}

	/**
	 * Instantiates a new property.
	 *
	 * @param property the property
	 */
	public Property(Property property) {
		super();
		propertyRegNo = property.getPropertyRegNo();
		size = property.getSize();
		floor = property.getFloor();
		rent = property.getRent();
		streetName = property.getStreetName();
		streetNo = property.getStreetNo();
		postalCode = property.getPostalCode();
		propertyTypeId = property.getPropertyTypeId();
		ownerAFM = property.getOwnerAFM();
		managerAFM = property.getManagerAFM();
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
	 * Gets the size.
	 *
	 * @return the size
	 */
	public BigDecimal getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(BigDecimal size) {
		this.size = size;
	}

	/**
	 * Gets the size op.
	 *
	 * @return the size op
	 */
	public String getSizeOp() {
		return sizeOp;
	}

	/**
	 * Sets the size op.
	 *
	 * @param sizeOp the new size op
	 */
	public void setSizeOp(String sizeOp) {
		this.sizeOp = sizeOp;
	}

	/**
	 * Gets the floor.
	 *
	 * @return the floor
	 */
	public Integer getFloor() {
		return floor;
	}

	/**
	 * Sets the floor.
	 *
	 * @param floor the new floor
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	/**
	 * Gets the floor op.
	 *
	 * @return the floor op
	 */
	public String getFloorOp() {
		return floorOp;
	}

	/**
	 * Sets the floor op.
	 *
	 * @param floorOp the new floor op
	 */
	public void setFloorOp(String floorOp) {
		this.floorOp = floorOp;
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
	 * Gets the property type id.
	 *
	 * @return the property type id
	 */
	public String getPropertyTypeId() {
		return propertyTypeId;
	}

	/**
	 * Sets the property type id.
	 *
	 * @param propertyTypeId the new property type id
	 */
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	/**
	 * Gets the property type id op.
	 *
	 * @return the property type id op
	 */
	public String getPropertyTypeIdOp() {
		return propertyTypeIdOp;
	}

	/**
	 * Sets the property type id op.
	 *
	 * @param propertyTypeIdOp the new property type id op
	 */
	public void setPropertyTypeIdOp(String propertyTypeIdOp) {
		this.propertyTypeIdOp = propertyTypeIdOp;
	}

	/**
	 * Gets the owner afm.
	 *
	 * @return the owner afm
	 */
	public String getOwnerAFM() {
		return ownerAFM;
	}

	/**
	 * Sets the owner afm.
	 *
	 * @param ownerAFM the new owner afm
	 */
	public void setOwnerAFM(String ownerAFM) {
		this.ownerAFM = ownerAFM;
	}

	/**
	 * Gets the owner afm op.
	 *
	 * @return the owner afm op
	 */
	public String getOwnerAFMOp() {
		return ownerAFMOp;
	}

	/**
	 * Sets the owner afm op.
	 *
	 * @param ownerAFMOp the new owner afm op
	 */
	public void setOwnerAFMOp(String ownerAFMOp) {
		this.ownerAFMOp = ownerAFMOp;
	}

	/**
	 * Gets the manager afm.
	 *
	 * @return the manager afm
	 */
	public String getManagerAFM() {
		return managerAFM;
	}

	/**
	 * Sets the manager afm.
	 *
	 * @param managerAFM the new manager afm
	 */
	public void setManagerAFM(String managerAFM) {
		this.managerAFM = managerAFM;
	}

	/**
	 * Gets the manager afm op.
	 *
	 * @return the manager afm op
	 */
	public String getManagerAFMOp() {
		return managerAFMOp;
	}

	/**
	 * Sets the manager afm op.
	 *
	 * @param managerAFMOp the new manager afm op
	 */
	public void setManagerAFMOp(String managerAFMOp) {
		this.managerAFMOp = managerAFMOp;
	}
	
	/**
	 * Insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO Properties VALUES(null,";
		query = query + (StringUtils.hasText(streetName) ? "'" + streetName + "'," : "null,");
		query = query + (StringUtils.hasText(streetNo) ? "'" + streetNo + "'," : "null,");
		query = query + (StringUtils.hasText(postalCode) ? "'" + postalCode + "'," : "null,");
		query = query + size + ",";
		query = query + floor + ",";
		query = query + rent + ",";
		query = query + (StringUtils.hasText(propertyTypeId) ? "'" + propertyTypeId + "'," : "null,");
		query = query + (StringUtils.hasText(ownerAFM) ? "'" + ownerAFM + "'," : "null,");
		query = query + (StringUtils.hasText(managerAFM) ? "'" + managerAFM + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateRegNo the update reg no
	 * @return the string
	 */
	public String updateQuery(String updateRegNo){
		String query = "UPDATE Properties SET ";
		query = query + (StringUtils.hasText(propertyRegNo) ? "PropertyRegistrationNo=" + propertyRegNo + "," : "PropertyRegistrationNo=null,");
		query = query + (StringUtils.hasText(streetName) ? "Addr_StreetName='" + streetName + "'," : "Addr_StreetName=null,");
		query = query + (StringUtils.hasText(streetNo) ? "Addr_StreetNo='" + streetNo + "'," : "Addr_StreetNo=null,");
		query = query + (StringUtils.hasText(postalCode) ? "Addr_PostalCode='" + postalCode + "'," : "Addr_PostalCode=null,");
		query = query + "size=" + size + ",";
		query = query + "floor=" + floor + ",";
		query = query + "rent=" + rent + ",";
		query = query + (StringUtils.hasText(propertyTypeId) ? "PropertyTypeId='" + propertyTypeId + "'," : "PropertyTypeId=null,");
		query = query + (StringUtils.hasText(ownerAFM) ? "OwnerAFM='" + ownerAFM + "'," : "OwnerAFM=null,");
		query = query + (StringUtils.hasText(managerAFM) ? "ManagerAFM='" + managerAFM + "'" : "ManagerAFM=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE PropertyRegistrationNo='"+updateRegNo+"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM Properties p WHERE";
		query = query + (StringUtils.hasText(propertyRegNo) ? " p.PropertyRegistrationNo"+ propertyRegNoOp+"" + propertyRegNo + " and" : "");
		query = query + Utils.constructSearchQuery(streetNameOp, streetName, " p.Addr_StreetName");
		query = query + Utils.constructSearchQuery(streetNoOp, streetNo, " p.Addr_StreetNo");
		query = query + Utils.constructSearchQuery(postalCodeOp, postalCode, " p.Addr_PostalCode");
		query = query + (size != null ? " p.Size"+ sizeOp+"" + size + " and" : "");
		query = query + (floor != null ? " p.Floor"+ floorOp+"" + floor + " and" : "");
		query = query + (rent != null ? " p.Rent"+ rentOp+"" + rent + " and" : "");
		query = query + Utils.constructSearchQuery(propertyTypeIdOp, propertyTypeId, " p.PropertyTypeId");
		query = query + Utils.constructSearchQuery(ownerAFMOp, ownerAFM, " p.OwnerAFM");
		query = query + Utils.constructSearchQuery(managerAFMOp, managerAFM, " p.ManagerAFM");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
