package gov.nih.nci.security.authorization.domainobjects;

import java.util.Date;





/**
 * @created 17-Nov-2004 3:19:57 PM
 * @version 1.0
 */
public class Application extends ApplicationContext {

	/**
	 * The collection of protection elements which belong to this application
	 */
	private java.util.Set protectionElements;
	/**
	 * The collection of groups which belong to this application
	 */
	private java.util.Set groups;
	/**
	 * The collection of roles which belong to this application
	 */
	private java.util.Set roles;
	/**
	 * The collection of protection groups which belong to this application
	 */
	private java.util.Set protectionGroups;
	/**
	 * The date when the data for this application was last modified
	 */
	private java.util.Date updateDate;
	/**
	 * The brief description of the application
	 */
	private String applicationDescription;

	public Application(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The brief description of the application
	 */
	public String getApplicationDescription(){
		return applicationDescription;
	}

	/**
	 * The collection of groups which belong to this application
	 */
	public java.util.Set getGroups(){
		return groups;
	}

	/**
	 * The collection of protection elements which belong to this application
	 */
	public java.util.Set getProtectionElements(){
		return protectionElements;
	}

	/**
	 * The collection of protection groups which belong to this application
	 */
	public java.util.Set getProtectionGroups(){
		return protectionGroups;
	}

	/**
	 * The collection of roles which belong to this application
	 */
	public java.util.Set getRoles(){
		return roles;
	}

	/**
	 * The date when the data for this application was last modified
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * The brief description of the application
	 * @param newVal    newVal
	 * 
	 */
	public void setApplicationDescription(String newVal){
		applicationDescription = newVal;
	}

	/**
	 * The collection of groups which belong to this application
	 * @param newVal    newVal
	 * 
	 */
	public void setGroups(java.util.Set newVal){
		groups = newVal;
	}

	/**
	 * The collection of protection elements which belong to this application
	 * @param newVal    newVal
	 * 
	 */
	public void setProtectionElements(java.util.Set newVal){
		protectionElements = newVal;
	}

	/**
	 * The collection of protection groups which belong to this application
	 * @param newVal    newVal
	 * 
	 */
	public void setProtectionGroups(java.util.Set newVal){
		protectionGroups = newVal;
	}

	/**
	 * The collection of roles which belong to this application
	 * @param newVal    newVal
	 * 
	 */
	public void setRoles(java.util.Set newVal){
		roles = newVal;
	}

	/**
	 * The date when the data for this application was last modified
	 * @param newVal    newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

}