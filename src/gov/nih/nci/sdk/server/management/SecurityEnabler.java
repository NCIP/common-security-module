/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * This class is a wrapper for CSM security.
 */
public class SecurityEnabler {
	
	
	public SecurityEnabler(String applicationContextName){
		
	}

	public boolean authenticate(String userId, String password){
		/**
		 * Call Authentication Manager here.
		 */
	  return true;
	}
	
	public boolean isUserInSession(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		return sm.isUserInSession(sessionKey);
	}
	public boolean hasAuthorization(String protectionElementName,String privilege,String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		UserSession us = (UserSession)sm.getSession(sessionKey);
		String userId = us.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		return true;
	}
	
	public void logOut(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		sm.killSession(sessionKey);
	}
	public static void main(String[] args) {
	}
}
