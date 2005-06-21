/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;



import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * This class is a wrapper for CSM security.
 */
public class SecurityEnabler {

	private static String applicationContextName = null;
	private static AuthorizationManager authorizationManager = null;
	private static AuthenticationManager authenticationManager = null;

	
	
	public SecurityEnabler(){
		ApplicationConfiguration ac = ApplicationConfiguration.getInstance();
		applicationContextName =ac.getProperty("applicationName");
	}

	private AuthorizationManager getAuthorizationManager(){
		if (null == authorizationManager)
		try {
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(applicationContextName);
		} catch (CSException e) {
			e.printStackTrace();
		}
		return authorizationManager;
	}
	private AuthenticationManager getAuthenticationManager(){
		if (null == authenticationManager)
			try {
				authenticationManager = SecurityServiceProvider.getAuthenticationManager(applicationContextName);
			} catch (CSException e) {
				e.printStackTrace();
			}
			return authenticationManager;
	}

	public String authenticate(String userId, String password){
		/**
		 * Call Authentication Manager here.
		 */
	  return "123";
	}
	
	public boolean isUserInSession(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		return sm.isUserInSession(sessionKey);
	}
	public boolean hasAuthorization(String sessionKey,String protectionElementName,String privilege){
		SessionManager sm = SessionManager.getInstance();
		UserSession us = (UserSession)sm.getSession(sessionKey);
		String userId = us.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		return true;
	}
	
	public int getSecurityLevel(){
		return 1;
	}
	
	public void logOut(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		sm.killSession(sessionKey);
	}
	public static void main(String[] args) {
	}
}
