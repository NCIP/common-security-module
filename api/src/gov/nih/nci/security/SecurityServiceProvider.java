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
	 * Obtains an instance of {@link AuthenticationManager} implementation from the
	 * {@link AuthenticationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authentication Manager Class is registered for the application then the
	 * {@link AuthenticationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link CommonAuthenticationManager} class
	 * is instantiated and returned.
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthenticationManager} interface is returned based on the
	 * configuration for the application
	 */
	public static AuthenticationManager getAuthenticationManager(String applicationContextName)
	{
		return AuthenticationManagerFactory.getAuthenticationManager(applicationContextName);		
	}
	

}