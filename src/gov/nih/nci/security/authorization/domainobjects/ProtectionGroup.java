package gov.nih.nci.security.authorization.domainobjects;

import java.util.Date;





/**
 * This class represents a Protection Group. It is nothing but a collection of
 * protection elements. A user or a user-group can assume various roles on a
 * protection group
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class ProtectionGroup {

	/**
	 * It is the unique id by which it is identified in an application. 
	 */
	private Long protectionGroupId;
	/**
	 * It is the name of the protection element 
	 */
	private String protectionGroupName;
	/**
	 * A collection of protection elements that belongs to this protection group. 
	 */
	private java.util.Set protectionElements;
	/**
	 * The brief description of the protection group.
	 */
	private String protectionGroupDescription;
	/**
	 * The date when the protection group was last updated
	 */
	private java.util.Date updateDate;
	/**
	 * The protection group which is parent of the current protection group
	 */
	private ProtectionGroup parentProtectionGroup;
	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 */
	private byte largeElementCountFlag;
	/**
	 * The application to which the following protection group belongs
	 */
	private Application application;

	public ProtectionGroup(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The application to which the following protection group belongs
	 */
	public Application getApplication(){
		return application;
	}

	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 */
	public byte getLargeElementCountFlag(){
		return largeElementCountFlag;
	}

	/**
	 * The protection group which is parent of the current protection group
	 */
	public ProtectionGroup getParentProtectionGroup(){
		return parentProtectionGroup;
	}

	/**
	 * A collection of protection elements that belongs to this protection group. 
	 */
	public java.util.Set getProtectionElements(){
		return protectionElements;
	}

	/**
	 * The brief description of the protection group.
	 */
	public String getProtectionGroupDescription(){
		return protectionGroupDescription;
	}

	/**
	 * It is the unique id by which it is identified in an application. 
	 */
	public Long getProtectionGroupId(){
		return protectionGroupId;
	}

	/**
	 * It is the name of the protection element 
	 */
	public String getProtectionGroupName(){
		return protectionGroupName;
	}

	/**
	 * The date when the protection group was last updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * The application to which the following protection group belongs
	 * @param newVal
	 * 
	 */
	public void setApplication(Application newVal){
		application = newVal;
	}

	/**
	 * The flag indicating whether the protection group has large number of protection
	 * elements or not
	 * @param newVal
	 * 
	 */
	public void setLargeElementCountFlag(byte newVal){
		largeElementCountFlag = newVal;
	}

	/**
	 * The protection group which is parent of the current protection group
	 * @param newVal
	 * 
	 */
	public void setParentProtectionGroup(ProtectionGroup newVal){
		parentProtectionGroup = newVal;
	}

	/**
	 * A collection of protection elements that belongs to this protection group.
	 * @param newVal
	 * 
	 */
	public void setProtectionElements(java.util.Set newVal){
		protectionElements = newVal;
	}

	/**
	 * The brief description of the protection group.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupDescription(String newVal){
		protectionGroupDescription = newVal;
	}

	/**
	 * It is the unique id by which it is identified in an application.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupId(Long newVal){
		protectionGroupId = newVal;
	}

	/**
	 * It is the name of the protection element
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupName(String newVal){
		protectionGroupName = newVal;
	}

	/**
	 * The date when the protection group was last updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

	/**
	 * @param members    members
	 * 
	 */
	public void addGroups(GroupRoleContext[] members){

	}

	/**
	 * @param members    members
	 * 
	 */
	public void addUsers(UserRoleContext[] members){

	}

	/**
	 * @param elements    elements
	 * 
	 */
	public void addProtectionElements(ProtectionElement[] elements){

	}

}