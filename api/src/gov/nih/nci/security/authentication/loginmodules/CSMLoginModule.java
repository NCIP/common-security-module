/*
 * Created on Nov 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.loginmodules;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * This is the abstract base class which provides the common methods to be used for
 * the authentication services. The individual Login Modules should extend this class. This class 
 * retrieves the credentials from the {@link CallbackHandler} passed. It also stored the login configuration
 * data read from the JAAS policy files and stores it. These configurations are then used by the individual
 * Login Modules to connect to the respective credential providers and authenticate the user credentials
 *  
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 */
public abstract class CSMLoginModule implements LoginModule
{
	private Subject 		subject;
	private CallbackHandler callbackHandler;
	private Map 			sharedState;
	private Map 			options;
	

	private String 		userID;
	private char[]		password;
	private boolean 	loginSuccessful = false;
	
	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		this.subject 			= subject;
		this.callbackHandler	= callbackHandler;
		this.sharedState 		= sharedState;
		this.options 			= options;
	}
	
	/**
	 * Retrieves the user credentials from the CallBacks and tries to validate 
	 * them against the database. It retrieves userID and password from the 
	 * CallbackHandler. It uses helper class to perform the actual authentication 
	 * operations and access the user record. This method returns a true if
	 * the user authentication was sucessful else it throws a Login Exception.
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	public boolean login() throws LoginException
	{
		if (callbackHandler == null)
			throw new LoginException("ERROR: CallbackHandler cannot be null");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("userid: ");
		callbacks[1] = new PasswordCallback("password: ", false);

		try
		{
			callbackHandler.handle(callbacks);
			userID = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();

			if (tmpPassword == null)
			{
				// treat a NULL password as an empty password
				tmpPassword = new char[0];
			}
			password = new char[tmpPassword.length];
			System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
			((PasswordCallback) callbacks[1]).clearPassword();
		}
		catch (java.io.IOException e)
		{
			throw new LoginException("Error: " + e.getMessage());
		}
		catch (UnsupportedCallbackException e)
		{
			throw new LoginException("Error: " + e.getMessage());
		}

		//now validate user
		if (validate(options, userID, password))
		{
			loginSuccessful = true;
		}
		else
		{
			// clear the values			
			loginSuccessful = false;
			userID 			= null;
			password 		= null;
			throw new FailedLoginException("Invalid userid or password");
		}
		return loginSuccessful;
	}
	
	
	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	public boolean commit() throws LoginException
	{
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	public boolean abort() throws LoginException
	{
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	public boolean logout() throws LoginException
	{
		subject.getPrincipals().clear();
		loginSuccessful	= false;
		userID			= null;
		password		= null;

		return true;
	}

	/**
	 * This is an internal method which is to be implemented by individual login
	 * modules. This method accepts the user credentials then performs the actual 
	 * authentication against the individual credential providers.
	 * @param user the user entered user name provided by the calling application
	 * @param password the user entered password provided by the calling application
	 * @return TRUE if the authentication was sucessful using the provided user 
	 * 		   	credentials and FALSE if the authentication fails
	 */
	protected abstract boolean validate(Map options, String user, char[] password);

}
