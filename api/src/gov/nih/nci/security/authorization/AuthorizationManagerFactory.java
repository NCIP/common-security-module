package gov.nih.nci.security.authorization;
import gov.nih.nci.security.AuthorizationManager;





/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:48 AM
 */
public class AuthorizationManagerFactory {

	public AuthorizationManager m_AuthorizationManager;

	public AuthorizationManagerFactory(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * @param applicationContextName
	 * 
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName){
		return null;
	}

}