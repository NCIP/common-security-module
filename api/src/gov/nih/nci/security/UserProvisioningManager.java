package gov.nih.nci.security;

import java.util.Set;

import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.*;





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
	public void createProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException;

	/**
	 * @param protectionGroup
	 * 
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * 
	 */
	public void removeProtectionGroup(String protectionGroupId)throws CSTransactionException;

	/**
	 * @param element
	 * 
	 */
	public void removeProtectionElement(String protectionElementId)throws CSTransactionException;

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * @param userName
	 * @param roles
	 * 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId, String userId, String[] rolesId)throws CSTransactionException;

	/**
	 * @param role
	 * 
	 */
	public void createRole(Role role)throws CSTransactionException;

	/**
	 * @param role
	 * 
	 */
	public void modifyRole(Role role)throws CSTransactionException;

	/**
	 * @param roleId
	 * 
	 */
	public void removeRole(String roleId)throws CSTransactionException;

	/**
	 * @param privilege
	 * 
	 */
	public void createPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * @param privilege
	 * 
	 */
	public void modifyPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * @param privilegeId
	 * 
	 */
	public void removePrivilege(String privilegeId)throws CSTransactionException;

	/**
	 * @param roleId
	 * @param privilegeIds
	 * 
	 */
	public void assignPrivilegesToRole(String roleId,String[] privilegeIds)throws CSTransactionException;

	/**
	 * @param group
	 * 
	 */
	public void createGroup(Group group)throws CSTransactionException;

	/**
	 * @param groupId
	 * 
	 */
	public void removeGroup(String groupId)throws CSTransactionException;

	/**
	 * @param group
	 * 
	 */
	public void modifyGroup(Group group)throws CSTransactionException;

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void addUserToGroup(String groupId, String userId)throws CSTransactionException;

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId)throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId[])throws CSTransactionException;

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 */
	public Privilege getPrivilege(String privilegeId) throws CSObjectNotFoundException;

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId)throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId)throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId)throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * 
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)throws CSObjectNotFoundException;

	/**
	 * @param roleName
	 * 
	 */
	public Role getRole(String roleName)throws CSObjectNotFoundException;
	/**
	 * @param roleId
	 * 
	 */
	public Role getRoleById(String roleId) throws CSObjectNotFoundException;
	/**
	 * @param roleId
	 * 
	 */
	public Set getPrivileges(String roleId) throws CSObjectNotFoundException;

	/**
	 * @param serachCriteria
	 * 
	 */
	public java.util.Set getObjects(SearchCriteria searchCriteria);
	
	public void createUser(User user) throws  CSTransactionException;
	
	public ProtectionGroup getProtectionGroup(Long protectionGroupId) throws CSObjectNotFoundException;
	
	public void assignProtectionElements(String protectionGroupId,String[] protectionElementIds) throws CSTransactionException;
	
	public void removeProtectionElementsFromProtectionGroup(String protectionGroupId,String[] protectionLementIds) throws CSTransactionException;
	
	public Set getProtectionGroupRoleContext(String userId) throws CSObjectNotFoundException;

}

