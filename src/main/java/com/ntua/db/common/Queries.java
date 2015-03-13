package com.ntua.db.common;

public class Queries {

	public static final String EMPLOYEE_AFM = "Select AFM,lastName FROM Employees";
	
	public static final String B_OWNER_AFM = "Select AFM,BusinessName FROM BusinessOwners";
	
	public static final String P_OWNER_AFM = "Select AFM,lastName FROM PrivateOwners";
	
	public static final String PROPERTY_TYPE_ID = "Select PropertyTypeID FROM PropertyTypes";
	
	public static final String SELECT_ALL_BUSINESS_OWNERS = "select o.AFM, Addr_StreetName, Addr_StreetNo, Addr_PostalCode"
			+ ", BusinessName, BusinessType, ContactFirstName, ContactLastName from Owners o JOIN BusinessOwners b on o.afm = b.afm";
	
	public static final String SELECT_ALL_PRIVATE_OWNERS = "select o.AFM, Addr_StreetName, Addr_StreetNo, Addr_PostalCode"
			+ ", FirstName, LastName from Owners o JOIN PrivateOwners p on o.afm = p.afm";
	
	public static final String SELECT_ALL_OWNERS_PHONE = "Select * FROM OwnerPhones";
	
	public static final String SELECT_ALL_CLIENTS_PHONE = "Select * FROM ClientPhones";
	
	public static final String CLIENTS_REG_NO = "Select ClientRegistrationNo,lastName FROM Clients";
	
	public static final String PROPERTY_REG_NO = "Select PropertyRegistrationNo FROM Properties";
}
