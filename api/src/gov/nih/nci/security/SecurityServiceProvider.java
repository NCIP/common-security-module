package gov.nih.nci.security;
import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import gov.nih.nci.security.authentication.AuthenticationManagerFactory;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;







/**
 * This class gives the appropriate managers for a application.  The three mnagers
 * that it gives are AuthorizationManager,UserProvisioningManager and
 * AuthenticationManager
 * @version 1.0
 * @created 03-Dec-2004 1:17:51 AM
 */
public class SecurityServiceProvider {

	public AuthorizationManagerFactory m_AuthorizationManagerFactory;
	public AuthenticationManagerFactory m_AuthenticationManagerFactory;
	public UserProvisioningManager m_UserProvisioningManager;

	public SecurityServiceProvider(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * This method will read a config file where name of the application context is
	 * there. It will make applicationcontext and keep it UserProvisioningManager
	 * @param contextName
	 * 
	 */
	public static UserProvisioningManager getUserProvisioningManger(String contextName){
		
		UserProvisioningManager userProvisioingManager = null;	
		UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName);		
		userProvisioingManager = (UserProvisioningManager)userProvisioningManagerImpl;			
		return userProvisioingManager;
	}

	/**
	 * @param applicationContextName
	 * 
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName){
		
		return (AuthorizationManager)(getUserProvisioningManger(applicationContextName));
	}

	/**
	 * @param applicationContextName
	 * 
	 */
	public static AuthenticationManager getAutheticationManager(String applicationContextName){
		return null;
	}
	

}