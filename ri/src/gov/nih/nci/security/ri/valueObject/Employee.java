package gov.nih.nci.security.ri.valueObject;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.validator.ValidatorForm;


/** @author Hibernate CodeGenerator */
public class Employee extends ValidatorForm implements Serializable {

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
    private String streetAddr;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String salary;

    /** nullable persistent field */
    private String ssn;
    
    /** nullable persistent field */
    private String emailAddr;
    
    /** nullable persistent field */
    private String zip;

    /** persistent field */
    private gov.nih.nci.security.ri.valueObject.Employee employee;

    /** persistent field */
    private Set employeeProjects;

    /** persistent field */
    private Set employees;

    /** full constructor */
    public Employee(Long employeeId, String firstName, String middleName, String lastName, String phoneNumber, String streetAddr, String city, String state, String salary, String ssn, gov.nih.nci.security.ri.valueObject.Employee employee, Set employeeProjects, Set employees) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.streetAddr = streetAddr;
        this.city = city;
        this.state = state;
        this.salary = salary;
        this.ssn = ssn;
        this.employee = employee;
        this.employeeProjects = employeeProjects;
        this.employees = employees;
    }

    /** default constructor */
    public Employee() {
    }

    /** minimal constructor */
    public Employee(Long employeeId, String firstName, String lastName, gov.nih.nci.security.ri.valueObject.Employee employee, Set employeeProjects, Set employees) {
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

    public void setEmployee(gov.nih.nci.security.ri.valueObject.Employee employee) {
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
        return new ToStringBuilder(this)
            .append("employeeId", getEmployeeId())
            .toString();
    }

	/**
	 * @return Returns the emailAddr.
	 */
	public String getEmailAddr() {
		return emailAddr;
	}
	/**
	 * @param emailAddr The emailAddr to set.
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
