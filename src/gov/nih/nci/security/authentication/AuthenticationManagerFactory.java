package gov.nih.nci.security.authentication;
import gov.nih.nci.security.AuthenticationManager;





/**
 * AuthenticationServiceFactory Object will read a config file and accordingly get
 * the appropriate implmentation for authentication. It returrns the
 * AuthenticationService, which is an abstract class.
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public class AuthenticationManagerFactory {

	public AuthenticationManager m_AuthenticationManager;

	public AuthenticationManagerFactory(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * @param applicationName
	 * 
	 */
	public static AuthenticationManager getAuthenticationManager(String applicationName){
		return null;
	}

}