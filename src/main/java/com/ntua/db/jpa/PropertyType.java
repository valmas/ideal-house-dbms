package com.ntua.db.jpa;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class PropertyType.
 */
public class PropertyType {

	/** The property type id. */
	private String propertyTypeId;
	
	/** The property type id op. */
	private String propertyTypeIdOp;
	
	/** The rooms. */
	private Integer rooms;
	
	/** The rooms op. */
	private String roomsOp;
	
	/** The description. */
	private String description;
	
	/** The description op. */
	private String descriptionOp;
	
	public PropertyType() {
	}
	
	public PropertyType(PropertyType pt) {
		propertyTypeId = pt.getPropertyTypeId();
		rooms = pt.getRooms();
		description = pt.getDescription();
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
	 * Gets the rooms.
	 *
	 * @return the rooms
	 */
	public Integer getRooms() {
		return rooms;
	}

	/**
	 * Sets the rooms.
	 *
	 * @param rooms the new rooms
	 */
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	/**
	 * Gets the rooms op.
	 *
	 * @return the rooms op
	 */
	public String getRoomsOp() {
		return roomsOp;
	}

	/**
	 * Sets the rooms op.
	 *
	 * @param roomsOp the new rooms op
	 */
	public void setRoomsOp(String roomsOp) {
		this.roomsOp = roomsOp;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the description op.
	 *
	 * @return the description op
	 */
	public String getDescriptionOp() {
		return descriptionOp;
	}

	/**
	 * Sets the description op.
	 *
	 * @param descriptionOp the new description op
	 */
	public void setDescriptionOp(String descriptionOp) {
		this.descriptionOp = descriptionOp;
	}
	
	public String insertQuery(){
		String query = "INSERT INTO PropertyTypes VALUES(";
		query = query + (StringUtils.hasText(propertyTypeId) ? "'" + propertyTypeId + "'," : "null,");
		query = query + rooms + ",";
		query = query + (StringUtils.hasText(description) ? "'" + description + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateRegNo the update reg no
	 * @return the string
	 */
	public String updateQuery(String updateRegNo){
		String query = "UPDATE PropertyTypes SET ";
		query = query + (StringUtils.hasText(propertyTypeId) ? "PropertyTypeID='" + propertyTypeId + "'," : "PropertyTypeID=null,");
		query = query + "Rooms=" + rooms + ",";
		query = query + (StringUtils.hasText(description) ? "Description='" + description + "'" : "Description=null,");
		if(query.endsWith(","))
			query = query.substring(0, query.length()-1);
		query += " WHERE PropertyTypeID='"+updateRegNo+"'";
		return query;
	}
	
	/**
	 * Search query.
	 *
	 * @return the string
	 */
	public String searchQuery(){
		String query = "SELECT * FROM PropertyTypes p WHERE";
		query = query + Utils.constructSearchQuery(propertyTypeIdOp, propertyTypeId, " p.PropertyTypeID");
		query = query + (rooms != null ? " p.Rooms"+ roomsOp+"" + rooms + " and" : "");
		query = query + Utils.constructSearchQuery(descriptionOp, description, " p.Description");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
	
}
