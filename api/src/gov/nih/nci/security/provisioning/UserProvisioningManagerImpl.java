package gov.nih.nci.security.provisioning;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.*;

import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.AuthorizationDAO;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.dao.AuthorizationDAOSessionFactory;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.*;

import java.security.Principal;

import java.util.Set;


import javax.security.auth.Subject;

import net.sf.hibernate.SessionFactory;





/**
 * This class is an implementation of UserProvisioningManager. All the methods
 * from UserProvisioingManager are implemented here.
 * @created 16-Nov-2004 02:06:12 PM
 * @version 1.0
 */
public class UserProvisioningManagerImpl implements UserProvisioningManager {

	/**
	 * The applicationContext is an instance of ApplicationContext class. It has all
	 * the information and configuration for an application.
	 */
	private ApplicationContext applicationContext;
	/**
	 * authorizationDAO is an instance of AuthorizationDAO , which is used for
	 * peristence.
	 */
	private AuthorizationDAO authorizationDAO;

	public UserProvisioningManagerImpl(String applicationContextName){
		
		SessionFactory sf = AuthorizationDAOSessionFactory.getHibernateSessionFactory(applicationContextName);
		AuthorizationDAOImpl adi = new AuthorizationDAOImpl(sf,applicationContextName);	
		authorizationDAO = (AuthorizationDAO)(adi);

	}

	public void finalize() throws Throwable {

	}

	/**
	 * @param protectionGroup
	 * 
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup) throws CSTransactionException{
		
		authorizationDAO.createProtectionGroup(protectionGroup);

	}

	/**
	 * @param loginName
	 * 
	 */
	public User getUser(String loginName){
		return authorizationDAO.getUser(loginName);
	}
	
	/**
	 * @param authorizationDAO
	 * 
	 */
	public void setAuthorizationDAO(AuthorizationDAO authorizationDAO){
		this.authorizationDAO = authorizationDAO;
	}

	public ApplicationContext getApplicationContext(){
		return null;
	}
	

	/**
	 * @param protectionGroup
	 * 
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException{
		
		authorizationDAO.modifyProtectionGroup(protectionGroup);
		

	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectName, String[] protectionElementAttributeName) throws CSTransactionException{
            
		authorizationDAO.assignProtectionElements(protectionGroupName,protectionElementObjectName);
	}
	/**
	 * @param protectionGroupName
	 * 
	 */
	public void removeProtectionGroup(String protectionGroupName) throws CSTransactionException{
		authorizationDAO.removeProtectionGroup(protectionGroupName);
	}

	/**
	 * @param element
	 * 
	 */
	public void removeProtectionElement(ProtectionElement element) throws CSTransactionException{

		authorizationDAO.removeProtectionElement(element);
	}

	/**
	 * @param protectionElementObjectName
	 * @param userName
	 * 
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectName, String userName)throws CSTransactionException{

		authorizationDAO.setOwnerForProtectionElement(protectionElementObjectName,userName);
	}

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException{
           
		authorizationDAO.assignUserRoleToProtectionGroup(userId,rolesId,protectionGroupId);
	}

	/**
	 * @param protectionElementObjectNames
	 * @param protectionGroupName
	 * 
	 */
	public void deAssignProtectionElements(String[] protectionElementObjectNames, String protectionGroupName)throws CSTransactionException{

		authorizationDAO.deAssignProtectionElements(protectionElementObjectNames,protectionGroupName);
	}

	/**
	 * @param protectionElement
	 * 
	 */
	public void createProtectionElement(ProtectionElement protectionElement)throws CSTransactionException{
		authorizationDAO.createProtectionElement(protectionElement);
	}

	/**
	 * @param protectionGroupName
	 * @param userName
	 * @param roles
	 * 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName, String userName, String[] roles)throws CSTransactionException{
		authorizationDAO.removeUserRoleFromProtectionGroup(protectionGroupName,userName,roles);
	}

	/**
	 * @param permission
	 * @param user
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, User user){
		
		return authorizationDAO.checkPermission(permission,user);
	}

	/**
	 * @param role
	 * 
	 */
	public void createRole(Role role)throws CSTransactionException{
		
		authorizationDAO.createRole(role);

	}

