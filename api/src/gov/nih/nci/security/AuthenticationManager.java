/*
 * Created on Nov 11, 2004
 *
 */
package gov.nih.nci.security;

import gov.nih.nci.security.exceptions.CSException;

import javax.security.auth.Subject;

/**
 * 
 * This <code>Authentication Manager</code> interface provides all the
 * authentication methods and services offered by the Common Security Module.
 * This interface defines the contract that any class which wants to acts as an
 * authentication manager should follow to be able to fit in the Common Security
 * Framework. It defines the methods which are required for the purpose of
 * authenticating a user against the configured credential providers. This
 * interface by default is implemented by the {@link gov.nih.nci.security.authentication.CommonAuthenticationManager}. If the client application wants to use its own
 * Authentication Class, then it should implement the
 * <code>AuthenticationManager</code> interface. Also an entry should be configured
 * in the <code>ApplicationServiceConfig</code> file against the Application
 * Context Name regsitering the class, which it wants to use, as shown below
 * <p>
 * <blockquote>
 * 
 * <pre>
 *		&lt;application&gt;
 *	   		&lt;context-name&gt;
 *	   			FooApplication
 *	      	&lt;/context-name&gt;
 *	      	&lt;authentication&gt;
 *		      	&lt;authentication-provider-class&gt;
 *	     			com.Foo.AuthenticationManagerClass
 *	     		&lt;/authentication-provider-class&gt;
 *			&lt;/authentication&gt;
 *			:
 *			:
 *		&lt;/application&gt;
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * If the client application wants to use just the authentication service then it can
 * obtain the implementation of the <code>AuthenticationManager</code> interface from the 
 * {@link SecurityServiceProvider} class.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.) 
 * 
 */
public interface AuthenticationManager {
	/**
	 * Accepts the user credentials from the calling application and authenticates 
	 * the same for the application. If the client application wants to use the default implementation, the JAAS is used to authenticate
	 * the user against the registered credential providers. However if the client application wants to use
	 * its custom implementation then the corresponding login method is invoked and the result of authentication is returned.
	 * Also before calling the <code>login</code> method the <code>initialize</code> method should be invoked setting the Application Context/Name
	 *
	 * @param userName The user-entered id provided by the calling application. 
	 * It should be the unique qualifier which can identify a particular user of the application
	 * @param password The user-entered password provided by the calling application.
	 * It should be provided in a non-encrypted format as simple {@link String} object.
	 * @return <code>TRUE</code> if the authentication was sucessful using the provided user 
	 * 		   	credentials and <code>FALSE</code> if the authentication fails.
	 * @throws CSException
	 */
	public boolean login(String userName, String password) throws CSException;

	/**
	 * Initializes the class and sets the passed Application Context/Name within the instance of the implemented class. This method
	 * should be immediately invoked after creating instantiating the class.
	 * @param applicationContextName The name or context of the calling application. 
	 * 			NOTE: that the application name/context should be same as those 
	 * 			configured in the configuration/property files
	 */
	public void initialize(String applicationContextName);

	/**
	 * Sets the passed Application Context/Name within the instance of the implemented class
	 * <code>applicationContextName</code> within the instance of the implemented class. This method
	 * should be immediately invoked after creating instantiating the implementation class. 
	 * @param applicationContextName The name or context of the calling application. 
	 * 			NOTE: that the application name/context should be same as those 
	 * 			configured in the configuration/property files
	 */
	public void setApplicationContextName(String applicationContextName);

	/**
	 * Returns the Application Context/Name which is stored within the class
	 * @return the Application Context/Name 
	 */
	public String getApplicationContextName();

	/**
	 * NOTE: This method is not implemented in the current phase
	 * @return A null object
	 */
	public Object getAuthenticatedObject();
	
	/**
	 * NOTE: This method is not implemented in the current phase
	 * @return A null object
	 */
	public Subject getSubject();
}