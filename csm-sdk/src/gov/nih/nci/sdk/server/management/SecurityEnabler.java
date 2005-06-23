/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;



import gov.nih.nci.sdk.common.ApplicationException;
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

	public String authenticate(String userId, String password) throws ApplicationException{
		/**
		 * Call Authentication Manager here.
		 */
		String sessionKey =null;
		boolean authenticated = false;
		try{
			authenticated = authenticationManager.login(userId,password);
		}catch(Exception ex){
			authenticated=false;
			throw new ApplicationException("Could not authenticate the user");
		}
		if(authenticated){
			SessionManager sm = SessionManager.getInstance();
			sessionKey = sm.initSession(userId);
		}
	  return sessionKey;
	}
	
	public boolean isUserInSession(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		return sm.isUserInSession(sessionKey);
	}
	public boolean hasAuthorization(String sessionKey,String protectionElementName,String privilege){
		boolean authorized = false;
		SessionManager sm = SessionManager.getInstance();
		UserSession us = (UserSession)sm.getSession(sessionKey);
		String userId = us.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		try{
			authorized = authorizationManager.checkPermission(userId,protectionElementName,privilege);
		}catch(Exception ex){
			authorized = false;
		}
		return authorized;
	}
	
	public int getSecurityLevel(){
		return 0;
	}
	
	public void logOut(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		sm.killSession(sessionKey);
	}
	public static void main(String[] args) {
	}
}
