package gov.nih.nci.security;
import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import gov.nih.nci.security.authentication.AuthenticationManagerFactory;
import gov.nih.nci.security.exceptions.CSException;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;


/**
 * This class gives the appropriate managers for a application.  The three mnagers
 * that it gives are AuthorizationManager,UserProvisioningManager and
 * AuthenticationManager
 * @version 1.0
 * created 03-Dec-2004 1:17:51 AM
 */
public class SecurityServiceProvider {

	public SecurityServiceProvider(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * This method will provides the default implementation of the {@link UserProvisioningManager}. This Manager
	 * is used only by the User Provisioning Tool and is not available for the applications to use at runtime. The 
	 * methods exposed 
	 * @param contextName
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName) throws CSException{
		
		UserProvisioningManager userProvisioningManager = null;
		try{
			UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName);		
			userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		}catch(Exception ex)
		{
			throw new CSException("Could  not initialize Manager",ex);
		}
		return userProvisioningManager;
	}

	/**
	 * Obtains an instance of {@link AuthorizationManager} implementation from the
	 * {@link AuthorizationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authorization Manager Class is registered for the application then the
	 * {@link AuthorizationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link UserProvisioningManagerImpl} class
	 * is instantiated and returned. This manager should be used by the Client Applications which
	 * needs to use the Authorization service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthorizationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthorizationManager} could not be obtained
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName)throws CSException{
		
		return AuthorizationManagerFactory.getAuthorizationManager(applicationContextName);
	}

	/**
	 * Obtains an instance of {@link AuthenticationManager} implementation from the
	 * {@link AuthenticationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authentication Manager Class is registered for the application then the
	 * {@link AuthenticationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link gov.nih.nci.security.authentication.CommonAuthenticationManager} class
	 * is instantiated and returned.This manager should be used by the Client Applications which
	 * needs to use the Authentication service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthenticationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthenticationManager} could not be obtained
	 */
	public static AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException
	{
		return AuthenticationManagerFactory.getAuthenticationManager(applicationContextName);		
	}

}