package gov.nih.nci.security;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.jaas.AccessPermission;
import javax.security.auth.Subject;
import java.security.Principal;





/**
 * The CommonSecurityManager will be used by client application. Programmers don't
 * have to worry about the underlying implementation for authentication.
 * @created 16-Nov-2004 02:01:31 PM
 * @version 1.0
 */
public class CommonSecurityManager {

	/**
	 * authorizationManager is an instance of AuthorizationManager, whcih is used for
	 * runtime authorization needs.
	 */
	private AuthorizationManager authorizationManager;
	/**
	 * authenticationManager is of AuthenticationManager type , which is used to
	 * authnticate a user in context of an application.
	 */
	private AuthenticationManager authenticationManager;
	public SecurityServiceProvider m_SecurityServiceProvider;



	public void finalize() throws Throwable {

	}

	public CommonSecurityManager(){

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
	 * @param protectionGroupName
	 * @param protectionElementObjectName
	 * @param protectionElementAttributeName
	 * 
	 */
	public void assignProtectionElements(String protectionGroupName, String[] protectionElementObjectName, String[] protectionElementAttributeName){

	}

	/**
	 * @param protectionElementObjectName
	 * @param userName
	 * 
	 */
	public void setOwnerForProtectionElement(String protectionElementObjectName, String userName){

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
	 * @param permission
	 * @param user
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, User user){
		return false;
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
	 * @param permission
	 * @param userName
	 * 
	 */
	public boolean checkPermission(AccessPermission permission, String userName){
		return false;
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
	 * @param userName
	 * @param objectId
	 * @param privilegeName
	 * 
	 */
	public boolean checkPermission(String userName, String objectId, String privilegeName){
		return false;
	}

	/**
	 * @param userName
	 * 
	 */
	public Principal[] getPrincipals(String userName){
		return null;
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

}