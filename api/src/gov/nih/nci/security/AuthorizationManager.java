package gov.nih.nci.security;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.Set;

import gov.nih.nci.security.exceptions.*;





/**
 * 
 * The <code>AuthorizationManager</code> interface provides all the
 * authorization methods and services offered by the Common Security Module.
 * This interface defines the contract that any class which wants to acts as an
 * authorization manager should follow to be able to fit in the Common Security
 * Framework. It defines the methods which are required for the purpose of
 * authorizing a user against the configured authorization data. This
 * interface by default is implemented by the
 * {@link UserProvisioningManager}. If the client application wants to use its own
 * Authorization Class, then it should implement the
 * <code>AuthorizationManager</code> interface. Also an entry should be configured
 * in the <code>ApplicationServiceConfig</code> file against the Application
 * Context Name regsitering the class, which it wants to use, as shown below
 * <p>
 * <blockquote>
 * 
 * <pre>
 *  	&lt;application&gt;
 *	   		&lt;context-name&gt;
 *	   			FooApplication
 *	      	&lt;/context-name&gt;
 *			:
 *			:
 *	      	&lt;authorization&gt;
 *		      	&lt;authorization-provider-class&gt;
 *	     			com.Foo.AuthorizationManagerClass
 *	     		&lt;/authorization-provider-class&gt;
 *			&lt;/authorization&gt;
 *		&lt;/application&gt;
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * If the client application wants to use just the authorization service then it can
 * obtain the implementation of the <code>AuthorizationManager</code> interface from the 
 * {@link SecurityServiceProvider} class.
 * 
 * @author Vinay Kumar(Ekagra Software Technologies Ltd.) 
 * 
 */

public interface AuthorizationManager {

	/**
	 * This method returns the User object from the database for the passed User's Login Name. If no User is
	 * found then null is returned
	 * @param loginName The Login Name of the User which is to be obtained
	 * 
	 * @return User The User object from the database for the passed Login Name
	 */
	public User getUser(String loginName);

	/**
	 * Returns the {@link ApplicationContext} for which the AuthorizationManager is instantiated. This 
	 * ApplicationContext object contains the information about the application as well all the associated
	 * data for the application
	 * @return ApplicationContext The {@link ApplicationContext} object for which the AuthorizationManager is instantiated
	 */
	public ApplicationContext getApplicationContext();

