package gov.nih.nci.security.authorization.domainobjects;

import java.util.Date;





/**
 * A ProtectionElement is a any resource that is a candidate for to be protected.
 * This way the access to different part of the data can be restricted.
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class ProtectionElement {

	/**
	 * It is the unique id by which it is identified in an application.
	 */
	private Long protectionElementId;
	/**
	 * It is the name of the protection element
	 */
	private String protectionElementName;
	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * attribute forms the unique key which can identify the protection element
	 */
	private String objectId;
	/**
	 * A collection of protection groups that this protection element belongs to.
	 */
	private java.util.Set protectionGroups;
	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * object id forms the unique key which can identify the protection element
	 */
	private String attribute;
	/**
	 * A brief descrition of the protection element
	 */
	private String protectionElementDescription;
	/**
	 * The date when the data for the protection element was updated
	 */
	private java.util.Date updateDate;
	/**
	 * The application to which the protection element belongs
	 */
	private Application application;
	/**
	 * The user which owns the protection element. It will be populated only if the
	 * protection element was created at run time by the user
	 */
	private User owner;

	public ProtectionElement(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The application to which the protection element belongs
	 */
	public Application getApplication(){
		return application;
	}

	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * object id forms the unique key which can identify the protection element
	 */
	public String getAttribute(){
		return attribute;
	}

	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * attribute forms the unique key which can identify the protection element
	 */
	public String getObjectId(){
		return objectId;
	}

	/**
	 * The user which owns the protection element. It will be populated only if the
	 * protection element was created at run time by the user
	 */
	public User getOwner(){
		return owner;
	}

	/**
	 * A brief descrition of the protection element
	 */
	public String getProtectionElementDescription(){
		return protectionElementDescription;
	}

	/**
	 * It is the unique id by which it is identified in an application.
	 */
	public Long getProtectionElementId(){
		return protectionElementId;
	}

	/**
	 * It is the name of the protection element
	 */
	public String getProtectionElementName(){
		return protectionElementName;
	}

	/**
	 * A collection of protection groups that this protection element belongs to.
	 */
	public java.util.Set getProtectionGroups(){
		return protectionGroups;
	}

	/**
	 * The date when the data for the protection element was updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * The application to which the protection element belongs
	 * @param newVal
	 * 
	 */
	public void setApplication(Application newVal){
		application = newVal;
	}

	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * object id forms the unique key which can identify the protection element
	 * @param newVal
	 * 
	 */
	public void setAttribute(String newVal){
		attribute = newVal;
	}

	/**
	 * It is the attribute of the object which is to be protected. It along with the
	 * attribute forms the unique key which can identify the protection element
	 * @param newVal
	 * 
	 */
	public void setObjectId(String newVal){
		objectId = newVal;
	}

	/**
	 * The user which owns the protection element. It will be populated only if the
	 * protection element was created at run time by the user
	 * @param newVal
	 * 
	 */
	public void setOwner(User newVal){
		owner = newVal;
	}

	/**
	 * A brief descrition of the protection element
	 * @param newVal
	 * 
	 */
	public void setProtectionElementDescription(String newVal){
		protectionElementDescription = newVal;
	}

	/**
	 * It is the unique id by which it is identified in an application.
	 * @param newVal
	 * 
	 */
	public void setProtectionElementId(Long newVal){
		protectionElementId = newVal;
	}

	/**
	 * It is the name of the protection element
	 * @param newVal
	 * 
	 */
	public void setProtectionElementName(String newVal){
		protectionElementName = newVal;
	}

	/**
	 * A collection of protection groups that this protection element belongs to.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroups(java.util.Set newVal){
		protectionGroups = newVal;
	}

	/**
	 * The date when the data for the protection element was updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

	/**
	 * @param protectionGroups    protectionGroups
	 * 
	 */
	public void assignToGroups(ProtectionGroup[] protectionGroups){

	}

}