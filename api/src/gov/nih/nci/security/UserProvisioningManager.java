package gov.nih.nci.security;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.dao.SearchCriteria;





/**
 * The UserProvisioningManager interface extends the AuthorizationManager
 * interface and is used for user provisioning. The UserProvisioningManager exist
 * in context of an application.
 * @version 1.0
 * @created 03-Dec-2004 1:17:51 AM
 */
public interface UserProvisioningManager extends AuthorizationManager {

	/**
	 * @param protectionGroup
	 * 
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup);

	/**
	 * @param protectionGroup
	 * 
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup);

	/**
	 * @param protectionGroupName
	 * 
	 */
	public void removeProtectionGroup(String protectionGroupName);

	/**
	 * @param element
	 * 
	 */
	public void removeProtectionElement(ProtectionElement element);

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId);

	/**
	 * @param protectionGroupName
	 * @param userName
	 * @param roles
	 * 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName, String userName, String[] roles);

	/**
	 * @param role
	 * 
	 */
	public void createRole(Role role);

	/**
	 * @param role
	 * 
	 */
	public void modifyRole(Role role);

	/**
	 * @param roleId
	 * 
	 */
	public void removeRole(String roleId);

	/**
	 * @param privilege
	 * 
	 */
	public void createPrivilege(Privilege privilege);

	/**
	 * @param privilege
	 * 
	 */
	public void modifyPrivilege(Privilege privilege);

	/**
	 * @param privilegeId
	 * 
	 */
	public void removePrivilege(String privilegeId);

	/**
	 * @param privilegesName
	 * @param roleName
	 * 
	 */
	public void assignPrivilegesToRole(String[] privilegesName, String roleName);

	/**
	 * @param role
	 * @param privilegeName
	 * 
	 */
	public void removePrivilegesFromRole(String role, String[] privilegeName);

	/**
	 * @param group
	 * 
	 */
	public void createGroup(Group group);

	/**
	 * @param groupId
	 * 
	 */
	public void removeGroup(String groupId);

	/**
	 * @param group
	 * 
	 */
	public void modifyGroup(Group group);

	/**
	 * @param groupId
	 * @param user
	 * 
	 */
	public void addUserToGroup(String groupId, String user);

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId);

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId);

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 */
	public Privilege getPrivilege(String privilegeId);

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId);

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId);

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId);

	/**
	 * @param protectionGroupName
	 * 
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName);

	/**
	 * @param roleName
	 * 
	 */
	public Role getRole(String roleName);

	/**
	 * @param serachCriteria
	 * 
	 */
	public java.util.Set getObjects(SearchCriteria serachCriteria);

}

