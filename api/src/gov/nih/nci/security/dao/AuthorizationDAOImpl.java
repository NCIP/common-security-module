package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.*;

import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;

/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public class AuthorizationDAOImpl implements AuthorizationDAO {

	public AuthorizationDAOImpl() {

	}

	public void finalize() throws Throwable {

	}

	

	

	


	/**
	 * @param permission
	 * @param subject
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) {
		return false;
	}

	/**
	 * @param permission
	 * @param userName
	 *  
	 */
	public boolean checkPermission(AccessPermission permission, String userName) {
		return false;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#addUserToGroup(java.lang.String, java.lang.String)
	 */
	public void addUserToGroup(String groupId, String user) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignGroupRoleToProtectionGroup(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId,
			String groupId, String rolesId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignPrivilegesToRole(java.lang.String[], java.lang.String)
	 */
	public void assignPrivilegesToRole(String[] privilegesName, String roleName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectName,
			String[] protectionElementAttributeName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignProtectionElements(java.lang.String, java.lang.String[])
	 */
	public void assignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectNames) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#assignUserRoleToProtectionGroup(java.lang.String, java.lang.String[], java.lang.String)
	 */
	public void assignUserRoleToProtectionGroup(String userId,
			String[] rolesId, String protectionGroupId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(gov.nih.nci.security.authorization.jaas.AccessPermission, gov.nih.nci.security.authorization.domainobjects.User)
	 */
	public boolean checkPermission(AccessPermission permission, User user) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String attributeId, String privilegeName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#checkPermission(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean checkPermission(String userName, String objectId,
			String privilegeName) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createGroup(gov.nih.nci.security.authorization.domainobjects.Group)
	 */
	public void createGroup(Group group) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
	 */
	public void createPrivilege(Privilege privilege) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
	 */
	public void createProtectionElement(ProtectionElement protectionElement) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#createRole(gov.nih.nci.security.authorization.domainobjects.Role)
	 */
	public void createRole(Role role) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public void deAssignProtectionElements(String protectionGroupName,
			String[] protectionElementObjectNames,
			String[] protectionElementAttributeNames) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#deAssignProtectionElements(java.lang.String[], java.lang.String)
	 */
	public void deAssignProtectionElements(
			String[] protectionElementObjectNames, String protectionGroupName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getObjects(gov.nih.nci.security.dao.SearchCriteria)
	 */
	public Set getObjects(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrincipals(java.lang.String)
	 */
	public Principal[] getPrincipals(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getPrivilege(java.lang.String)
	 */
	public Privilege getPrivilege(String privilegeId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionElement(java.lang.String)
	 */
	public ProtectionElement getProtectionElement(String objectId) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getProtectionGroup(java.lang.String)
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getRole(java.lang.String)
	 */
	public Role getRole(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#getUser(java.lang.String)
	 */
	public User getUser(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyGroup(gov.nih.nci.security.authorization.domainobjects.Group)
	 */
	public void modifyGroup(Group group) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyPrivilege(gov.nih.nci.security.authorization.domainobjects.Privilege)
	 */
	public void modifyPrivilege(Privilege privilege) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyProtectionGroup(gov.nih.nci.security.authorization.domainobjects.ProtectionGroup)
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#modifyRole(gov.nih.nci.security.authorization.domainobjects.Role)
	 */
	public void modifyRole(Role role) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroup(java.lang.String)
	 */
	public void removeGroup(String groupId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupFromProtectionGroup(java.lang.String, java.lang.String)
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId,
			String groupId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeGroupRoleFromProtectionGroup(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId,
			String groupId, String[] roleId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removePrivilege(java.lang.String)
	 */
	public void removePrivilege(String privilegeId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removePrivilegesFromRole(java.lang.String, java.lang.String[])
	 */
	public void removePrivilegesFromRole(String role, String[] privilegeName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeProtectionElement(gov.nih.nci.security.authorization.domainobjects.ProtectionElement)
	 */
	public void removeProtectionElement(ProtectionElement element) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeProtectionGroup(java.lang.String)
	 */
	public void removeProtectionGroup(String protectionGroupName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeRole(java.lang.String)
	 */
	public void removeRole(String roleId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromGroup(java.lang.String, java.lang.String)
	 */
	public void removeUserFromGroup(String groupId, String userId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserFromProtectionGroup(java.lang.String, java.lang.String)
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId,
			String userId) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#removeUserRoleFromProtectionGroup(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName,
			String userName, String[] roles) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setOwnerForProtectionElement(String userName,
			String protectionElementObjectName,
			String protectionElementAttributeName) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.AuthorizationDAO#setOwnerForProtectionElement(java.lang.String, java.lang.String)
	 */
	public void setOwnerForProtectionElement(
			String protectionElementObjectName, String userName) {
		// TODO Auto-generated method stub

	}
}