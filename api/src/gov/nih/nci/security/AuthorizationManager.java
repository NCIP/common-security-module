package gov.nih.nci.security;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */

import gov.nih.nci.security.authorization.ObjectPrivilegeMap;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.SecurityServiceProvider;

import java.net.URL;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;





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
	 * Assigns Owners for a Protection Element. It retrieves the Protection Element from the database for the passed Object Id
	 * The retrieves the User object for the list of USer Names passed. It then associated the Users as owners to the 
	 * Protection Element
	 * @param protectionElementObjectId The Object Id of the Protection Element to which the Owners are to be assigned
	 * @param userNames The list of User names which are to be assigned as owners to the Protection Element
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Users 
	 * for the given data or there are any errors in the actual assignment.
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectId, String[] userNames)throws CSTransactionException;

	/**
	 * Deassigns Owners for a Protection Element. It retrieves the Protection Element from the database for the passed Object Id
	 * The retrieves the User object for the list of USer Names passed. It then removes association of the Users as owners from the 
	 * Protection Element
	 * @param protectionElementObjectId The Object Id of the Protection Element to which the Owners are to be assigned
	 * @param userNames The list of User names which are to be removed as owners from the Protection Element
	 * @throws CSTransactionException If it isnt able to retrieve either the  Protection Element or Users 
	 * for the given data or there are any errors in the actual assignment.
	 */
	public void removeOwnerForProtectionElement(String protectionElementObjectId, String[] userNames)throws CSTransactionException;
	
	
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
	 * The method checks the permission for a {@link Subject} for a given {@link AccessPermission}. The {@link Subject}
	 * is nothing but the <code>JAAS</code> subject and the {@link AccessPermission} is collection of the 
	 * resource on the operation is to be performed and the actual operation itself.
	 * @param permission The collection of resource and the operation to be performed on it
	 * @param subject The <code>JAAS</code> representation of the user.
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject) throws CSException;

	/**
	 * The method checks the permission for a {@link User} for a given {@link AccessPermission}. The 
	 * {@link AccessPermission} is collection of the resource on the operation is to be performed and 
	 * the actual operation itself. The userName is used to to obtain the User object and then the check
	 * permission operation is performed to see if the user has the required access or not.
	 * @param permission The collection of resource and the operation to be performed on it
	 * @param userName The user name of the user which is trying to perform the operation
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermission(AccessPermission permission, String userName) throws CSException;

	/**
	 * The method checks the permission for a {@link User} for a given {@link ProtectionElement}. The 
	 * {@link ProtectionElement} is obtained using the object id and the attribute name both. 
	 * The userName is used to to obtain the User object. Then the check
	 * permission operation is performed to see if the user has the required access or not. If caching is enabled 
	 * for the user then the permissions are validated against the internal stored cache else the query is 
	 * fired against the database to check the permissions
	 * @param userName The user name of the user which is trying to perform the operation
	 * @param objectId The object id of the protection element on which the user wants to perform the operation
	 * @param attributeName The attribute of the protection element on which the user wants to perform the operation
	 * @param privilegeName The operation which the user wants to perform on the protection element
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermission(String userName, String objectId, String attributeName, String privilegeName) throws CSException;

	/**
	 * The method checks the permission for a {@link User} for a given {@link ProtectionElement}. The 
	 * {@link ProtectionElement} is obtained using the object id only. 
	 * The userName is used to to obtain the User object. Then the check
	 * permission operation is performed to see if the user has the required access or not. If caching is enabled 
	 * for the user then the permissions are validated against the internal stored cache else the query is 
	 * fired against the database to check the permissions
	 * @param userName The user name of the user which is trying to perform the operation
	 * @param objectId The object id of the protection element on which the user wants to perform the operation
	 * @param privilegeName The operation which the user wants to perform on the protection element
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName) throws CSException;

	/**
	 * The method checks the permission for a {@link Group} for a given {@link ProtectionElement}. The 
	 * {@link ProtectionElement} is obtained using the object id and the attribute name both. 
	 * The groupName is used to to obtain the Group object. Then the check
	 * permission operation is performed to see if the Group has the required access or not. If caching is enabled 
	 * for the group then the permissions are validated against the internal stored cache else the query is 
	 * fired against the database to check the permissions
	 * @param groupName The group name which is trying to perform the operation
	 * @param objectId The object id of the protection element on which the user wants to perform the operation
	 * @param attributeName The attribute of the protection element on which the user wants to perform the operation
	 * @param privilegeName The operation which the user wants to perform on the protection element
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermissionForGroup(String groupName, String objectId, String attributeName, String privilegeName) throws CSException;

	/**
	 * The method checks the permission for a {@link Group} for a given {@link ProtectionElement}. The 
	 * {@link ProtectionElement} is obtained using the object id only. 
	 * The userName is used to to obtain the Group object. Then the check
	 * permission operation is performed to see if the group has the required access or not. If caching is enabled 
	 * for the group then the permissions are validated against the internal stored cache else the query is 
	 * fired against the database to check the permissions
	 * @param groupName The group name which is trying to perform the operation
	 * @param objectId The object id of the protection element on which the user wants to perform the operation
	 * @param privilegeName The operation which the user wants to perform on the protection element
	 * 
	 * @return boolean Returns true if the user has permission to perform the operation on that particular resource
	 * @throws CSException If there are any errors while checking for permission
	 */
	public boolean checkPermissionForGroup(String groupName, String objectId, String privilegeName) throws CSException;
	
	/**
	 * The method returns a list of all the {@link Group} which has permission to perform 
	 * the passed {@link Privilege} for the passed {@link ProtectionElement}. This method queries 
	 * the database using the passed objectId and the privilege and obtains all the groups which have access over.
	 * @param objectId The object id of the protection element on which the operation is performed
	 * @param privilegeName The operation which is performed on the protection element
	 * 
	 * @return List Returns the list of {@link Group} which as access permission
	 * @throws CSException If there are any errors while retrieving the accessbile groups
	 */
	public List getAccessibleGroups(String objectId, String privilegeName) throws CSException;

	/**
	 * The method returns a list of all the {@link Group} which has permission to perform 
	 * the passed {@link Privilege} for the passed {@link ProtectionElement}. This method queries 
	 * the database using the passed objectId and the privilege and obtains all the groups which have access over.
	 * @param objectId The object id of the protection element on which the operation is performed
	 * @param attributeName The attribute of the protection element on  on which the operation is performed
	 * @param privilegeName The operation which is performed on the protection element
	 * 
	 * @return List Returns the list of {@link Group} which as access permission
	 * @throws CSException If there are any errors while retrieving the accessbile groups
	 */
	public List getAccessibleGroups(String objectId, String attributeName, String privilegeName) throws CSException;
	
	/**
	 * The method returns all the principals for the user.
	 * @param userName The user name whose principals we are trying to obtain
	 * 
	 * @return Principal[] The <code>JAAS</code> Principals for the given user
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
	 * The methods sets the ownership of a given {@link ProtectionElement} to the given {@link User}. The protectio element
	 * is obtained using the object id and attribute name passed and the user is retrieved using the user name passed
	 * @param userName The user name of the user who is to become the owner of the protection element
	 * @param protectionElementObjectId The object id of the protection element which is to be associated with the user
	 * @param protectionElementAttributeName The attribute name of the protection element which is to be associated with the user
	 * 
	 * @throws CSTransactionException If there were issues in either obtaining the {@link ProtectionElement} or the {@link User} or in the
	 * actual assignment of ownership
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException;
	
	/**
	 * The method removes the ownership of a given {@link ProtectionElement} to the given {@link User}. The Protection Element
	 * is obtained using the object id and attribute name passed and the User is retrieved using the user name passed
	 * @param userName The user name of the user who is to be removed as the owner of the protection element
	 * @param protectionElementObjectId The object id of the protection element which is to be deassociated with the user
	 * @param protectionElementAttributeName The attribute name of the protection element which is to be deassociated with the user
	 * 
	 * @throws CSTransactionException If there were issues in either obtaining the {@link ProtectionElement} or the {@link User} or in the
	 * actual assignment of ownership
	 */
	public void removeOwnerForProtectionElement(String userName, String protectionElementObjectId, String protectionElementAttributeName)throws CSTransactionException;

	

	/**
	 * Accepts the applicationContextName to initialize the AuthorizationManager
	 * @param applicationContextName The name of the application Context which is used to instantiate this Authorization Manager
	 * 
	 */
	public void initialize(String applicationContextName);

	/**
	 * Accepts the applicationContextName and the URL to the hibernate configuration file to initialize the AuthorizationManager
	 * @param applicationContextName The name of the application Context which is used to instantiate this Authorization Manager
	 * 
	 */
	public void initialize(String applicationContextName, URL url);
	
	/**
	 * This methods return a List of all the {@link ProtectionGroup} for the current application.
	 * @return List List of all the Protection Groups for the current application
	 */
	public java.util.List getProtectionGroups();
	
	/**
	 * This method returns the {@link ProtectionElement} for a given objectId and attributeName
	 * @param objectId The object id of the protection element to be obtained
	 * @param attributeName The attribute name of the protection element to be obtained
	 * @return ProtectionElement Returns the {@link ProtectionElement} if found else null
	 */
	public ProtectionElement getProtectionElement(String objectId,String attributeName);
	
	/**
	 *  The secure object method can be used only with objects which follow the java beans specifications.
	 *  This method assumes that Object passed has all the public getter and setter methods. The method checks
	 *  permission for every attribute of the object for the passed userName. If the user does not have read
	 *  permission on the few attributes, then they will appear as null.
	 * @param userName The user name for the User who is trying to access the object
	 * @param obj The Java Bean data object whose attribute needs to be protected.
	 * @return Object The mutated Java Bean object which is a copy of the original object. This object doesnt not contain
	 * values for the attributes on which the user doesnot have permission
	 * @throws CSException If there is any problem the checking for access permissions for securing the object
	 */
	public Object secureObject(String userName, Object obj) throws CSException;
	
	/**
	 * This methods works the same way as the <code>secureObject</code> except that it accepts an collection of Objects
	 * rather then single instance
	 * @param userName The user name for the User who is trying to access the object
	 * @param objects The collection Java Bean data objects whose attribute needs to be protected.
	 * @return Collection The collection mutated Java Bean objects which are a copy of the original objects. These objects doesnt not contain
	 * values for the attributes on which the user doesnot have permission.
	 * @throws CSException If there is any problem the checking for access permissions for securing the objects
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
	 * The method returns a Collection of {@link ObjectPrivilegeMap}. For every passed <code>ProtectionElement</code>,the 
	 * method looks for the privileges that this {@link User} have on the {@link ProtectionElement}
	 * @param userName The user name for the User who is trying to access the object
	 * @param protectionElements Collection of Protection Elements for which the access priveleges are to be retrieved for the given user
	 * @return Collection The collection of {@link ObjectPrivilegeMap}
	 * @throws CSException If there is any problem obtaining the access permissions for securing the objects
	 */
	public Collection getPrivilegeMap(String userName,Collection protectionElements) throws CSException;
	
	
	/**
	 * This method accepts the original as well as the mutated object. It then retrieved the Attribute level privilege using
	 * the object's name. Using this privilege access map it checks if the attributes on which the {@link User} doesnt have 
	 * access has been modified in the mutated object. If found then it replaces the value of the same back from the original
	 * object. Thus this methods doesnt allow modification of attributes of an object on which the user doesnt have access.
	 * This new object is returned which contains the changed values only for those attributes on which the User has access.
	 * @param userName The user name of the {@link User} which is trying to update the object
	 * @param originalObject The original data object as it was read from the data base
	 * @param mutatedObject The data object which contains the changes which the user has made
	 * @return The object which contains the changed values only for those attributes on which the User has access.
	 * @throws CSException If there is any problem obtaining the access permissions for securing the objects
	 */
	public Object secureUpdate(String userName, Object originalObject,Object mutatedObject) throws CSException;
	
	/**
	 * The methods checks if the given {@link User} is owner of the {@link ProtectionElement}. The {@link  User} object is
	 * obtained using the user name provided . The {@link ProtectionElement} is retrieved using the object id passed. Even though
	 * if the user has ownership on a particular attribute of the object then he has ownership on the entire object
	 * @param userName The user for which the ownership for the protection element is to be determined
	 * @param protectionElementObjectId The object id of the protection element for which the ownership is to be determined 
	 * @return True if the user has ownership on the protection element else False
	 */
	public boolean checkOwnership(String userName, String protectionElementObjectId);
	
	/**
	 * This method is used to set the Audit user info. This method should be used
	 * only if you are creating a new Authentication Manager for each user. Else
	 * just set the user info using the <code>UserInfoHelper</code> class directly 
	 * @param userName The name of the user accessing the Authentication Service
	 * @param sessionId The session id of the user trying to access the Authentication Service
	 */
	public void setAuditUserInfo(String userName, String sessionId);
	

	/**
	 * This method is used to indicate if encryption is enabled or not for user passwords
	 * stored in the database. 
	 *  
	 * @param isEncryptionEnabled boolean value 
	 */
	public void setEncryptionEnabled(boolean isEncryptionEnabled);

	
	/**
	 * This method returns the Application Object for which the manager is obtained. This 
	 * method uses the application Context name passed and retrieves the application object
	 * from the database.
	 * @param applicationContextName the name of the application which is to be retrieved from the database.
	 * @return Application object for the application context name using which this manager was obtained.
	 * @throws CSObjectNotFoundException 
	 */
	public Application getApplication(String applicationContextName) throws CSObjectNotFoundException;
	
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
	 * @throws CSTransactionException If there is any exception in assigning user role to the protection group
	 */
	public void assignUserRoleToProtectionGroup(String userId, String[] rolesId, String protectionGroupId)throws CSTransactionException;

	/**
	 * Removes the User and multiple Role from the Assigned ProtectionGroup
	 * 
	 * @param protectionGroupId String the id of the Protection Group from which the user Role is to be removed
	 * @param userId String The id of the user which is to be deassigned
	 * @param rolesId String[] List of the role ids which are to be removed from Protection Group
	 * @throws CSTransactionException If there is any exception in assigning user role to the protection group
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
	 * Assigns multiple Users to a single Group.
	 * The same method should be called for de-assigning or modifying the association
	 * @param groupId the Group to which the Users are to be assigned
	 * @param userIds The Users which are to be assigned to the Group
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignUsersToGroup(String groupId,String[] userIds)throws CSTransactionException;
	
	
	/**
	 * Assigns a User to a Group
	 *
	 * @param userName the User Name to which the Group will be assigned
	 * @param groupName The Group Name which is to be assigned to the User
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignUserToGroup(String userName,String groupName)throws CSTransactionException;


	/**
	 * Removes the User from the Group to which it is assigned
	 * @param groupId the group id of the group to which the user belongs
	 * @param userId the user id of the user which is to be removed
	 * 
	 * @throws CSTransactionException If there are any error in removal operation
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
	 * the attributes on which the search is to be based. Then instanstiate a new {@link gov.nih.nci.security.dao.RoleSearchCriteria} passing the 
	 * {@link Role}. Now pass this {@link gov.nih.nci.security.dao.RoleSearchCriteria} typecasted as {@link SearchCriteria} to this method.
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
	 * Assigns multiple Protection Element to a single Protection Group. This method is to be used if you want to group
	 * multiple Protection Element to a Protection Group.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionGroupId the Protection Group to which the protection elements are to be assigned
	 * @param protectionElementIds The Protection Element which are to be assigned to the Protection Group
	 * @throws CSTransactionException If there are any errors in the Assignment
	 */
	public void assignProtectionElements(String protectionGroupId,String[] protectionElementIds) throws CSTransactionException;
	
		
	/**
	 * Removes/ Deassigns multiple Protection Element from a single Protection Group. This method is to be used if you want to group
	 * multiple Protection Element from a Protection Group.
	 * The same method should be called for de-assigning or modifying the association
	 * @param protectionGroupId the Protection Group to which the protection elements are to be assigned
	 * @param protectionElementIds The Protection Elements which are to be deassigned to the Protection Group
	 * @throws CSTransactionException If there are any errors in the Deassignment
	 */
	public void removeProtectionElementsFromProtectionGroup(String protectionGroupId, String[] protectionElementIds) throws CSTransactionException;
	
		
	
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
	 * Returns the ProtectionElementPrivilegeContext object containing the list of associated protection elements with their privileges
	 * for the passed User. It also includes the Protection Element which are owned by the User
	 * @param userId the User whose Protection Element - Privilege context is to be obtained
	 * @return Set A list of the ProtectionElementPrivilegeContext objects for that passed user id
	 * @throws CSObjectNotFoundException If the user is not found
	 */
	public Set getProtectionElementPrivilegeContextForUser(String userId) throws CSObjectNotFoundException;
	
	/**
	 * Returns the ProtectionElementPrivilegeContext object containing the list of associated protection elements with their privileges
	 * for the passed Group
	 * @param groupId the Group whose Protection Element - Privilege context is to be obtained
	 * @return A list of the ProtectionElementPrivilegeContext objects for that passed group id
	 * @throws CSObjectNotFoundException If the group is not found
	 */
	public Set getProtectionElementPrivilegeContextForGroup(String groupId) throws CSObjectNotFoundException;
		
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
	 * Returns the Assigned Users for a particular Group. The Group is obtained from the Group Id passed
	 * @param groupId The id of the Group object whose associated Users are to be obtained
	 * 
	 * @return Set The list of the associated Users for the Group
	 * @throws CSObjectNotFoundException if the Group object is not found for the given id
	 */
	public Set getUsers(String groupId) throws CSObjectNotFoundException;
		
	
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
	 * @param parentProtectionGroupId The id for the parent protection group
	 * @param childProtectionGroupId The id for the child protection group
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
	 * @param applicationId The id of the Application object which is to be obtained
	 * 
	 * @return Application The Application object from the database for the passed Application id
	 * @throws CSObjectNotFoundException if the Application object is not found for the given id
	 */
	public Application getApplicationById(String applicationId) throws CSObjectNotFoundException;
	

	/**
	 * Assigns multiple Owners (User) to a single Protection Element. This method is to be used if you want to group
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

	
	/**
	 * This method returns the list of attributes of provided class name which are associated ot the user for the passed privilege. 
	 * These attributes are stored as Protection Elements in the CSM Schema with the object id holding the class name and 
	 * the attribute 
	 * the name of the attribute
	 * @param userName the user for which the attribute map is to be retrieved
	 * @param className the class whose attributes are to be obtained
	 * @param privilegeName the operation for which the list of the attributes which the user can access is to be obtained.
	 * @return list of attributes of the provided class name on which the user has access else an empty list
	 */
	public List getAttributeMap(String userName, String className, String privilegeName);
	
	/**
	 * This method returns the list of attributes of provided class name which are associated to the group for the passed privilege. 
	 * These attributes are stored as Protection Elements in the CSM Schema with the object id holding the class name and 
	 * the attribute 
	 * the name of the attribute
	 * @param groupName the group name for which the attribute map is to be retrieved
	 * @param className the class whose attributes are to be obtained
	 * @param privilegeName the operation for which the list of the attributes which the user can access is to be obtained.
	 * @return list of attributes of the provided class name on which the groups have access else an empty list
	 */
	public List getAttributeMapForGroup(String groupName, String className, String privilegeName);
	
	
	/**
	 * This method accepts a new filter clause and persists it in to the underlying database
	 * @param filterClause the filter clause which is to be persisted in the database. On successful creation
	 *        the id attribute will be populated with the database assigned primary key
	 * @throws CSTransactionException is thrown if there is an error in creating the Filter Clause
	 */
	public void createFilterClause(FilterClause filterClause) throws CSTransactionException;
	
	
	/**
	 * Returns the {@link FilterClause} Object for the passed Filter Clause Id
	 * @param filterClauseId The id of the Filter Clause which is to be retrieved
	 * @return The Filter Clause Ojbect from the database for the passed Filter Clause id
	 * @throws CSObjectNotFoundException if the Filter Clause Object is not found for the given id
	 */
	public FilterClause getFilterClauseById(String filterClauseId) throws CSObjectNotFoundException;
	
	
	/**
	 * This method modifies the data of an existing {@link FilterClause} object into the database
	 * @param filterClause The Filter Clause object containing the updated values which are to be persisted in the database 
	 * @throws CSTransactionException if there is any error in updation of the passed Filter Clause object
	 */
	public void modifyFilterClause(FilterClause filterClause) throws CSTransactionException;
	
	
	/**
	 * This method removes the Filter Clause object from the database for the passed id.
	 * @param filterClauseId the id of the Filter Clause object which is to be removed from the database 
	 * @throws CSTransactionException if there is any error in the deletion of the Filter CLause Ojbect
	 */
	public void removeFilterClause(String filterClauseId) throws CSTransactionException;
	
}

