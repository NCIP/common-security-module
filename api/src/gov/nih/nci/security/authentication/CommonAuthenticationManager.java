/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.authentication.callback.CSMCallbackHandler;

/**
 * This is the default implmentation of the {@link AuthenticationManager} interface.
 * It provides methods to perform the authentication using the  provided user credentials.
 * It uses JAAS to perform this authentication. This class accepts the Application Context/Name,
 * and instantiate a corresponding {@link LoginContext} for the same. It accepts the
 * user credentials and creates a {@link CallbackHandler} class using the same. Using the 
 * {@link LoginContext} and the {@link CallbackHandler} created JAAS instatiate the configured
 * {@link LoginModule} for the application and uses the same to authenticate the user credentials
 * against the credential providers.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 */
public class CommonAuthenticationManager implements AuthenticationManager{

	private String applicationContextName = null;
	
	/**
	 * This method accepts the user credentials as parameter and uses the same to authenticate the user
	 * against the registered credential providers. It creates an instance of the  {@link CSMCallbackHandler} class 
	 * and populates the same with the user credentials. It also creates a JAAS {@link LoginContext} class using the 
	 * Application Context/Name as parameter. It then calls the <code>login</code> method on the {@link LoginContext} class.
	 * The login Method then uses the registered {@link LoginModule} for the given Application Context/Name in the JAAS policy file
	 * and authenticate the user credentails. There can be more than one {@link LoginModule} class registered for the application.
	 * 
	 * @see gov.nih.nci.security.AuthenticationManager#login(java.lang.String, java.lang.String)
	 */
	public boolean login(String userName, String password)
	{
		boolean loginSuccessful = false;
		try
		{
			CSMCallbackHandler csmCallbackHandler = new CSMCallbackHandler(userName, password);
			LoginContext loginContext = new LoginContext(applicationContextName, csmCallbackHandler);
			loginContext.login();
			loginSuccessful = true;
		}
		catch (LoginException le)
		{
			System.out.println("ERROR: Login Credentials Failed");
			le.printStackTrace();
			loginSuccessful = false;
		}
		return loginSuccessful;
	}

	/**
	 * 
	 * @see gov.nih.nci.security.AuthenticationManager#initialize(java.lang.String)
	 */
	public void initialize(String applicationContextName) 
	{
		this.applicationContextName = applicationContextName;		
	}

	/**
	 * @see gov.nih.nci.security.AuthenticationManager#setApplicationContextName(java.lang.String)
	 */
	public void setApplicationContextName(String applicationContextName) 
	{
		this.applicationContextName = applicationContextName;		
	}

	/**
	 * @see gov.nih.nci.security.AuthenticationManager#getApplicationContextName()
	 */
	public String getApplicationContextName() 
	{
		return this.applicationContextName;
	}

	/**
	 * @see gov.nih.nci.security.AuthenticationManager#getAuthenticatedObject()
	 */
	public Object getAuthenticatedObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
