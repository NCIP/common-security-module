/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.helper;

import gov.nih.nci.security.constants.Constants;

import java.security.Security;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * This is a helper class which is used to perform all LDAP operations like
 * connecting to the LDAP server, executing the LDAP queries etc. This is a static class and
 * provides a single helper method.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class LDAPHelper {


	/**
	 * Default Private Class Constructor
	 *  
	 */
	private LDAPHelper() {
	}

	/**
	 * Accepts the connection properties as well as the user id and password. It
	 * opens a connection to the database and fires a the query to find. If the
	 * query was successful then it returns TRUE else it returns FALSE
	 * 
	 * @param connectionProperties table containing details for establishing connection like the 
	 * the url of the ldap server and the seach base which is to be used as the starting 
	 * point
	 * @param userID the user entered user name provided by the calling application
	 * @param password the user entered password provided by the calling application
	 * @return TRUE if the authentication was sucessful using the provided user
	 * credentials and FALSE if the authentication fails
	 */
	public static boolean authenticate(Hashtable connectionProperties, String userID, char[] password) {
		Hashtable environment = new Hashtable();
		setLDAPEnvironment(environment, connectionProperties);
		return ldapAuthenticateUser(environment, connectionProperties, userID,
				new String(password));

	}

	/**
	 * This methods clears and reloads the enviroment variables from the connection properties
	 * supplied. It loads the Initial context, provider URL and the connection details.
	 * @param environment This
	 * @param connectionProperties
	 */
	private static void setLDAPEnvironment(Hashtable environment,
			Hashtable connectionProperties) {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		environment.clear();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT);
		environment.put(Context.PROVIDER_URL, connectionProperties.get(Constants.LDAP_HOST));
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PROTOCOL, "ssl");
	}

	/**
	 * This method returns the the Fully Distinguished Name obtained from the
	 * Directory Server for the given user id. It accepts the evironment variables
	 * and connection properties to connect to the LDAP server. It then obtains the 
	 * Fully Distinguished User Name for the user id provided from the LDAP server
	 * 
	 * @param environment The environment variables which are used to connect to LDAP
	 * @param connectionProperties The LDAP url and search base used to point to LDAP
	 * @param userName The user name which is to be authenticated
	 * @return The Fully Distinguished User Name obtained from the LDAP for the passed user name
	 */
	private static String getFullyDistinguishedName(Hashtable environment,
			Hashtable connectionProperties, String userName) {
		String[] attributeIDs = { (String) connectionProperties.get(Constants.LDAP_USER_ID_LABEL) };
		String searchFilter = "(" + (String) connectionProperties.get(Constants.LDAP_USER_ID_LABEL) + "=" + userName + "*)";

		try {
			DirContext dirContext = new InitialDirContext(environment);

			SearchControls searchControls = new SearchControls();
			searchControls.setReturningAttributes(attributeIDs);
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String fullyDistinguishedName = null;
			NamingEnumeration searchEnum = dirContext.search(
					(String) connectionProperties.get(Constants.LDAP_SEARCHABLE_BASE),
					searchFilter, searchControls);
			dirContext.close();

			while (searchEnum.hasMore()) {
				SearchResult searchResult = (SearchResult) searchEnum.next();
				fullyDistinguishedName = searchResult.getName()	+ "," + (String) connectionProperties.get(Constants.LDAP_SEARCHABLE_BASE);
				System.out.println("USER NAME : " + searchResult.getName() + " " + "Dn = " + fullyDistinguishedName);
				return fullyDistinguishedName;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR : CONNECTION TO LDAP FAILED");
			return null;
		}
		return null;
	}

	/**
	 * Return the result of user authentication with LDAP server
	 * 
	 * @param loginName the login name of the user
	 * @param passwd the password of the user
	 * @return true for successful authentication <br>
	 *         false for failed authentication
	 */
	private static boolean ldapAuthenticateUser(Hashtable environment, Hashtable connectionProperties, String userName, String password)
	{
		String fullyDistinguishedName = getFullyDistinguishedName(environment, connectionProperties, userName);

		if (null == fullyDistinguishedName) {
			System.out.println("ERROR: USER NAME CANNOT BE OBTAINED");
			return false;
		}

		try {
			environment.put(Context.SECURITY_PRINCIPAL, fullyDistinguishedName);
			environment.put(Context.SECURITY_CREDENTIALS, password);
			DirContext initialDircontext = new InitialDirContext(environment);
			System.out.println("SUCCESS: USER IS AUTHENTICATED");
			initialDircontext.close();
			setLDAPEnvironment(environment, connectionProperties);
			return true;
		} catch (Exception ne) {
			setLDAPEnvironment(environment, connectionProperties);
			ne.printStackTrace();
			return false;
		}
	}
}