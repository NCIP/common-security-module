package gov.nih.nci.security.provisioning;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.AuthorizationDAO;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.security.auth.Subject;

import net.sf.hibernate.SessionFactory;





/**
 * This class is an implementation of UserProvisioningManager. All the methods
 * from UserProvisioingManager are implemented here.
 * @version 1.0
 * @author modik
 */
public class UserProvisioningManagerImpl implements UserProvisioningManager {


	/**
	 * authorizationDAO is an instance of AuthorizationDAO , which is used for
	 * peristence.
	 */
	private AuthorizationDAO authorizationDAO;

	/**
	 * The application context object for the given application
	 * peristence.
	 */
	private ApplicationContext applicationContext;
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.UserProvisioningManager#addUserToGroup(java.lang.String, java.lang.String)
	 */
	public void addUserToGroup(String groupId, String userId)
			throws CSTransactionException {
		// TODO Auto-generated method stub

	}

	/**
	 * Constructor for UserProvisioningManagerImpl.
	 * @param applicationContextName String
	 * @throws Exception
	 */
	public UserProvisioningManagerImpl(String applicationContextName) throws Exception{
		/**
		 *  Ultimately we have to use ApplicationSessionFactory class
		 *  to get appropriate sessionFcatory for a application.
		 */
		try{
		//SessionFactory sf = AuthorizationDAOSessionFactory.getHibernateSessionFactory(applicationContextName);
		SessionFactory sf = ApplicationSessionFactory.getSessionFactory(applicationContextName);
		AuthorizationDAOImpl adi = new AuthorizationDAOImpl(sf,applicationContextName);	
		authorizationDAO = (AuthorizationDAO)(adi);
		}catch(Exception ex){
			throw ex;
		}

	}

	/**
	 * Method finalize.
	 * @throws Throwable
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * @param protectionGroup
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createProtectionGroup(ProtectionGroup)
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup) throws CSTransactionException{
		protectionGroup.setApplication(authorizationDAO.getApplication());
		protectionGroup.setUpdateDate(new Date());
		authorizationDAO.createObject(protectionGroup);
		
		//authorizationDAO.createProtectionGroup(protectionGroup);

	}

	/**
	 * @param loginName
	 * 
	 * @return User
	 * @see gov.nih.nci.security.AuthorizationManager#getUser(String)
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
	

	/**
	 * @param protectionGroup
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyProtectionGroup(ProtectionGroup)
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException{
		
		protectionGroup.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(protectionGroup);
		

	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId String
	 * @param protectionElementAttributeName
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#assignProtectionElements(String, String, String)
	 */
	public void assignProtectionElement(String protectionGroupName, String protectionElementObjectId, String protectionElementAttributeName) throws CSTransactionException{
            
		authorizationDAO.assignProtectionElement(protectionGroupName,protectionElementObjectId);
	}
	/**
	 * @param protectionGroupId String
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionGroup(String)
	 */
	public void removeProtectionGroup(String protectionGroupId) throws CSTransactionException{
		ProtectionGroup protectionGroup = new ProtectionGroup();
		protectionGroup.setProtectionGroupId(new Long(protectionGroupId));
		protectionGroup.setProtectionGroupName("XX");
		protectionGroup.setProtectionGroupDescription("XX");
		protectionGroup.setUpdateDate(new Date());
		authorizationDAO.removeObject(protectionGroup);
		
    }

	/**
	 * @param protectionElementId String
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionElement(String)
	 */
	public void removeProtectionElement(String  protectionElementId) throws CSTransactionException{
            
		ProtectionElement protectionElement = new ProtectionElement();
		protectionElement.setProtectionElementId(new Long(protectionElementId));
		protectionElement.setProtectionElementName("XX");
		protectionElement.setObjectId("XX");
		protectionElement.setAttribute("XX");
		protectionElement.setUpdateDate(new Date());
		authorizationDAO.removeObject(protectionElement);
	}

	/**
	 * @param protectionElementObjectId String
	 * @param userNames String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#setOwnerForProtectionElement(String, String[])
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectId, String[] userNames)throws CSTransactionException{

		authorizationDAO.setOwnerForProtectionElement(protectionElementObjectId,userNames);
	}

	/**
	 * @param userId
	 * @param rolesId
	 * @param protectionGroupId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignUserRoleToProtectionGroup(String, String[], String)
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException{
           
		authorizationDAO.assignUserRoleToProtectionGroup(userId,rolesId,protectionGroupId);
	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#deAssignProtectionElements(String, String)
	 */
	public void deAssignProtectionElements(String protectionGroupName,String protectionElementObjectId)throws CSTransactionException{
		;
	}


