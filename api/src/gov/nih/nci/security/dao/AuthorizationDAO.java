package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.*;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;

import javax.security.auth.Subject;

/**
 * This interface is used for persistence to the appropriate application. The
 * interface will persist to and retrive the data from the database.
 * 
 * @version 1.0
 * created 03-Dec-2004 1:17:47 AM
 */
public interface AuthorizationDAO {

	/**
	 * @param protectionGroup
	 *  
	 */
	/**
	public void createProtectionGroup(ProtectionGroup protectionGroup)
			throws CSTransactionException;
     */
	/**
	 * @param loginName
	 *  
	 */
	public User getUser(String loginName);

	public ApplicationContext getApplicationContext();
	
	public Application getApplication();

	
	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 *  
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId,
			String protectionElementAttributeName)
			throws CSTransactionException;

	
	

	/**
	 * @param protectionElementObjectId
	 * @param userNames
	 *  
	 */
	public void setOwnerForProtectionElement(
			String protectionElementObjectId, String[] userNames)
			throws CSTransactionException;

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 *  
	 */
	public void assignUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId)
			throws CSTransactionException;

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 *  
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException;

	
	/**
	 * @param protectionGroupId
	 * @param userId
	 * @param rolesId 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId, String userId, String[] rolesId) throws CSTransactionException;

	
	
	/**
	 * @param permission
	 * @param subject
	 * @throws CSException
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) throws CSException;

	
	/**
	 * @param permission
	 * @param userName
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, String userName) throws CSException;

	

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 *  
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeId, String privilegeName) throws CSException;

	/**
	 * @param privilege
	 *  
	 */
	/**
	public void createPrivilege(Privilege privilege)
			throws CSTransactionException;
    */
	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 *  
	 */
	public boolean checkPermission(String userName, String objectId,
			String privilegeName) throws CSException;

	

	/**
	 * @param userName
	 *  
	 */
	public Principal[] getPrincipals(String userName);

	
	/**
	 * @param roleId
	 * @param privilegeIds
	 *  
	 */
	public void assignPrivilegesToRole(String roleId, String[] privilegeIds)
			throws CSTransactionException;

	/**
	 * Returns the protection element for the passed object id
	 * 
	 * @param objectId
	 *  
	 */
	public ProtectionElement getProtectionElement(String objectId)
			throws CSObjectNotFoundException;

	

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 *  
	 */
	public void assignProtectionElement(String protectionGroupName,
			String protectionElementObjectId) throws CSTransactionException;

	

	
	/**
	 * @param userName
	 * @param protectionElementName
	 * @param protectionElementAttributeName
	 * @throws CSTransactionException
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)
			throws CSTransactionException;;
	
	/**
	 * @param userId
	 * @param groupIds
	 */
	public void assignGroupsToUser(String userId,String[] groupIds)
			throws CSTransactionException;;

	/**
	 * @param groupId
	 * @param userId
	 *  
	 */
	public void removeUserFromGroup(String groupId, String userId)
			throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 *  
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String[] rolesId) throws CSTransactionException;;

	
	/**
	 * This method removes the user from a protection group irrespective of all
	 * the roles
	 * 
	 * @param protectionGroupId
	 * @param userId
	 *  
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId,
			String userId) throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 *  
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId,
			String groupId, String[] roleId) throws CSTransactionException;;

	/**
	 * @param protectionGroupId
	 * @param groupId
	 *  
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId,
			String groupId) throws CSTransactionException;;

	/**
	 * @param protectionGroupName
	 *  
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName)
			throws CSObjectNotFoundException;

	

	/**
	 * @param roleName
	 *  
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException;

	

	

	

	public Set getPrivileges(String roleId) throws CSObjectNotFoundException;

	/**
	 * @param searchCriteria
	 *  
	 */
	public java.util.List getObjects(SearchCriteria searchCriteria);

	//public void createUser(User user) throws CSTransactionException;

	public void assignProtectionElements(String protectionGroupId,
			String[] protectionElementIds) throws CSTransactionException;

	public void removeProtectionElementsFromProtectionGroup(
			String protectionGroupId, String[] protectionLementIds)
			throws CSTransactionException;
	
	public Set getProtectionGroupRoleContextForUser(String userId) throws CSObjectNotFoundException;
	
	public Set getProtectionGroupRoleContextForGroup(String groupId) throws CSObjectNotFoundException;

	
	
	
	public void removeObject(Object obj) throws CSTransactionException;
	
	public void modifyObject(Object obj) throws CSTransactionException;
	
	public Object getObjectByPrimaryKey(Class objectType, String primaryKey)throws CSObjectNotFoundException;
	
	public void createObject(Object obj) throws CSTransactionException;
	
	public Set getGroups(String userId) throws CSObjectNotFoundException;
	
	public Set getProtectionElements(String protectionGroupId) throws CSObjectNotFoundException;

	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException;
	
	public void assignToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException;

	public void assignParentProtectionGroup(String parentProtectionGroupId,String childProtectionGroupId) throws CSTransactionException;
	
	public void assignOwners(String protectionElementId,String[] userIds) throws CSTransactionException;
	
	public Set getOwners(String protectionElementId) throws CSObjectNotFoundException;

	public Object secureObject(String userName, Object obj) throws CSException;
	
	public Collection secureCollection(String userName,Collection objects) throws CSException;
	
	public Collection getPrivilegeMap(String userName,Collection pEs) throws CSException;
}