	/**
	 * Assigns a ProtectionElement to the Protection Group. The Protection Element is first retrieved using the passed
	 * Object Id and Attribute Name from the database. Similarly the Protection Group is retrieved using the name passed.
	 * Then both of these entities are associated in the database. If there is any error in the association then a 
	 * {@link CSTransactionException} is thrown. These errors could be raised if it isnt able to retrieve either the 
	 * Protection Element or Protection Group for the given data or there are any errors in the actual assignment
	 * @param protectionGroupName The name of the Protection Group to which the Protection Element is to be associated
	 * @param protectionElementObjectId The object Id of the Protection Element to which the Protection Group is to be associated
	 * @param protectionElementAttributeName The attribute name of the Protection Element to which the Protection Group is to be associated
	 * 
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Protection Group 
	 * for the given data or there are any errors in the actual assignment.
	 */
	public void assignProtectionElement(String protectionGroupName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException;

	/**
	 * Assigns Owners for a Protection Elements. It retrieves the Protection Element from the database for the passed Object Id
	 * The retrieves the User object for the list of USer Names passed. It then associated the Users as owners to the 
	 * Protection Element
	 * @param protectionElementObjectId The Object Id of the Protection Element to which the Owners are to be assigned
	 * @param userNames The list of User names which are to be assigned as owners to the Protection Element
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Users 
	 * for the given data or there are any errors in the actual assignment.
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectId, String[] userNames)throws CSTransactionException;

	/**
	 * Deassigns a ProtectionElement from the Protection Group. The Protection Element is first retrieved using the passed
	 * Object Id from the database. Similarly the Protection Group is retrieved using the name passed.
	 * Then both of these entities are de-associated in the database. If there is any error in the de-association then a 
	 * {@link CSTransactionException} is thrown. These errors could be raised if it isnt able to retrieve either the 
	 * Protection Element or Protection Group for the given data or there are any errors in the actual deassignment
	 * @param protectionGroupName The name of the Protection Group from which the Protection Element is to be de-associated
	 * @param protectionElementObjectId The object Id of the Protection Element from which the Protection Group is to be de-associated
	 * 
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Protection Group 
	 * for the given data or there are any errors in the actual deassignment.
	 */
	public void deAssignProtectionElements(String protectionGroupName,String protectionElementObjectId)throws CSTransactionException;

	/**
	 * This method creates a new Protection Element in the database based on the data passed
	 * @param protectionElement the Protection Element object which is to be created
	 * 
	 * @throws CSTransactionException If there is any exception in creating the Protection Element
	 */
	public void createProtectionElement(ProtectionElement protectionElement)throws CSTransactionException;

	
	/**
	 * The method checks the permission for a {@link Subject} for a given {@link AccessPermission}
	 * @param permission
	 * @param subject
	 * 
	 * @return boolean
	 * @throws CSException
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) throws CSException;

	/**
	 * The method checks the permission for a user for a given {@link AccessPermission}
	 * 
	 * @param permission
	 * @param userName
	 * 
	 * @return boolean
	 * @throws CSException
	 */
	public boolean checkPermission(AccessPermission permission, String userName) throws CSException;

	/**
	 * The method checks if the user has passed privilege on the atribute for the passed object.
	 * @param userName
	 * @param objectId
	 * @param attributeName
	 * @param privilegeName
	 * 
	 * @return boolean
	 * @throws CSException
	 */
	public boolean checkPermission(String userName, String objectId, String attributeName, String privilegeName) throws CSException;

	/**
	 * This method verifies whether the User has the provided Privilege on the Protection Element.
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 * @return boolean
	 * @throws CSTransactionException
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName) throws CSException;

	/**
	 * The method returns all the principals for the user
	 *  The method return 
	 * @param userName
	 * 
	 * @return Principal[]
	 */
	public Principal[] getPrincipals(String userName);

	/**
	 * Retrieves the Protection Element object from the database for the passed Object Id
	 * @param objectId The object Id of the Protection Element which is to be retrieved from the Database
	 * 
	 * @return ProtectionElement The Protection Element object which is returned from the database for the passed Object Id
	 * @throws CSObjectNotFoundException if the Protection Element object is not found for the given object id
	 */
	public ProtectionElement getProtectionElement(String objectId)throws CSObjectNotFoundException;
	/**
	 * Returns the Protection Element object from the database for the passed Protection Element Id
	 * @param protectionElementId The id of the Protection Element object which is to be obtained
	 * 
	 * @return ProtectionElement The Protection Element object from the database for the passed Protection Element id
	 * @throws CSObjectNotFoundException if the Protection Element object is not found for the given id
	 */
	public ProtectionElement getProtectionElementById(String protectionElementId) throws CSObjectNotFoundException;

	/**
	 * Assigns a ProtectionElement to the Protection Group. The Protection Element is first retrieved using the passed
	 * Object Id from the database. Similarly the Protection Group is retrieved using the name passed.
	 * Then both of these entities are associated in the database. If there is any error in the association then a 
	 * {@link CSTransactionException} is thrown. These errors could be raised if it isnt able to retrieve either the 
	 * Protection Element or Protection Group for the given data or there are any errors in the actual assignment
	 * @param protectionGroupName The name of the Protection Group to which the Protection Element is to be associated
	 * @param protectionElementObjectId The object Id of the Protection Element to which the Protection Group is to be associated
	 * 
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Protection Group 
	 * for the given data or there are any errors in the actual assignment.
	 */
	public void assignProtectionElement(String protectionGroupName, String protectionElementObjectId)throws CSTransactionException;

	/**
	 * @param userName
	 * @param protectionElementObjectId
	 * @param protectionElementAttributeName
	 * 
	 * @throws CSTransactionException
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException;

	

	/**
	 * Accepts the applicationContextName to initialize the AuthorizationManager
	 * @param applicationContextName The name of the application Context which is used to instantiate this Authorization Manager
	 * 
	 */
	public void initialize(String applicationContextName);

	/**
	 * This methods return a List of all the protection groups
	 * @return List 
	 */
	public java.util.List getProtectionGroups();
	
	/**
	 * This method returns the ProtectionElement for a given objectId and attributeName
	 * @param objectId
	 * @param attributeName
	 * @return ProtectionElement
	 */
	public ProtectionElement getProtectionElement(String objectId,String attributeName);
	
	/**
	 *  The secure object method can be used only with objects which follow the java beans specifications.
	 *  This method assumes that Object passed has all the public getter and setter methods. The method checks
	 *  permission for every attribute of the object for the passed userName. If the user does not have read
	 *  permission on the few attributes, then they will appear as null.
	 * @param userName
	 * @param obj
	 * @return Object
	 * @throws CSException
	 */
	public Object secureObject(String userName, Object obj) throws CSException;
	
	/**
	 * The secureCollection method secures a collection of objects which follow java beans specifications.
	 * @param userName
	 * @param objects
	 * @return Collection
	 * @throws CSException
	 */
	public Collection secureCollection(String userName,Collection objects) throws CSException;
	
	/**
	 * Returns the Assigned Protection Groups for a particular Protection Element. The Protection Element is obtained from the Protection Element Id passed
	 * @param protectionElementId The id of the Protection Element object whose associated Protection Groups are to be obtained
	 * 
	 * @return Set The list of the associated Protection Groups for the User
	 * @throws CSObjectNotFoundException if the Protection Element object is not found for the given id
	 */
	public Set getProtectionGroups(String protectionElementId) throws CSObjectNotFoundException;
	
	/**
	 * The method return a Collection of <code>ObjectPrivilegeMap</code>. For every passed <code>ProtectionElement</code>,the 
	 * method looks for the privileges that this user have on the <code>ProtectionElement</code>
	 * @param userName
	 * @param protectionElements
	 * @return Collection
	 * @throws CSException
	 */
	public Collection getPrivilegeMap(String userName,Collection protectionElements) throws CSException;
	
	public Object secureUpdate(String userName, Object originalObject,Object mutatedObject) throws CSException;
	
	public boolean checkIsOwnerForProtectionElement(String userName,
			String protectionElementObjectId);
	
}