	/**
	 * @param protectionElement
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#createProtectionElement(ProtectionElement)
	 */
	public void createProtectionElement(ProtectionElement protectionElement)throws CSTransactionException{
		protectionElement.setApplication(authorizationDAO.getApplication());
		protectionElement.setUpdateDate(new Date());
		authorizationDAO.createObject(protectionElement);
		
		//authorizationDAO.createProtectionElement(protectionElement);
	}

	/**
	 * @param protectionGroupId String
	 * @param userId String
	 * @param rolesId String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeUserRoleFromProtectionGroup(String, String, String[])
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId, String userId, String[] rolesId)throws CSTransactionException{
		authorizationDAO.removeUserRoleFromProtectionGroup(protectionGroupId,userId,rolesId);
	}

	

	/**
	 * @param role
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createRole(Role)
	 */
	public void createRole(Role role)throws CSTransactionException{
		role.setApplication(authorizationDAO.getApplication());
		role.setUpdateDate(new Date());
		authorizationDAO.createObject(role);
		
		//authorizationDAO.createRole(role);

	}

	/**
	 * @param permission
	 * @param subject
	 * 
	 * @return boolean
	 * @throws CSException
	 * @see gov.nih.nci.security.AuthorizationManager#checkPermission(AccessPermission, Subject)
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) throws CSException{
	   return authorizationDAO.checkPermission(permission,subject);
	}

	/**
	 * @param role
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyRole(Role)
	 */
	public void modifyRole(Role role) throws CSTransactionException{
		role.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(role);
	}

	/**
	 * @param permission
	 * @param userName
	 * 
	 * @return boolean
	 * @see gov.nih.nci.security.AuthorizationManager#checkPermission(AccessPermission, String)
	 */
	public boolean checkPermission(AccessPermission permission, String userName) throws CSException{
		return authorizationDAO.checkPermission(permission,userName);
	}

	/**
	 * @param applicationContextName
	 * 
	 * @see gov.nih.nci.security.AuthorizationManager#initialize(String)
	 */
	public void initialize(String applicationContextName){
        
	}

	/**
	 * @param roleId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeRole(String)
	 */
	public void removeRole(String roleId) throws CSTransactionException{
		Role r = new Role();
		r.setId(new Long(roleId));
		r.setName("XX");
		r.setDesc("XX");
		r.setUpdateDate(new Date());
		authorizationDAO.removeObject(r);
		
	}

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 * 
	 * @return boolean
	 * @see gov.nih.nci.security.AuthorizationManager#checkPermission(String, String, String, String)
	 */
	public boolean checkPermission(String userName, String objectId, String attributeId, String privilegeName)throws CSException{
		return authorizationDAO.checkPermission(userName,objectId,attributeId,privilegeName) ;
	}

