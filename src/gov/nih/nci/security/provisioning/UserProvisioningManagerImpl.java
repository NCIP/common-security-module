package gov.nih.nci.security.provisioning;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.AuthorizationDAO;
import gov.nih.nci.security.dao.SearchCriteria;

import java.security.Principal;

import javax.security.auth.Subject;





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

	public UserProvisioningManagerImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * @param protectionGroup
	 * 
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup){

	}

	/**
	 * @param loginName
	 * 
	 */
	public User getUser(String loginName){
		return null;
	}

	public ApplicationContext getApplicationContext(){
		return null;
	}

	/**
	 * @param protectionGroup
	 * 
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup){

	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectName, String[] protectionElementAttributeName){

	}

	/**
	 * @param protectionGroupName
	 * 
	 */
	public void removeProtectionGroup(String protectionGroupName){

	}

	/**
	 * @param element
	 * 
	 */
	public void removeProtectionElement(ProtectionElement element){

	}

	/**
	 * @param protectionElementObjectName
	 * @param userName
	 * 
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectName, String userName){

	}

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId){

	}

	/**
	 * @param protectionElementObjectNames
	 * @param protectionGroupName
	 * 
	 */
	public void deAssignProtectionElements(String[] protectionElementObjectNames, String protectionGroupName){

	}

	/**
	 * @param protectionElement
	 * 
	 */
	public void createProtectionElement(ProtectionElement protectionElement){

	}

	/**
	 * @param protectionGroupName
	 * @param userName
	 * @param roles
	 * 
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupName, String userName, String[] roles){

	}

	/**
	 * @param permission
	 * @param user
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, User user){
		return false;
	}

	/**
	 * @param role
	 * 
	 */
	public void createRole(Role role){

	}

	/**
	 * @param permission
	 * @param subject
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject){
		return false;
	}

	/**
	 * @param role
	 * 
	 */
	public void modifyRole(Role role){

	}

	/**
	 * @param permission
	 * @param userName
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, String userName){
		return false;
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
	public void removeRole(String roleId){

	}

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String attributeId, String privilegeName){
		return false;
	}

	/**
	 * @param privilege
	 * 
	 */
	public void createPrivilege(Privilege privilege){

	}

	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName){
		return false;
	}

	/**
	 * @param privilege
	 * 
	 */
	public void modifyPrivilege(Privilege privilege){

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
	public void removePrivilege(String privilegeId){

	}

	/**
	 * @param privilegesName
	 * @param roleName
	 * 
	 */
	public void assignPrivilegesToRole(String[] privilegesName, String roleName){

	}

	/**
	 * Returns the protection element for the passed object id
	 * @param objectId
	 * 
	 */
	public ProtectionElement getProtectionElement(String objectId){
		return null;
	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames){

	}

	/**
	 * @param role
	 * @param privilegeName
	 * 
	 */
	public void removePrivilegesFromRole(String role, String[] privilegeName){

	}

	/**
	 * @param group
	 * 
	 */
	public void createGroup(Group group){

	}

	/**
	 * @param userName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectName, String protectionElementAttributeName){

	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * @param protectionElementAttributeNames
	 * 
	 */
	public void deAssignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames, String[] protectionElementAttributeNames){

	}

	/**
	 * @param groupId
	 * 
	 */
	public void removeGroup(String groupId){

	}

	/**
	 * @param group
	 * 
	 */
	public void modifyGroup(Group group){

	}

	/**
	 * @param groupId
	 * @param user
	 * 
	 */
	public void addUserToGroup(String groupId, String user){

	}

	/**
	 * @param groupId
	 * @param userId
	 * 
	 */
	public void removeUserFromGroup(String groupId, String userId){

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId){

	}

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 */
	public Privilege getPrivilege(String privilegeId){
		return null;
	}

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId){

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId){

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId){

	}

	/**
	 * @param protectionGroupName
	 * 
	 */
	public ProtectionGroup getProtectionGroup(String protectionGroupName){
		return null;
	}

	/**
	 * @param roleName
	 * 
	 */
	public Role getRole(String roleName){
		return null;
	}

	/**
	 * @param searchCriteria
	 * 
	 */
	public java.util.Set getObjects(SearchCriteria searchCriteria){
		return null;
	}

}