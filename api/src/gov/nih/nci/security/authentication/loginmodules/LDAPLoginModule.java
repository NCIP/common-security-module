/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.loginmodules;

import gov.nih.nci.security.authentication.helper.LDAPHelper;

import java.util.Hashtable;
import java.util.Map;

/**
 * This class is the implementation of the LoginModule interface and is used to 
 * connect to LDAP to authenticate the user using the passed credentials. The
 * class is initialized and invoked by the JAAS based on the configuration 
 * settings.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class LDAPLoginModule extends CSMLoginModule
{

	/**
	 * This is an internal method which invokes the helper method from the
	 * LDAPHelper class. It returns TRUE is the authentication is sucessful.
	 * @param user the user entered user name provided by the calling application
	 * @param password the user entered password provided by the calling application
	 * @return TRUE if the authentication was sucessful using the provided user 
	 * credentials and FALSE if the authentication fails
	 */
	protected boolean validate (Map options, String user, char[] password)
	{
		return LDAPHelper.authenticate (new Hashtable(options), user, password);
	}
	
}
