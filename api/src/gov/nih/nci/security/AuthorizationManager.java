package gov.nih.nci.security;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import javax.security.auth.Subject;
import java.security.Principal;





/**
 * The AuthorizationManager interface facilitates with programatic authorization
 * needs. This interfcae mainly deals with runtime authorization needs. Only one
 * AuthorizationManager will exist for a application. In other words the
 * AuthorizationManager exist in the context of an application.
 * @version 1.0
 * @created 03-Dec-2004 1:17:48 AM
 */
public interface AuthorizationManager {

	/**
	 * @param loginName
	 * 
	 */
	public User getUser(String loginName);

	public ApplicationContext getApplicationContext();

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectName, String[] protectionElementAttributeName);

	/**
	 * @param protectionElementObjectName
	 * @param userName
	 * 
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectName, String userName);

	/**
	 * @param protectionElementObjectNames
	 * @param protectionGroupName
	 * 
	 */
	public void deAssignProtectionElements(String[] protectionElementObjectNames, String protectionGroupName);

	/**
	 * @param protectionElement
	 * 
	 */
	public void createProtectionElement(ProtectionElement protectionElement);

	/**
	 * @param permission
	 * @param user
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, User user);

	/**
	 * @param permission
	 * @param subject
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, Subject subject);

	/**
	 * @param permission
	 * @param userName
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, String userName);

	/**
	 * @param userName
	 * @param objectId
	 * @param attributeId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String attributeId, String privilegeName);

	/**
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName);

	/**
	 * @param userName
	 * 
	 */
	public Principal[] getPrincipals(String userName);

	/**
	 * Returns the protection element for the passed object id
	 * @param objectId
	 * 
	 */
	public ProtectionElement getProtectionElement(String objectId);

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames);

	/**
	 * @param userName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void setOwnerForProtectionElement(String userName, String protectionElementObjectName, String protectionElementAttributeName);

	/**
	 * @param protectionGroupName
	 * @param protectionElementObjectNames
	 * @param protectionElementAttributeNames
	 * 
	 */
	public void deAssignProtectionElements(String protectionGroupName, String[] protectionElementObjectNames, String[] protectionElementAttributeNames);

	/**
	 * @param applicationContextName
	 * 
	 */
	public void initialize(String applicationContextName);

}

