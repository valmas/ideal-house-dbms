package com.ntua.db.jpa;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.ntua.db.common.Utils;

/**
 * The Class Employee.
 */
public class Employee {

	/** The afm. */
	private String afm;
	
	/** The afm op. */
	private String afmOp;
	
	/** The employee no. */
	private String employeeNo;
	
	/** The emp no opeator. */
	private String employeeNoOp;
	
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
	
	/** The Salary. */
	private BigDecimal salary;
	
	/** The salary opeator. */
	private String salaryOp;
	
	/** The work phone number. */
	private String workPhoneNumber;
	
	/** The work phone number op. */
	private String workPhoneNumberOp;
	
	/** The mobile phone number. */
	private String mobilePhoneNumber;
	
	/** The mobile phone number op. */
	private String mobilePhoneNumberOp;
	
	/** The supervisor afm. */
	private String supervisorAFM;
	
	/** The supervisor afm op. */
	private String supervisorAFMOp;
	
	/**
	 * Instantiates a new employee.
	 */
	public Employee() {
		
	}
	
	/**
	 * Instantiates a new employee.
	 *
	 * @param employee the employee
	 */
	public Employee(Employee employee) {
		super();
		this.afm = employee.getAfm();
		this.employeeNo = employee.getEmployeeNo();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.streetName = employee.getStreetName();
		this.streetNo = employee.getStreetNo();
		this.postalCode = employee.getPostalCode();
		this.salary = employee.getSalary();
		this.workPhoneNumber = employee.getWorkPhoneNumber();
		this.mobilePhoneNumber = employee.getMobilePhoneNumber();
		this.supervisorAFM = employee.getSupervisorAFM();
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
	 * Gets the employee no.
	 *
	 * @return the employee no
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * Sets the employee no.
	 *
	 * @param employeeNo the new employee no
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * Sets the salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/**
	 * Gets the work phone number.
	 *
	 * @return the work phone number
	 */
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	/**
	 * Sets the work phone number.
	 *
	 * @param workPhoneNumber the new work phone number
	 */
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	/**
	 * Gets the mobile phone number.
	 *
	 * @return the mobile phone number
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * Sets the mobile phone number.
	 *
	 * @param mobilePhoneNumber the new mobile phone number
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * Gets the supervisor afm.
	 *
	 * @return the supervisor afm
	 */
	public String getSupervisorAFM() {
		return supervisorAFM;
	}

	/**
	 * Sets the supervisor afm.
	 *
	 * @param supervisorAFM the new supervisor afm
	 */
	public void setSupervisorAFM(String supervisorAFM) {
		this.supervisorAFM = supervisorAFM;
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
	 * Gets the employee no op.
	 *
	 * @return the employee no op
	 */
	public String getEmployeeNoOp() {
		return employeeNoOp;
	}

	/**
	 * Sets the employee no op.
	 *
	 * @param employeeNoOp the new employee no op
	 */
	public void setEmployeeNoOp(String employeeNoOp) {
		this.employeeNoOp = employeeNoOp;
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
	 * Gets the salary op.
	 *
	 * @return the salary op
	 */
	public String getSalaryOp() {
		return salaryOp;
	}

	/**
	 * Sets the salary op.
	 *
	 * @param salaryOp the new salary op
	 */
	public void setSalaryOp(String salaryOp) {
		this.salaryOp = salaryOp;
	}

	/**
	 * Gets the work phone number op.
	 *
	 * @return the work phone number op
	 */
	public String getWorkPhoneNumberOp() {
		return workPhoneNumberOp;
	}

	/**
	 * Sets the work phone number op.
	 *
	 * @param workPhoneNumberOp the new work phone number op
	 */
	public void setWorkPhoneNumberOp(String workPhoneNumberOp) {
		this.workPhoneNumberOp = workPhoneNumberOp;
	}

	/**
	 * Gets the mobile phone number op.
	 *
	 * @return the mobile phone number op
	 */
	public String getMobilePhoneNumberOp() {
		return mobilePhoneNumberOp;
	}

	/**
	 * Sets the mobile phone number op.
	 *
	 * @param mobilePhoneNumberOp the new mobile phone number op
	 */
	public void setMobilePhoneNumberOp(String mobilePhoneNumberOp) {
		this.mobilePhoneNumberOp = mobilePhoneNumberOp;
	}

	/**
	 * Gets the supervisor afm op.
	 *
	 * @return the supervisor afm op
	 */
	public String getSupervisorAFMOp() {
		return supervisorAFMOp;
	}

	/**
	 * Sets the supervisor afm op.
	 *
	 * @param supervisorAFMOp the new supervisor afm op
	 */
	public void setSupervisorAFMOp(String supervisorAFMOp) {
		this.supervisorAFMOp = supervisorAFMOp;
	}

	/**
	 * Create the insert query.
	 *
	 * @return the string
	 */
	public String insertQuery(){
		String query = "INSERT INTO Employees VALUES(";
		query = query + (StringUtils.hasText(afm) ? "'" + afm + "'," : "null,");
		query = query + (StringUtils.hasText(employeeNo) ? employeeNo+"," : "null,");
		query = query + (StringUtils.hasText(firstName) ? "'" + firstName + "'," : "null,");
		query = query + (StringUtils.hasText(lastName) ? "'" + lastName + "'," : "null,");
		query = query + (StringUtils.hasText(streetName) ? "'" + streetName + "'," : "null,");
		query = query + (StringUtils.hasText(streetNo) ? "'" + streetNo + "'," : "null,");
		query = query + (StringUtils.hasText(postalCode) ? "'" + postalCode + "'," : "null,");
		query = query + salary + ",";
		query = query + (StringUtils.hasText(workPhoneNumber) ? "'" + workPhoneNumber + "'," : "null,");
		query = query + (StringUtils.hasText(mobilePhoneNumber) ? "'" + mobilePhoneNumber + "'," : "null,");
		query = query + (StringUtils.hasText(supervisorAFM) ? "'" + supervisorAFM + "'" : "null") + ")";
		return query;
	}
	
	/**
	 * Update query.
	 *
	 * @param updateAfm the update afm
	 * @return the string
	 */
	public String updateQuery(String updateAfm){
		String query = "UPDATE Employees SET ";
		query = query + (StringUtils.hasText(afm) ? "afm='" + afm + "'," : "afm=null,");
		query = query + (StringUtils.hasText(employeeNo) ? "employeeNo="+ employeeNo+"," : "employeeNo=null,");
		query = query + (StringUtils.hasText(firstName) ? "firstName='" + firstName + "'," : "firstName=null,");
		query = query + (StringUtils.hasText(lastName) ? "lastName='" + lastName + "'," : "lastName=null,");
		query = query + (StringUtils.hasText(streetName) ? "Addr_StreetName='" + streetName + "'," : "Addr_StreetName=null,");
		query = query + (StringUtils.hasText(streetNo) ? "Addr_StreetNo='" + streetNo + "'," : "Addr_StreetNo=null,");
		query = query + (StringUtils.hasText(postalCode) ? "Addr_PostalCode='" + postalCode + "'," : "Addr_PostalCode=null,");
		query = query + "salary=" + salary + ",";
		query = query + (StringUtils.hasText(workPhoneNumber) ? "WorkPhoneNumber='" + workPhoneNumber + "'," : "WorkPhoneNumber=null,");
		query = query + (StringUtils.hasText(mobilePhoneNumber) ? "MobilePhoneNumber='" + mobilePhoneNumber + "'," : "MobilePhoneNumber=null,");
		query = query + (StringUtils.hasText(supervisorAFM) ? "SupervisorAFM='" + supervisorAFM + "'" : "SupervisorAFM=null,");
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
	public String searchQuery(){
		String query = "SELECT * FROM Employees e WHERE";
		query = query + Utils.constructSearchQuery(afmOp, afm, " e.AFM");
		query = query + Utils.constructSearchQuery(employeeNoOp, employeeNo, " e.EmployeeNo");
		query = query + Utils.constructSearchQuery(firstNameOp, firstName, " e.FirstName");
		query = query + Utils.constructSearchQuery(lastNameOp, lastName, " e.LastName");
		query = query + Utils.constructSearchQuery(streetNameOp, streetName, " e.Addr_StreetName");
		query = query + Utils.constructSearchQuery(streetNoOp, streetNo, " e.Addr_StreetNo");
		query = query + Utils.constructSearchQuery(postalCodeOp, postalCode, " e.Addr_PostalCode");
		query = query + (salary != null ? " e.Salary"+ salaryOp+"" + salary + " and" : "");
		query = query + Utils.constructSearchQuery(workPhoneNumberOp, workPhoneNumber, " e.WorkPhoneNumber");
		query = query + Utils.constructSearchQuery(mobilePhoneNumberOp, mobilePhoneNumber, " e.MobilePhoneNumber");
		query = query + Utils.constructSearchQuery(supervisorAFMOp, supervisorAFM, " e.SupervisorAFM");
		if(query.endsWith("WHERE"))
			query = query.substring(0, query.length()-8);
		else
			query = query.substring(0, query.length()-3);
		return query;
	}
	
}
