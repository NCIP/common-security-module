package gov.nih.nci.security.authorization.domainobjects;





/**
 * It is class which hold the roles which are assumed by a particular group in
 * context of a protection group.
 * @version 1.0
 * created 03-Dec-2004 1:17:49 AM
 */
public class GroupRoleContext {

	/**
	 * The collection of roles which the group can assume in context of a protection
	 * group
	 */
	private java.util.Set roles;
	/**
	 * The group object of the group which assumes the roles in context of a
	 * protection group
	 */
	private Group group;

	public GroupRoleContext(){

	}

	/**
	 * @param roles
	 * @param member    member
	 * 
	 */
	public GroupRoleContext(java.util.Set roles, Group member){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	/**
	 * The group object of the group which assumes the roles in context of a
	 * protection group
	 */
	public Group getGroup(){
		return group;
	}

	/**
	 * The collection of roles which the group can assume in context of a protection
	 * group
	 */
	public java.util.Set getRoles(){
		return roles;
	}

	/**
	 * The group object of the group which assumes the roles in context of a
	 * protection group
	 * @param newVal
	 * 
	 */
	public void setGroup(Group newVal){
		group = newVal;
	}

	/**
	 * The collection of roles which the group can assume in context of a protection
	 * group
	 * @param newVal
	 * 
	 */
	public void setRoles(java.util.Set newVal){
		roles = newVal;
	}

}