	/**
	 * @param permission
	 * @param subject
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject){
	   return authorizationDAO.checkPermission(permission,subject);
	}

	/**
	 * @param role
	 * 
	 */
	public void modifyRole(Role role) throws CSTransactionException{
		authorizationDAO.modifyRole(role);
	}

	/**
	 * @param permission
	 * @param userName
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, String userName){
		return authorizationDAO.checkPermission(permission,userName);
	}

	/**
	 * @param applicationContextName
	 * 
	 */
	public void initialize(String applicationContextName){
        
	}

	/**
	 * @param roleId
	 * 
	 */
	public void removeRole(String roleId) throws CSTransactionException{
		authorizationDAO.removeRole(roleId);
	}

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String attributeId, String privilegeName){
		return authorizationDAO.checkPermission(userName,objectId,attributeId,privilegeName);
	}

	/**
	 * @param privilege
	 * 
	 */
	public void createPrivilege(Privilege privilege) throws CSTransactionException{
		authorizationDAO.createPrivilege(privilege);
	}

	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName){
		return authorizationDAO.checkPermission(userName,objectId,privilegeName);
	}

	/**
	 * @param privilege
	 * 
	 */
	public void modifyPrivilege(Privilege privilege) throws CSTransactionException{
		authorizationDAO.modifyPrivilege(privilege);
	}

	/**
	 * @param userName
	 * 
	 */
	public Principal[] getPrincipals(String userName){
		return null;
	}

	/**
	 * @param privilegeId
	 * 
	 */
	public void removePrivilege(String privilegeId) throws CSTransactionException{
      
		authorizationDAO.removePrivilege(privilegeId);
		
	}

	/**
	 * @param roleId
	 * @param privilegeIds
	 * 
	 */
	public void assignPrivilegesToRole(String roleId,String[] privilegeIds)throws CSTransactionException{
		authorizationDAO.assignPrivilegesToRole(roleId,privilegeIds);
	}


	/**
	 * Returns the protection element for the passed object id
	 * @param objectId
	 * 
	 */
	public ProtectionElement getProtectionElement(String objectId) throws CSObjectNotFoundException{
		return null;
	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames)throws CSTransactionException{

	}

	/**
	 * @param group
	 * 
	 */
	public void createGroup(Group group)throws CSTransactionException{
		authorizationDAO.createGroup(group);
	}

	/**
	 * @param userName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectName, String protectionElementAttributeName)throws CSTransactionException{

	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * @param protectionElementAttributeNames
	 * 
	 */
	public void deAssignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames, String[] protectionElementAttributeNames)throws CSTransactionException{

	}

	/**
	 * @param groupId
	 * 
	 */
	public void removeGroup(String groupId)throws CSTransactionException{

	}

	/**
	 * @param group
	 * 
	 */
	public void modifyGroup(Group group)throws CSTransactionException{

	}

	/**
	 * @param groupId
	 * @param user
	 * 
	 */
	public void addUserToGroup(String groupId, String user)throws CSTransactionException{

	}

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId)throws CSTransactionException{

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId)throws CSTransactionException{

	}

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 */
	public Privilege getPrivilege(String privilegeId)throws CSObjectNotFoundException{
		return authorizationDAO.getPrivilege(privilegeId);
	}

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId) throws CSTransactionException{

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId) throws CSTransactionException{

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId) throws CSTransactionException{

	}

	/**
	 * @param protectionGroupName
	 * 
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName) throws CSObjectNotFoundException{
		return null;
	}

	/**
	 * @param roleName
	 * 
	 */
	public Role getRole(String roleName) throws CSObjectNotFoundException{
		return null;
	}
	/**
	 * @param roleId
	 * 
	 */
	public Role getRoleById(String roleId) throws CSObjectNotFoundException{
		return authorizationDAO.getRole(new Long(roleId));
	}
	
	/**
	 * @param roleId
	 * 
	 */
	public Set getPrivileges(String roleId) throws CSObjectNotFoundException{
		return authorizationDAO.getPrivileges(roleId);
	}

	/**
	 * @param searchCriteria
	 * 
	 */
	public java.util.Set getObjects(SearchCriteria searchCriteria){
		return null;
	}
	public void createUser(User user) throws CSTransactionException{
		authorizationDAO.createUser(user);
	}
}
