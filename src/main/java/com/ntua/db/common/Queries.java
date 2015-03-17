package com.ntua.db.common;

public class Queries {

	public static final String EMPLOYEE_AFM = "Select AFM,lastName FROM Employees";
	
	public static final String B_OWNER_AFM = "Select AFM,BusinessName FROM BusinessOwners";
	
	public static final String P_OWNER_AFM = "Select AFM,lastName FROM PrivateOwners";
	
	public static final String PROPERTY_TYPE_ID = "Select PropertyTypeID FROM PropertyTypes";
	
	public static final String SELECT_ALL_BUSINESS_OWNERS = "select * from vw_b_owners";
	
	public static final String SELECT_ALL_PRIVATE_OWNERS = "select * from vw_p_owners";
	
	public static final String SELECT_ALL_OWNERS_PHONE = "Select * FROM OwnerPhones";
	
	public static final String SELECT_ALL_CLIENTS_PHONE = "Select * FROM ClientPhones";
	
	public static final String CLIENTS_REG_NO = "Select ClientRegistrationNo,lastName FROM Clients";
	
	public static final String PROPERTY_REG_NO = "Select PropertyRegistrationNo FROM Properties";
	
	public static final String NEWS_IDS = "Select * FROM Newspapers";
	
	public static final String ADS_STATS = "Select * FROM VW_ads_info";
	
	public static final String MOST_WANTED = "select Addr_StreetName ,Addr_StreetNo ,Addr_PostalCode,size,floor,p.rent,"
			+ "description,rooms, Count(*) As contracts_num, IF(min(status) = 0, 'rented' ,'available') as status From Properties p, "
			+ "PropertyTypes pt, (select PropertyRegistrationNo, IF(c.rentStart > DATE(NOW()) or c.rentFinish < DATE(NOW()), 1 ,0)"
			+ " as status from contracts c) As table1 where p.PropertyRegistrationNo = table1.PropertyRegistrationNo "
			+ "and pt.PropertyTypeID = p.PropertyTypeID group by p.PropertyRegistrationNo order by contracts_num DESC;";

	public static final String BEST_CLIENTS = "select firstname, lastname, sum(rent) as totalRent  from Contracts c, "
			+ "Clients cl where cl.clientregistrationNo = c.clientregistrationNo group by c.clientregistrationNo "
			+ "having totalRent >(select avg(tb.totalRent) from (select sum(rent) as totalRent  from Contracts "
			+ "group by clientregistrationNo) as tb) order by totalRent DESC";
	
	public static final String HIGHEST_SALARY = "select max(salary) from employees";
	
	public static final String LOWEST_SALARY = "select min(salary) from employees";
	
	public static final String AVERAGE_SALARY = "select avg(salary) from employees";
}
