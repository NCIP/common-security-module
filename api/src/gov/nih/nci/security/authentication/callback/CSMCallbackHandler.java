/*
 * Created on Oct 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.callback;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Category;

/**
 * 
 * This class implements the CallbackHandler interface of JAAS. It provides
 * methods for the JAAS service to create and populate the callbacks based on 
 * the data provided.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class CSMCallbackHandler implements CallbackHandler {

	private static final Category log = Category.getInstance(CSMCallbackHandler.class);
	
	
	private String _userID;
	private String _password;

	/**
	 * This constructor of the class instantiates the an instance of the 
	 * CSMCallbackHandler and stores the user entered userID and password which 
	 * are provided by the calling application.
	 * 
	 */
	public CSMCallbackHandler(String userID, String password) {
		super();
		_userID = userID;
		_password = password;
	}

	/**
	 * Populates the callbacks with the data provided during the instantiation 
	 * of the class. It creates a NameCallback and PasswordCallback which holds
	 * the user entered userID and password which are provided by the calling 
	 * application.
	 * 
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback) callbacks[i];
				nameCallback.setName(_userID);
			} else if (callbacks[i] instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
				passwordCallback.setPassword(_password.toCharArray());
			} else {
				if (log.isDebugEnabled())
					log.debug("Authentication||"+_userID+"|handle|Failure| Error in initializing the CallBack Handler |" );				
				throw new UnsupportedCallbackException(callbacks[i], "Error in initializing the CallBack Handler");
			}
		}
	}

}