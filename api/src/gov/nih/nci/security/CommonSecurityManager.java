/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security;

/**
 * This class provides the single common interface for the entire Common Security Module.
 * It exposes all the authentication and the authorization services provided the Common Security Module. 
 * The client application have make an instance of this class before utilising any of the services. 
 * This class provides services to authenticate the user credentials against the registered 
 * credentials providers.
 * <p>
 * It uses the {@link SecurityServiceProvider} class to obtain the implementations of the
 * Authentication or Authorization interfaces and delegates the corresponding services to them.
 * Based on whether the application wants to use the default implementation of the Authentication 
 * Manager provided or its custom Authentication Manager the corresponding class object is obtained.
 * 
 * @author Kunal Modi (Ekagra Software Technologies)
 *
 */
public class CommonSecurityManager
{
	/**
	 * Default Constructor
	 */
	public CommonSecurityManager()
	{
	}
	
	/**
	 * Accepts the user credentials  and the Application Context/Name from the calling application and authenticates 
	 * the same for the application. If the client application wants to use the default implementation, then JAAS is used to authenticate
	 * the user against the registered credential providers. However if the client application wants to use
	 * its custom implementation then the corresponding <code>login</code> method of the provide class is invoked and the result of authentication is returned.
	 *
	 * @param applicationContextName The name or context of the calling application. 
	 * 			NOTE: that the application name/context should be same as those 
	 * 			configured in the configuration/property files
	 * @param userName The user-entered id provided by the calling application. 
	 * It should be the unique qualifier which can identify a particular user of the application
	 * @param password The user-entered password provided by the calling application.
	 * It should be provided in a non-encrypted format as simple {@link String} object.
	 * @return <code>TRUE</code> if the authentication was sucessful using the provided user 
	 * 		   	credentials and <code>FALSE</code> if the authentication fails.
	 */
	public boolean login (String applicationContextName, String userName, String password)
	{
		AuthenticationManager authenticationManager = SecurityServiceProvider.getAuthenticationManager(applicationContextName);
		return authenticationManager.login(userName,password);
	}
}
