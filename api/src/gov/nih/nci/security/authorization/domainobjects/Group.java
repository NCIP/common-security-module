package gov.nih.nci.security.authorization.domainobjects;
import java.security.Principal;





/**
 * A Group is a logical collection of users. A group belongs to an application.
 * @version 1.0
 * @created 03-Dec-2004 1:17:49 AM
 */
public class Group implements Principal {

	/**
	 * This is the collection of ProtectionGroupRoleContext objects indicating the
	 * access of this group on the protection groups with respect to roles.
	 */
	private java.util.Set protectionGroupRoleContexts;
	/**
	 * A collection of User objects. Indicates which users belongs to this group.
	 */
	private java.util.Set users;
	/**
	 * It is the unique id by which it is identified within an application.
	 */
	private Long groupId;
	/**
	 * It is the logical name for the group.
	 */
	private String groupName;
	/**
	 * This is the brief description of the group.
	 */
	private String groupDesc;
	/**
	 * The date when the group information was updated
	 */
	private java.util.Date updateDate;
	/**
	 * The application to which the following group belongs 
	 */
	private Application application;
	public ProtectionGroupRoleContext m_ProtectionGroupRoleContext;

	public Group(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * The application to which the following group belongs 
	 */
	public Application getApplication(){
		return application;
	}

	/**
	 * This is the brief description of the group.
	 */
	public String getGroupDesc(){
		return groupDesc;
	}

	/**
	 * It is the unique id by which it is identified within an application.
	 */
	public Long getGroupId(){
		return groupId;
	}

	/**
	 * It is the logical name for the group.
	 */
	public String getGroupName(){
		return groupName;
	}

	/**
	 * This is the collection of ProtectionGroupRoleContext objects indicating the
	 * access of this group on the protection groups with respect to roles.
	 */
	public java.util.Set getProtectionGroupRoleContexts(){
		return protectionGroupRoleContexts;
	}

	/**
	 * The date when the group information was updated
	 */
	public java.util.Date getUpdateDate(){
		return updateDate;
	}

	/**
	 * A collection of User objects. Indicates which users belongs to this group.
	 */
	public java.util.Set getUsers(){
		return users;
	}

	/**
	 * The application to which the following group belongs
	 * @param newVal
	 * 
	 */
	public void setApplication(Application newVal){
		application = newVal;
	}

	/**
	 * This is the brief description of the group.
	 * @param newVal
	 * 
	 */
	public void setGroupDesc(String newVal){
		groupDesc = newVal;
	}

	/**
	 * It is the unique id by which it is identified within an application.
	 * @param newVal
	 * 
	 */
	public void setGroupId(Long newVal){
		groupId = newVal;
	}

	/**
	 * It is the logical name for the group.
	 * @param newVal
	 * 
	 */
	public void setGroupName(String newVal){
		groupName = newVal;
	}

	/**
	 * This is the collection of ProtectionGroupRoleContext objects indicating the
	 * access of this group on the protection groups with respect to roles.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroupRoleContexts(java.util.Set newVal){
		protectionGroupRoleContexts = newVal;
	}

	/**
	 * The date when the group information was updated
	 * @param newVal
	 * 
	 */
	public void setUpdateDate(java.util.Date newVal){
		updateDate = newVal;
	}

	/**
	 * A collection of User objects. Indicates which users belongs to this group.
	 * @param newVal
	 * 
	 */
	public void setUsers(java.util.Set newVal){
		users = newVal;
	}

	public int hashCode(){
		return 0;
	}

	/**
	 * @param obj
	 * 
	 */
	public boolean equals(Object obj){
		Group other = (Group)obj;
		if(this.groupId.toString().equals(other.getGroupId().toString())){
			return true;
		}else{
		return false;
		}
	}

	public String toString(){
		return "";
	}

	public String getName(){
		return "";
	}

}