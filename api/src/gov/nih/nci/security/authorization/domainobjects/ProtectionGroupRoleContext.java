package gov.nih.nci.security.authorization.domainobjects;





/**
 * This class established the relationship between protection group and roles and
 * becomes a member for a user and a group.
 * @version 1.0
 * @created 03-Dec-2004 1:17:50 AM
 */
public class ProtectionGroupRoleContext {

	/**
	 * Collection of roles for this protection group
	 */
	private java.util.Set roles;
	/**
	 * The protection group for which roles are assigned.
	 */
	private ProtectionGroup protectionGroup;

	public ProtectionGroupRoleContext(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * Collection of roles for this protection group
	 */
	public java.util.Set getRoles(){
		return roles;
	}

	/**
	 * Collection of roles for this protection group
	 * @param newVal
	 * 
	 */
	public void setRoles(java.util.Set newVal){
		roles = newVal;
	}

	/**
	 * The protection group for which roles are assigned.
	 */
	public ProtectionGroup getProtectionGroup(){
		return protectionGroup;
	}

	/**
	 * The protection group for which roles are assigned.
	 * @param newVal
	 * 
	 */
	public void setProtectionGroup(ProtectionGroup newVal){
		protectionGroup = newVal;
	}

}