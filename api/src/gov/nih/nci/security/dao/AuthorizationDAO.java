package gov.nih.nci.security.dao;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.*;
import gov.nih.nci.security.exceptions.*;

import java.security.Principal;
import java.util.Set;
import java.util.Collection;

import javax.security.auth.Subject;





/**
 * This interface is used for persistence to the appropriate application. The
 * interface will persist to and retrive the data from the database.
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public interface AuthorizationDAO {

	/**
	 * @param protectionGroup
	 * 
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup) throws CSTransactionException;

	/**
	 * @param loginName
	 * 
	 */
	public User getUser(String loginName);

	public ApplicationContext getApplicationContext();

	/**
	 * @param protectionGroup
	 * 
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup) throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * 
	 */
	public void removeProtectionGroup(String protectionGroupName)throws CSTransactionException;

	/**
	 * @param element
	 * 
	 */
	public void removeProtectionElement(ProtectionElement element)throws CSTransactionException;

	/**
	 * @param protectionElementObjectName
	 * @param userName
	 * 
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectName, String userName)throws CSTransactionException;

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException;

	/**
	 * @param protectionElementObjectNames
	 * @param protectionGroupName
	 * 
	 */
	public void deAssignProtectionElements(String[] protectionElementObjectNames, String protectionGroupName)throws CSTransactionException;

	/**
	 * @param protectionElement
	 * 
	 */
	public void createProtectionElement(ProtectionElement protectionElement)throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * @param userName
	 * @param roles
	 * 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName, String userName, String[] roles)throws CSTransactionException;

	/**
	 * @param permission
	 * @param user
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, User user);

	/**
	 * @param role
	 * 
	 */
	public void createRole(Role role)throws CSTransactionException;

	/**
	 * @param permission
	 * @param subject
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject);

	/**
	 * @param role
	 * 
	 */
	public void modifyRole(Role role)throws CSTransactionException;

	/**
	 * @param permission
	 * @param userName
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, String userName);

	/**
	 * @param roleId
	 * 
	 */
	public void removeRole(String roleId)throws CSTransactionException;

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String attributeId, String privilegeName);

	/**
	 * @param privilege
	 * 
	 */
	public void createPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName);

	/**
	 * @param privilege
	 * 
	 */
	public void modifyPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * @param userName
	 * 
	 */
	public Principal[] getPrincipals(String userName);

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
	 * Returns the protection element for the passed object id
	 * @param objectId
	 * 
	 */
	public ProtectionElement getProtectionElement(String objectId) throws CSObjectNotFoundException;
	/**
	 * Returns the protection element for the passed object id
	 * @param protectionElementId
	 * 
	 */
	public ProtectionElement getProtectionElement(Long protectionElementId) throws CSObjectNotFoundException;
	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String protectionElementObjectId)throws CSTransactionException;

	/**
	 * @param group
	 * 
	 */
	public void createGroup(Group group)throws CSTransactionException;;

	/**
	 * @param userName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectName, String protectionElementAttributeName)throws CSTransactionException;;

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * @param protectionElementAttributeNames
	 * 
	 */
	public void deAssignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames, String[] protectionElementAttributeNames)throws CSTransactionException;;

	/**
	 * @param groupId
	 * 
	 */
	public void removeGroup(String groupId)throws CSTransactionException;;

	/**
	 * @param group
	 * 
	 */
	public void modifyGroup(Group group)throws CSTransactionException;;

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void addUserToGroup(String groupId, String userId)throws CSTransactionException;;

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId)throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String[] rolesId)throws CSTransactionException;;

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 */
	public Privilege getPrivilege(String privilegeId)throws CSObjectNotFoundException;;

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId)throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId)throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId)throws CSTransactionException;;

	/**
	 * @param protectionGroupName
	 * 
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)throws CSObjectNotFoundException;
    
	/**
	 * @param protectionGroupId
	 * 
	 */
	public ProtectionGroup getProtectionGroup(Long protectionGroupId)throws CSObjectNotFoundException;
	
	/**
	 * @param roleName
	 * 
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException;
	
	/**
	 * @param roleId
	 * 
	 */
	public Role getRole(Long roleId) throws CSObjectNotFoundException;
	
	/**
	 * @param groupId
	 * 
	 */
	public Group getGroup(Long groupId) throws CSObjectNotFoundException;
	
	/**
	 * @param groupName
	 * 
	 */
	public Group getGroup(String groupName) throws CSObjectNotFoundException;

	
	public Set getPrivileges(String roleId) throws CSObjectNotFoundException;
	/**
	 * @param searchCriteria
	 * 
	 */
	public java.util.Set getObjects(SearchCriteria searchCriteria);
	
	public void createUser(User user) throws CSTransactionException;
	
	public void assignProtectionElement(String protectionGroupId,String[] protectionElementIds) throws CSTransactionException;


}

