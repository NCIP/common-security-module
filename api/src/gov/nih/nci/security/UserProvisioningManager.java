package gov.nih.nci.security;


import java.util.Set;

import gov.nih.nci.security.authorization.domainobjects.Application;
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
	 * This method creates a new Protection Group in the database based on the data passed
	 * @param protectionGroup the Protection Group object which is to be created
	 * 
	 * @throws CSTransactionException If there is any exception in creating the Protection Group
	 */
	public void createProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException;

	/**
	 * This method modifies the data for an existing Protection Group
	 * @param protectionGroup the protection grouop which is to be modified
	 * 
	 * @throws CSTransactionException If there is any exception in modifying the Protection Group
	 */
	public void modifyProtectionGroup(ProtectionGroup protectionGroup)throws CSTransactionException;

	/**
	 * Removes the Protection Group for which the id is passed from the database. This id is the auto generated 
	 * id provided to that Protection Group when it is created.
	 * @param protectionGroupId the primary key id of the Protection Group which is needed to be removed
	 * 
	 * @throws CSTransactionException If there is any exception in removing the Protection Group
	 */
	public void removeProtectionGroup(String protectionGroupId)throws CSTransactionException;

	/**
	 * Removes the Protection Element for which the id is passed from the database. This id is the auto generated 
	 * id provided to that Protection Element when it is created.
	 * @param protectionElementId the primary key id of the Protection Element which is needed to be removed
	 * 
	 * @throws CSTransactionException If there is any exception in removing the Protection Element
	 */
	public void removeProtectionElement(String protectionElementId)throws CSTransactionException;

	/**
	 * Assigns multiples roles to a Protection Group for a particular user. This method accepts a single user id and 
	 * Protection Group id and multiple role ids
	 * The same method should be called for de-assigning or modifying the association
	 * @param userId The id of the user which needs to be assigned the Protection Group and roles
	 * @param rolesId The ids of the roles which are to be assigned to the user for a Protection Group
	 * @param protectionGroupId The id of the protection Group which needs to be assigned to the group and roles
	 * 
	 * @throws CSTransactionException
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException;

	/**
	 * @param protectionGroupId String
	 * @param userId String
	 * @param rolesId String[]
	 * @throws CSTransactionException
	 */
	public void removeUserRoleFromProtectionGroup(String protectionGroupId, String userId, String[] rolesId)throws CSTransactionException;

	/**
	 * Creates an entry for a new role in the database based on the data passed
	 * @param role the role object that needs to be created in the database
	 * 
	 * @throws CSTransactionException if there is any exception in creating the role in the database
	 */
	public void createRole(Role role)throws CSTransactionException;

	/**
	 * Modifies an entry for an existing role in the database based on the data passed
	 * @param role the role object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the role in the database
	 */
	public void modifyRole(Role role)throws CSTransactionException;

	/**
	 * Removes the role object from the database for the role id passed. The role id is assigned to the role when 
	 * a new role is created in the database
	 * @param roleId the primary key id of the role which needs to be removed from the database
	 * 
	 * @throws CSTransactionException if there is any exception in removeing the role from the database
	 */
	public void removeRole(String roleId)throws CSTransactionException;

	/**
	 * Creates an entry for a new privilege in the database based on the data passed
	 * @param privilege the privilege object that needs to be created in the database
	 * 
	 * @throws CSTransactionException if there is any exception in creating the privilege in the database
	 */
	public void createPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * Modifies an entry for an existing privilege in the database based on the data passed
	 * @param privilege the privilege object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the privilege in the database
	 */
	public void modifyPrivilege(Privilege privilege)throws CSTransactionException;

	/**
	 * Removes the privilege object from the database for the privilege id passed. The privilege id is assigned to the role when 
	 * a new privilege is created in the database
	 * @param privilegeId the primary key id of the privilege which needs to be removed from the database
	 * 
	 * @throws CSTransactionException if there is any exception in removeing the privilege from the database
	 */
	public void removePrivilege(String privilegeId)throws CSTransactionException;

	
	/**
	 * Assigns multiple Privileges to a single Role.
	 * The same method should be called for de-assigning or modifying the association
	 * @param roleId the Role to which the Privileges are to be assigned
	 * @param privilegeIds The Privileges which are to be assigned to the Role
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignPrivilegesToRole(String roleId,String[] privilegeIds)throws CSTransactionException;

	/**
	 * Creates an entry for a new group in the database based on the data passed
	 * @param group the group object that needs to be created in the database
	 * 
	 * @throws CSTransactionException if there is any exception in creating the group in the database
	 */
	public void createGroup(Group group)throws CSTransactionException;

	/**
	 * Removes the group object from the database for the group id passed. The group id is assigned to the role when 
	 * a new group is created in the database
	 * @param groupId the primary key id of the group which needs to be removed from the database
	 * 
	 * @throws CSTransactionException if there is any exception in removeing the group from the database
	 */
	public void removeGroup(String groupId)throws CSTransactionException;

	/**
	 * Modifies an entry for an existing group in the database based on the data passed
	 * @param group the group object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the group in the database
	 */
	public void modifyGroup(Group group)throws CSTransactionException;

	/**
	 * Assigns multiple Groups to a single User.
	 * The same method should be called for de-assigning or modifying the association
	 * @param userId the User to which the Groups are to be assigned
	 * @param groupIds The Groups which are to be assigned to the User
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignGroupsToUser(String userId,String[] groupIds)throws CSTransactionException;

	/**
	 * @param groupId
	 * @param userId
	 * 
	 * @throws CSTransactionException
	 */
	public void removeUserFromGroup(String groupId, String userId)throws CSTransactionException;

	/**
	 * Assigns multiples roles to a Protection Group for a particular user. This method accepts a single user id and 
	 * Protection Group id and multiple role ids
	 * The same method should be called for de-assigning or modifying the association
	 * @param groupId The id of the group which needs to be assigned the Protection Group and roles
	 * @param rolesId The ids of the roles which are to be assigned to the group for a Protection Group
	 * @param protectionGroupId The id of the protection Group which needs to be assigned to the group and roles
	 * 
	 * @throws CSTransactionException if there is any error in the assignment operations
	 */
	public void assignGroupRoleToProtectionGroup(String protectionGroupId, String groupId, String rolesId[])throws CSTransactionException;

	/**
	 * Returns the Privilege object for the passed Privilege id
	 * @param privilegeId The id of the Privilege object which is to be obtained
	 * 
	 * @return Privilege The Privilege object from the database for the passed Privilege id
	 * @throws CSObjectNotFoundException if the Privilege object is not found for the given id
	 */
	public Privilege getPrivilegeById(String privilegeId) throws CSObjectNotFoundException;

	/**
	 * This method removes the user from a Protection Group irrespective of all the roles
	 * @param protectionGroupId the Protection Group which is to be deassigned from the user
	 * @param userId the user which is to be deassigned from the Protection Group
	 * 
	 * @throws CSTransactionException If there is any error in deassigning
	 */
	public void removeUserFromProtectionGroup(String protectionGroupId, String userId)throws CSTransactionException;

	/**
	 * @param protectionGroupId
	 * @param groupId 
	 * @param roleId
	 * 
	 * @throws CSTransactionException
	 */
	public void removeGroupRoleFromProtectionGroup(String protectionGroupId, String groupId, String[] roleId)throws CSTransactionException;

	/**
	 * This method removes the group from a Protection Group irrespective of all the roles
     * @param protectionGroupId the Protection Group which is to be deassigned from the group
	 * @param groupId the group which is to be deassigned from the Protection Group
	 * 
	 * @throws CSTransactionException If there is any error in deassigning
	 */
	public void removeGroupFromProtectionGroup(String protectionGroupId, String groupId)throws CSTransactionException;

	/**
	 * Returns the Role object for the passed Role id
	 * @param roleId The id of the Role object which is to be obtained
	 * 
	 * @return Role The Role object from the database for the passed Role id
	 * @throws CSObjectNotFoundException if the Role object is not found for the given id
	 */
	public Role getRoleById(String roleId) throws CSObjectNotFoundException;
	/**
	 * Returns the Assigned Privileges for a particular Role. The Role is obtained from the Role Id passed
	 * @param roleId The id of the Role object whose associated Privileges are to be obtained
	 * 
	 * @return Set The list of the associated Priviledges for the Role
	 * @throws CSObjectNotFoundException if the Role object is not found for the given id
	 */
	public Set getPrivileges(String roleId) throws CSObjectNotFoundException;

	/**
	 * This method accepts an instance of the {@link SearchCriteria} object and uses the same to Query the database.
	 * The calling application should instantiate appropriate instance of the SearchCriteria. For e.g. In order to
	 * search for a {@link Role} The client application first has to create an instance of the {@link Role}, then set
	 * the attributes on which the search is to be based. Then instanstiate a new {@link RoleSearchCriteria} passing the 
	 * {@link Role}. Now pass this {@link RoleSearchCriteria} typecasted as {@link SearchCriteria} to this method.
	 * This method returns List of the corresponding objects which are returned from the database for the Search Criteria. These objects
	 * should be then type casted. In the e.g. This method returns a List of {@link Role} Objects. This method also returns
	 * message from the back end as part of the {@link SearchCriteria} object which is passed as parameter. This message 
	 * can be obtained by calling the <code>getMessage</code> method of {@link SearchCriteria}
	 * @param searchCriteria SearchCriteria The search criteria object which contains the search parameters which are
	 * to be used for querying against the database.
	 * @return java.util.List Corresponding Objects which are returned from database as the result of search.
	 */
	public java.util.List getObjects(SearchCriteria searchCriteria);
	
	/**
	 * This method creates a new User in the database based on the data passed
	 * @param user the User object which is to be created
	 * 
	 * @throws CSTransactionException If there is any exception in creating the User
	 */
	public void createUser(User user) throws  CSTransactionException;
	
	/**
	 * Returns the Protection Group object for the passed Protection Group id
	 * @param protectionGroupId The id of the Protection Group object which is to be obtained
	 * 
	 * @return ProtectionGroup The Protection Group object from the database for the passed Protection Group id
	 * @throws CSObjectNotFoundException if the Protection Group object is not found for the given id
	 */
	public ProtectionGroup getProtectionGroupById(String protectionGroupId) throws CSObjectNotFoundException;
	
	/**
	 * Assigns multiple Protection Element to a single Protection Group. This method is to be used if u want to group
	 * multiple Protection Element to a Protection Group.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionGroupId the Protection Group to which the protection elements are to be assigned
	 * @param protectionElementIds The Protection Element which are to be assigned to the Protection Group
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignProtectionElements(String protectionGroupId,String[] protectionElementIds) throws CSTransactionException;
	
	/**
	 * Method removeProtectionElementsFromProtectionGroup.
	 * @param protectionGroupId String
	 * @param protectionLementIds String[]
	 * @throws CSTransactionException
	 */
	public void removeProtectionElementsFromProtectionGroup(String protectionGroupId,String[] protectionLementIds) throws CSTransactionException;
	
	/**
	 * Returns the ProtectionGroupRoleContext object containing the list of associated protection groups with their roles
	 * for the passed User
	 * @param userId the User whose Protection Group - Role context is to be obtained
	 * @return Set A list of the ProtectionGroupRoleContext objects for that passed user id
	 * @throws CSObjectNotFoundException If the user is not found
	 */
	public Set getProtectionGroupRoleContextForUser(String userId) throws CSObjectNotFoundException;
	
	/**
	 * Returns the ProtectionGroupRoleContext object containing the list of associated protection groups with their roles
	 * for the passed Group
	 * @param groupId the Group whose Protection Group - Role context is to be obtained
	 * @return A list of the ProtectionGroupRoleContext objects for that passed group id
	 * @throws CSObjectNotFoundException If the group is not found
	 */
	public Set getProtectionGroupRoleContextForGroup(String groupId) throws CSObjectNotFoundException;
	
	/**
	 * Returns the Group object for the passed Group id
	 * @param groupId The id of the Group object which is to be obtained
	 * 
	 * @return Group The Group object from the database for the passed Group id
	 * @throws CSObjectNotFoundException if the Group object is not found for the given id
	 */
	public Group getGroupById(String groupId) throws CSObjectNotFoundException;
	
	/**
	 * Modifies an entry for an existing Protection Element in the database based on the data passed
	 * @param protectionElement the Protection Element object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the Protection Element in the database
	 */
	public void modifyProtectionElement(ProtectionElement protectionElement) throws CSTransactionException;

	/**
	 * Returns the User object for the passed User id
	 * @param userId The id of the User object which is to be obtained
	 * 
	 * @return User The User object from the database for the passed User id
	 * @throws CSObjectNotFoundException if the User object is not found for the given id
	 */
	public User getUserById(String userId) throws CSObjectNotFoundException;
	
	/**
	 * Modifies an entry for an existing User in the database based on the data passed
	 * @param user the User object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the User in the database
	 */
	public void modifyUser(User user)throws CSTransactionException;
	
	/**
	 * Removes the User object from the database for the User id passed. The User id is assigned to the User when 
	 * a new User is created in the database
	 * @param userId the primary key id of the User which needs to be removed from the database
	 * 
	 * @throws CSTransactionException if there is any exception in removeing the User from the database
	 */
	public void removeUser(String userId)throws CSTransactionException;
	
	/**
	 * Returns the Assigned Groups for a particular User. The User is obtained from the User Id passed
	 * @param userId The id of the User object whose associated Groups are to be obtained
	 * 
	 * @return Set The list of the associated Groups for the User
	 * @throws CSObjectNotFoundException if the User object is not found for the given id
	 */
	public Set getGroups(String userId) throws CSObjectNotFoundException;
	
	/**
	 * Returns the Assigned Protection Elements for a particular Protection Group. The Protection Group is obtained from the Protection Group Id passed
	 * @param protectionGroupId The id of the Protection Group object whose associated Protection Element are to be obtained
	 * 
	 * @return Set The list of the associated Protection Elements for the User
	 * @throws CSObjectNotFoundException if the Protection Group object is not found for the given id
	 */
	public Set getProtectionElements(String protectionGroupId) throws CSObjectNotFoundException;
	
	/**
	 * Returns the Assigned Protection Groups for a particular Protection Element. The Protection Element is obtained from the Protection Element Id passed
	 * @param protectionElementId The id of the Protection Element object whose associated Protection Groups are to be obtained
	 * 
	 * @return Set The list of the associated Protection Groups for the User
	 * @throws CSObjectNotFoundException if the Protection Element object is not found for the given id
	 */
	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException;
	
	/**
	 * Assigns multiple Protection Groups to a single Protection Element.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionElementId the Protection Element to which the protection Groups are to be assigned
	 * @param protectionGroupIds The Protection Group which are to be assigned to the Protection Element
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignToProtectionGroups(String protectionElementId,String[] protectionGroupIds) throws CSTransactionException;
	
	/**
	 * Assigns a Protection Group to another Protection Group as a parent.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionElementId the Protection Element to which the protection Groups are to be assigned
	 * @param protectionGroupIds The Protection Group which are to be assigned to the Protection Element
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignParentProtectionGroup(String parentProtectionGroupId,String childProtectionGroupId) throws CSTransactionException;
	
	/**
	 * This method creates a new Application in the database based on the data passed
	 * @param application the Application object which is to be created
	 * 
	 * @throws CSTransactionException If there is any exception in creating the Application
	 */
	public void createApplication(Application application)throws CSTransactionException;

	/**
	 * Modifies an entry for an existing Application in the database based on the data passed
	 * @param application the Application object that needs to be modified in the database
	 * 
	 * @throws CSTransactionException if there is any exception in modifying the Application in the database
	 */
	public void modifyApplication(Application application)throws CSTransactionException;
	/**
	 * Removes the Application object from the database for the Application id passed. The Application id is assigned to the Application when 
	 * a new Application is created in the database
	 * @param applicationId the primary key id of the Application which needs to be removed from the database
	 * 
	 * @throws CSTransactionException if there is any exception in removeing the Application from the database
	 */
	public void removeApplication(String applicationId) throws CSTransactionException;

	/**
	 * Returns the Application object for the passed Application id
	 * @param appicationId The id of the Application object which is to be obtained
	 * 
	 * @return Application The Application object from the database for the passed Application id
	 * @throws CSObjectNotFoundException if the Application object is not found for the given id
	 */
	public Application getApplicationById(String applicationId) throws CSObjectNotFoundException;
	

	/**
	 * Assigns multiple Owners (User) to a single Protection Element. This method is to be used if u want to group
	 * multiple Owners to a Protection Element.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionElementId the Protection Element to which the Owners(User) are to be assigned
	 * @param userIds The Owners (Users) which are to be associated to the Protection Element
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignOwners(String protectionElementId,String[] userIds) throws CSTransactionException;

	/**
	 * Returns the Assigned Owners (Users) for a particular Protection Elements. The Protection Element is obtained from the Protection Element Id passed
	 * @param protectionElementId The id of the Protection Element object whose associated Owners (Users) are to be obtained
	 * 
	 * @return Set The list of the associated Owners (Users) for the Protection Element
	 * @throws CSObjectNotFoundException if the Protection Element object is not found for the given id
	 */
	public Set getOwners(String protectionElementId) throws CSObjectNotFoundException;
	
}