	/**
	 * @param privilege
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createPrivilege(Privilege)
	 */
	public void createPrivilege(Privilege privilege) throws CSTransactionException{
		privilege.setUpdateDate(new Date());
		authorizationDAO.createObject(privilege);
		//authorizationDAO.createPrivilege(privilege);
	}

	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 * @return boolean
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#checkPermission(String, String, String)
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName) throws CSException{
		return authorizationDAO.checkPermission(userName,objectId,privilegeName);
	}

	/**
	 * @param privilege
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyPrivilege(Privilege)
	 */
	public void modifyPrivilege(Privilege privilege) throws CSTransactionException{
		privilege.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(privilege);
	}


	/**
	 * @param privilegeId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removePrivilege(String)
	 */
	public void removePrivilege(String privilegeId) throws CSTransactionException{
      
		Privilege p = new Privilege();
		p.setId(new Long(privilegeId));
		p.setDesc("XX");
		p.setName("XX");
		p.setUpdateDate(new Date());
		authorizationDAO.removeObject(p);
		
	}

	/**
	 * @param roleId
	 * @param privilegeIds
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignPrivilegesToRole(String, String[])
	 */
	public void assignPrivilegesToRole(String roleId,String[] privilegeIds)throws CSTransactionException{
		authorizationDAO.assignPrivilegesToRole(roleId,privilegeIds);
	}


	/**
	 * Returns the protection element for the passed object id
	 * @param objectId
	 * 
	 * @return ProtectionElement
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.AuthorizationManager#getProtectionElement(String)
	 */
	public ProtectionElement getProtectionElement(String objectId) throws CSObjectNotFoundException{
		return authorizationDAO.getProtectionElement(objectId);
	}
	
	/**
	 * Returns the protection element for the passed object id
	 * @param protectionElementId String
	 * @return ProtectionElement
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.AuthorizationManager#getProtectionElementById(String)
	 */
	public ProtectionElement getProtectionElementById(String protectionElementId) throws CSObjectNotFoundException{
		return (ProtectionElement)authorizationDAO.getObjectByPrimaryKey(ProtectionElement.class,protectionElementId);
		//authorizationDAO.getProtectionElement(protectionElementId);
	}

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#assignProtectionElements(String, String)
	 */
	public void assignProtectionElement(String protectionGroupName, String protectionElementObjectId)throws CSTransactionException{
            authorizationDAO.assignProtectionElement(protectionGroupName,protectionElementObjectId);
	}

	/**
	 * @param group
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createGroup(Group)
	 */
	public void createGroup(Group group)throws CSTransactionException{
		group.setApplication(authorizationDAO.getApplication());
		group.setUpdateDate(new Date());
		authorizationDAO.createObject(group);
		//authorizationDAO.createGroup(group);
	}

	/**
	 * @param userName
	 * @param protectionElementName String
	 * @param protectionElementAttributeName
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.AuthorizationManager#setOwnerForProtectionElement(String, String, String)
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException{
		authorizationDAO.setOwnerForProtectionElement( userName, protectionElementObjectId, protectionElementAttributeName );
	}

	

	/**
	 * @param groupId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeGroup(String)
	 */
	public void removeGroup(String groupId)throws CSTransactionException{
		Group group = new Group();
		group.setGroupId(new Long(groupId));
		group.setGroupName("XX");
		group.setGroupDesc("XX");
		group.setUpdateDate(new Date());    
		authorizationDAO.removeObject(group);
	}

	/**
	 * @param group
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyGroup(Group)
	 */
	public void modifyGroup(Group group)throws CSTransactionException{
		group.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(group);
	}

	/**
	 * @param userId
	 * 
	 * @param groupIds String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignGroupsToUser(String, String[])
	 */
	public void assignGroupsToUser(String userId,String[] groupIds)throws CSTransactionException{
        authorizationDAO.assignGroupsToUser(userId,groupIds);
	}

	/**
	 * @param groupId
	 * @param userId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeUserFromGroup(String, String)
	 */
	public void removeUserFromGroup(String groupId, String userId)throws CSTransactionException{
           authorizationDAO.removeUserFromGroup(groupId,userId);
	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param rolesId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignGroupRoleToProtectionGroup(String, String, String[])
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId[])throws CSTransactionException{
		authorizationDAO.assignGroupRoleToProtectionGroup( protectionGroupId, groupId, rolesId );
	}

	/**
	 * Returns the privilege for the passed name privilege id
	 * @param privilegeId
	 * 
	 * @return Privilege
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getPrivilegeById(String)
	 */
	public Privilege getPrivilegeById(String privilegeId)throws CSObjectNotFoundException{
		return (Privilege)authorizationDAO.getObjectByPrimaryKey(Privilege.class,privilegeId);
	}

	/**
	 * This method removes the user from a protection group irrespective of all the
	 * roles
	 * @param protectionGroupId
	 * @param userId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeUserFromProtectionGroup(String, String)
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId) throws CSTransactionException{
		authorizationDAO.removeUserFromProtectionGroup(protectionGroupId,userId);
	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * @param roleId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeGroupRoleFromProtectionGroup(String, String, String[])
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId) throws CSTransactionException{

	}

	/**
	 * @param protectionGroupId
	 * @param groupId
	 * 
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeGroupFromProtectionGroup(String, String)
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId) throws CSTransactionException{
		authorizationDAO.removeGroupFromProtectionGroup(protectionGroupId,groupId);
	}

	/**
	 * @param protectionGroupId
	 * 
	 * @return ProtectionGroup
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupById(String)
	 */
	public ProtectionGroup getProtectionGroupById(String protectionGroupId) throws CSObjectNotFoundException{
		return (ProtectionGroup)authorizationDAO.getObjectByPrimaryKey(ProtectionGroup.class,protectionGroupId);
	}

	/**
	 * @param roleId
	 * 
	 * @return Role
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getRoleById(String)
	 */
	public Role getRoleById(String roleId) throws CSObjectNotFoundException{
		return (Role)authorizationDAO.getObjectByPrimaryKey(Role.class,roleId);
	}
	
	/**
	 * @param roleId
	 * 
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getPrivileges(String)
	 */
	public Set getPrivileges(String roleId) throws CSObjectNotFoundException{
		return authorizationDAO.getPrivileges(roleId);
	}

	/**
	 * @param searchCriteria
	 * 
	 * @return java.util.List
	 * @see gov.nih.nci.security.UserProvisioningManager#getObjects(SearchCriteria)
	 */
	public java.util.List getObjects(SearchCriteria searchCriteria){
		return authorizationDAO.getObjects(searchCriteria);
	}
	/**
	 * Method createUser.
	 * @param user User
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createUser(User)
	 */
	public void createUser(User user) throws CSTransactionException{
		user.setUpdateDate(new Date());
		authorizationDAO.createObject(user);
		//authorizationDAO.createUser(user);
	}
	/**
	 * Method assignProtectionElements.
	 * @param protectionGroupId String
	 * @param protectionElementIds String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignProtectionElements(String, String[])
	 */
	public void assignProtectionElements(String protectionGroupId,String[] protectionElementIds) throws CSTransactionException{
		authorizationDAO.assignProtectionElements(protectionGroupId,protectionElementIds);
	}
	/**
	 * Method removeProtectionElementsFromProtectionGroup.
	 * @param protectionGroupId String
	 * @param protectionLementIds String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeProtectionElementsFromProtectionGroup(String, String[])
	 */
	public void removeProtectionElementsFromProtectionGroup(String protectionGroupId,String[] protectionLementIds) throws CSTransactionException{
		;
	}
	
	/**
	 * Method getProtectionGroupRoleContextForUser.
	 * @param userId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupRoleContextForUser(String)
	 */
	public Set getProtectionGroupRoleContextForUser(String userId) throws CSObjectNotFoundException{
		return authorizationDAO.getProtectionGroupRoleContextForUser(userId);
	}
	
	/**
	 * Method getProtectionGroupRoleContextForGroup.
	 * @param groupId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroupRoleContextForGroup(String)
	 */
	public Set getProtectionGroupRoleContextForGroup(String groupId) throws CSObjectNotFoundException{
		return authorizationDAO.getProtectionGroupRoleContextForGroup(groupId);
	}
	
	/**
	 * Method getGroupById.
	 * @param groupId String
	 * @return Group
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getGroupById(String)
	 */
	public Group getGroupById(String groupId) throws CSObjectNotFoundException{
		return (Group)authorizationDAO.getObjectByPrimaryKey(Group.class,groupId);
	}

	/**
	 * Method modifyProtectionElement.
	 * @param protectionElement ProtectionElement
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyProtectionElement(ProtectionElement)
	 */
	public void modifyProtectionElement(ProtectionElement protectionElement) throws CSTransactionException{
		protectionElement.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(protectionElement);
	}
	
	/**
	 * Method getUserById.
	 * @param userId String
	 * @return User
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getUserById(String)
	 */
	public User getUserById(String userId) throws CSObjectNotFoundException{
		return (User)authorizationDAO.getObjectByPrimaryKey(User.class,userId);
	}
	
	/**
	 * Method modifyUser.
	 * @param user User
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyUser(User)
	 */
	public void modifyUser(User user)throws CSTransactionException{
		user.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(user);
	}
	
	/**
	 * Method removeUser.
	 * @param userId String
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeUser(String)
	 */
	public void removeUser(String userId)throws CSTransactionException{
		try{
		User user = this.getUserById(userId);
		 authorizationDAO.removeObject(user);
		}catch(CSObjectNotFoundException ex){
			throw new CSTransactionException("Failed to find this user with userId:"+userId,ex);
		}
		
	}
	
	/**
	 * Method getGroups.
	 * @param userId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getGroups(String)
	 */
	public Set getGroups(String userId) throws CSObjectNotFoundException{
		return authorizationDAO.getGroups(userId);
	}
	
	/**
	 * Method getProtectionElements.
	 * @param protectionGroupId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionElements(String)
	 */
	public Set getProtectionElements(String protectionGroupId) throws CSObjectNotFoundException{
		return authorizationDAO.getProtectionElements(protectionGroupId);
	}
	
	/**
	 * Method getProtectionGroups.
	 * @param protectionElementId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getProtectionGroups(String)
	 */
	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException{
		return authorizationDAO.getProtectionGroups(protectionElementId);
	}
	
	/**
	 * Method assignToProtectionGroups.
	 * @param protectionElementId String
	 * @param protectionGroupIds String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignToProtectionGroups(String, String[])
	 */
	public void assignToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException{
		authorizationDAO.assignToProtectionGroups(protectionElementId,protectionGroupIds);
	}
	
	/**
	 * Method assignParentProtectionGroup.
	 * @param parentProtectionGroupId String
	 * @param childProtectionGroupId String
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignParentProtectionGroup(String, String)
	 */
	public void assignParentProtectionGroup(String parentProtectionGroupId,String childProtectionGroupId) throws CSTransactionException{
		authorizationDAO.assignParentProtectionGroup(parentProtectionGroupId,childProtectionGroupId);
	}
	/**
	 * Method createApplication.
	 * @param application Application
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#createApplication(Application)
	 */
	public void createApplication(Application application)throws CSTransactionException{
		application.setUpdateDate(new Date());
		authorizationDAO.createObject(application);
		
	}
	/**
	 * Method modifyApplication.
	 * @param application Application
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#modifyApplication(Application)
	 */
	public void modifyApplication(Application application)throws CSTransactionException{
		application.setUpdateDate(new java.util.Date());
		authorizationDAO.modifyObject(application);
	}
	/**
	 * Method removeApplication.
	 * @param applicationId String
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#removeApplication(String)
	 */
	public void removeApplication(String applicationId) throws CSTransactionException{
	        try{
			Application app = this.getApplicationById(applicationId);
			authorizationDAO.removeObject(app);
	        }catch(Exception ex){
	        	throw new CSTransactionException("Could not remove application",ex);
	        }
			
	}
	/**
	 * Method getApplicationById.
	 * @param applicationId String
	 * @return Application
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getApplicationById(String)
	 */
	public Application getApplicationById(String applicationId) throws CSObjectNotFoundException{
		return (Application)authorizationDAO.getObjectByPrimaryKey(Application.class,applicationId);
		//authorizationDAO.getProtectionElement(protectionElementId);
	}
	
	/**
	 * Method assignOwners.
	 * @param protectionElementId String
	 * @param userIds String[]
	 * @throws CSTransactionException
	 * @see gov.nih.nci.security.UserProvisioningManager#assignOwners(String, String[])
	 */
	public void assignOwners(String protectionElementId,String[] userIds) throws CSTransactionException{
		authorizationDAO.assignOwners(protectionElementId,userIds);
	}

	/**
	 * Method getOwners.
	 * @param protectionElementId String
	 * @return Set
	 * @throws CSObjectNotFoundException
	 * @see gov.nih.nci.security.UserProvisioningManager#getOwners(String)
	 */
	public Set getOwners(String protectionElementId) throws CSObjectNotFoundException{
		return authorizationDAO.getOwners(protectionElementId);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.AuthorizationManager#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		// TODO Auto-generated method stub
		return applicationContext;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.AuthorizationManager#getPrincipals(java.lang.String)
	 */
	public Principal[] getPrincipals(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public java.util.List getProtectionGroups(){
		ProtectionGroup pg = new ProtectionGroup();
		pg.setProtectionGroupName("%");
		SearchCriteria sc = new ProtectionGroupSearchCriteria(pg);
		return this.getObjects(sc);
	}
	
	public ProtectionElement getProtectionElement(String objectId,String attributeName){
		ProtectionElement result = null;
		ProtectionElement pe = new ProtectionElement();
		pe.setObjectId(objectId);
		pe.setAttribute(attributeName);
		SearchCriteria sc = new ProtectionElementSearchCriteria(pe);
		java.util.List list = this.getObjects(sc);
		if(list.size()!=0){
			result = (ProtectionElement)list.get(0);
		}
		return result;
	}
	
	public Object secureObject(String userName, Object obj) throws CSException{
		return authorizationDAO.secureObject(userName,obj);
	}
	
	public Collection secureCollection(String userName,Collection objects) throws CSException{
		return authorizationDAO.secureCollection(userName,objects);
	}
	
	public Collection getPrivilegeMap(String userName,Collection protectionElements) throws CSException{
		return authorizationDAO.getPrivilegeMap(userName,protectionElements);
	}
	
}
