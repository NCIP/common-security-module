package gov.nih.nci.security.authorization.domainobjects;





/**
 * It is class which hold the roles which are assumed by a particular group in
 * context of a protection group.
 * @version 1.0
 * created 03-Dec-2004 1:17:52 AM
 */
public class UserRoleContext {

	/**
	 * The collection of roles assigned to a user for a protection group.
	 */
	private java.util.Set roles;
	/**
	 * The user who assumes these roles for a protection group
	 */
	private User user;

	public UserRoleContext(){

	}

	/**
	 * @param member
	 * @param roles    roles
	 * 
	 */
	public UserRoleContext(User member, java.util.Set roles){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The collection of roles assigned to a user for a protection group.
	 */
	public java.util.Set getRoles(){
		return roles;
	}

	/**
	 * The user who assumes these roles for a protection group
	 */
	public User getUser(){
		return user;
	}

	/**
	 * The collection of roles assigned to a user for a protection group.
	 * @param newVal
	 * 
	 */
	public void setRoles(java.util.Set newVal){
		roles = newVal;
	}

	/**
	 * The user who assumes these roles for a protection group
	 * @param newVal
	 * 
	 */
	public void setUser(User newVal){
		user = newVal;
	}

}