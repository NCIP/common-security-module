package gov.nih.nci.security.authorization.domainobjects;
import java.security.Principal;
import java.util.Date;





/**
 * The user class is a representation of an application user.
 * @version 1.0
 * @created 03-Dec-2004 1:17:51 AM
 */
public class User implements Principal {

	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 */
	private java.util.Set protectionGroupRoleContexts;
	/**
	 * This attribute tells which groups does this user belong to.
	 */
	private java.util.Set groups;
	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 */
	private java.util.Set protectionElements;
	/**
	 * This a unique id to identify a user within an application.
	 */
	private Long userId;
	/**
	 * This string is used for login into the application.
	 */
	private String loginName;
	/**
	 * The first name of the user
	 */
	private String firstName;
	/**
	 * The last name of the user
	 */
	private String lastName;
	/**
	 * The name of the organization that this user belongs to.
	 */
	private String organization;
	/**
	 * The name of the department that this user belongs to.
	 */
	private String department;
	/**
	 * The name of the title for this user.
	 */
	private String title;
	/**
	 * This is the work phone of the user.
	 */
	private String phoneNumber;
	/**
	 * The password used to login into the application
	 */
	private String password;
	/**
	 * Email id for this user.
	 */
	private String emailId;
	/**
	 * It is the start date for this user.
	 */
	private java.util.Date startDate;
	/**
	 * It is the end date for this user.
	 */
	private java.util.Date endDate;
	/**
	 * It is the date when the user information was last updated
	 */
	private java.util.Date updateDate;

	public User(){

	}

	/**
	 * @exception Throwable Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The name of the department that this user belongs to.
	 */
	public String getDepartment(){
		return department;
	}

	/**
	 * Email id for this user.
	 */
	public String getEmailId(){
		return emailId;
	}

	/**
	 * It is the end date for this user.
	 */
	public Date getEndDate(){
		return endDate;
	}

	/**
	 * The first name of the user
	 */
	public String getFirstName(){
		return firstName;
	}

	/**
	 * This attribute tells which groups does this user belong to.
	 */
	public java.util.Set getGroups(){
		return groups;
	}

	/**
	 * The last name of the user
	 */
	public String getLastName(){
		return lastName;
	}

	/**
	 * This string is used for login into the application.
	 */
	public String getLoginName(){
		return loginName;
	}

	/**
	 * The name of the organization that this user belongs to.
	 */
	public String getOrganization(){
		return organization;
	}

	/**
	 * The password used to login into the application
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * This is the work phone of the user.
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}

	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 */
	public java.util.Set getProtectionElements(){
		return protectionElements;
	}

	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 */
	public java.util.Set getProtectionGroupRoleContexts(){
		return protectionGroupRoleContexts;
	}

	/**
	 * It is the start date for this user.
	 */
	public Date getStartDate(){
		return startDate;
	}

	/**
	 * The name of the title for this user.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * It is the date when the user information was last updated
	 */
	public Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * This a unique id to identify a user within an application.
	 */
	public Long getUserId(){
		return userId;
	}

	/**
	 * The name of the department that this user belongs to.
	 * @param newVal
	 * 
	 */
	public void setDepartment(String newVal){
		department = newVal;
	}

	/**
	 * Email id for this user.
	 * @param newVal
	 * 
	 */
	public void setEmailId(String newVal){
		emailId = newVal;
	}

	/**
	 * It is the end date for this user.
	 * @param newVal
	 * 
	 */
	public void setEndDate(Date newVal){
		endDate = newVal;
	}

	/**
	 * The first name of the user
	 * @param newVal
	 * 
	 */
	public void setFirstName(String newVal){
		firstName = newVal;
	}

	/**
	 * This attribute tells which groups does this user belong to.
	 * @param newVal
	 * 
	 */
	public void setGroups(java.util.Set newVal){
		groups = newVal;
	}

	/**
	 * The last name of the user
	 * @param newVal
	 * 
	 */
	public void setLastName(String newVal){
		lastName = newVal;
	}

	/**
	 * This string is used for login into the application.
	 * @param newVal
	 * 
	 */
	public void setLoginName(String newVal){
		loginName = newVal;
	}

	/**
	 * The name of the organization that this user belongs to.
	 * @param newVal
	 * 
	 */
	public void setOrganization(String newVal){
		organization = newVal;
	}

	/**
	 * The password used to login into the application
	 * @param newVal
	 * 
	 */
	public void setPassword(String newVal){
		password = newVal;
	}

	/**
	 * This is the work phone of the user.
	 * @param newVal
	 * 
	 */
	public void setPhoneNumber(String newVal){
		phoneNumber = newVal;
	}

	/**
	 * A collection of ProtectionElement objects. Indicates the protection elements
	 * owned by this user.
	 * @param newVal
	 * 
	 */
	public void setProtectionElements(java.util.Set newVal){
		protectionElements = newVal;
	}

	/**
	 * This a collection of protectionGroupRoleContext objects. It indicates as to
	 * which protection groups this user has access to and in which roles.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupRoleContexts(java.util.Set newVal){
		protectionGroupRoleContexts = newVal;
	}

	/**
	 * It is the start date for this user.
	 * @param newVal
	 * 
	 */
	public void setStartDate(Date newVal){
		startDate = newVal;
	}

	/**
	 * The name of the title for this user.
	 * @param newVal
	 * 
	 */
	public void setTitle(String newVal){
		title = newVal;
	}

	/**
	 * It is the date when the user information was last updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(Date newVal){
		updateDate = newVal;
	}

	/**
	 * This a unique id to identify a user within an application.
	 * @param newVal
	 * 
	 */
	public void setUserId(Long newVal){
		userId = newVal;
	}

	public int hashCode(){
		return 0;
	}

	/**
	 * @param obj    obj
	 * 
	 */
	public boolean equals(Object obj){
		User other = (User)obj;
		if(this.userId.toString().equals(other.getUserId().toString())){
			return true;
		}else{
		return false;
		}
	}

	public String toString(){
		return "";
	}

	public String getName(){
		return this.loginName;
	}

}