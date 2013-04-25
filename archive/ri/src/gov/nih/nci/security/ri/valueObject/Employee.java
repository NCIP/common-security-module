/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.validator.ValidatorForm;

/** @author Hibernate CodeGenerator */
public class Employee extends ValidatorForm implements Serializable, Cloneable {

	/** identifier field */
	private Long employeeId;

	/** persistent field */
	private String firstName;

	/** nullable persistent field */
	private String middleName;

	/** persistent field */
	private String lastName;

	/** nullable persistent field */
	private String phoneNumber;

	/** nullable persistent field */
	private String emailAddr;

	/** nullable persistent field */
	private String streetAddr;

	/** nullable persistent field */
	private String city;

	/** nullable persistent field */
	private String state;

	/** nullable persistent field */
	private String zip;

	/** nullable persistent field */
	private String salary;

	/** nullable persistent field */
	private String ssn;

	/** persistent field */
	private gov.nih.nci.security.ri.valueObject.Employee employee;

	/** persistent field */
	private Set employeeProjects;

	private String managerStatus;

	/**
	 * @return Returns the managerStatus.
	 */
	public String getManagerStatus() {
		return managerStatus;
	}

	/**
	 * @param managerStatus
	 *            The managerStatus to set.
	 */
	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}

	/** persistent field */
	private Set employees;

	private String userName;

	private String password;

	private String businessUnit;

	/**
	 * @return Returns the businessUnit.
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * @param businessUnit
	 *            The businessUnit to set.
	 */
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	String[] associatedIds;

	String[] availableIds;

	/** full constructor */
	public Employee(Long employeeId, String firstName, String middleName,
			String lastName, String phoneNumber, String emailAddr,
			String streetAddr, String city, String state, String zip,
			String salary, String ssn,
			gov.nih.nci.security.ri.valueObject.Employee employee,
			Set employeeProjects, Set employees) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddr = emailAddr;
		this.streetAddr = streetAddr;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.salary = salary;
		this.ssn = ssn;
		this.employee = employee;
		this.employeeProjects = employeeProjects;
		this.employees = employees;
	}

	public Employee(Employee empl) {
		this(empl.getEmployeeId(), empl.getFirstName(), empl.getMiddleName(),
				empl.getLastName(), empl.getPhoneNumber(), empl.getEmailAddr(),
				empl.getStreetAddr(), empl.getCity(), empl.getState(), empl
						.getZip(), empl.getSalary(), empl.getSsn(), empl
						.getEmployee(), empl.getEmployeeProjects(), empl
						.getEmployees());
	}

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Long employeeId, String firstName, String lastName,
			gov.nih.nci.security.ri.valueObject.Employee employee,
			Set employeeProjects, Set employees) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employee = employee;
		this.employeeProjects = employeeProjects;
		this.employees = employees;
	}

	public Long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddr() {
		return this.emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getStreetAddr() {
		return this.streetAddr;
	}

	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public gov.nih.nci.security.ri.valueObject.Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(
			gov.nih.nci.security.ri.valueObject.Employee employee) {
		this.employee = employee;
	}

	public Set getEmployeeProjects() {
		return this.employeeProjects;
	}

	public void setEmployeeProjects(Set employeeProjects) {
		this.employeeProjects = employeeProjects;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public String toString() {
		return new ToStringBuilder(this).append("employeeId", getEmployeeId())
				.toString();
	}

	/**
	 * @return Returns the associatedIds.
	 */
	public String[] getAssociatedIds() {
		return associatedIds;
	}

	/**
	 * @param associatedIds
	 *            The associatedIds to set.
	 */
	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}

	/**
	 * @return Returns the availableIds.
	 */
	public String[] getAvailableIds() {
		return availableIds;
	}

	/**
	 * @param availableIds
	 *            The availableIds to set.
	 */
	public void setAvailableIds(String[] availableIds) {
		this.availableIds = availableIds;
	}

	/**
	 * @param obj
	 *  
	 */
	public boolean equals(Object obj) {
		Employee other = (Employee) obj;
		if (this.getEmployeeId().longValue() == other.getEmployeeId().longValue()) {
			return true;
		} else {
			return false;
		}
	}
}