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

	public String authenticateYY(String userId, String password) throws ApplicationException{
		/**
		 * Call Authentication Manager here.
		 */
		String sessionKey =null;
		boolean authenticated = true;
		
		if(authenticated){
			SessionManager sm = SessionManager.getInstance();
			sessionKey = sm.initSession(userId);
		}
		System.out.println("The security system has checked for authentication for:"+userId);
		System.out.println("The system has generated sessionKey:"+sessionKey);
	  return sessionKey;
	}
	
	public String authenticate(String userId, String password) throws ApplicationException{
		/**
		 * Call Authentication Manager here.
		 */
		String sessionKey =null;
		boolean authenticated = false;
		if(null==authenticationManager){
			authenticationManager = getAuthenticationManager();
		}
		try{
			authenticated = authenticationManager.login(userId,password);
		}catch(Exception ex){
			authenticated=false;
			throw new ApplicationException("Could not authenticate the user");
		}
		if(authenticated){
			System.out.println("User:"+userId+ " is authenticated!");
			SessionManager sm = SessionManager.getInstance();
			sessionKey = sm.initSession(userId);
			System.out.println("Created sessionkey="+sessionKey);
		}
	  return sessionKey;
	}
	
	public boolean isUserInSession(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		return sm.isUserInSession(sessionKey);
	}
	public boolean hasAuthorizationYY(String sessionKey,String protectionElementName,String privilege){
		boolean authorized = true;
		if(this.isBlank(sessionKey)){
			authorized = false;
			return authorized;
		}
		SessionManager sm = SessionManager.getInstance();
		
		UserSession us = (UserSession)sm.getSession(sessionKey);
		String userId = us.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		System.out.println("The security system has checked authorization for:"+userId);
		System.out.println("The security system has checked authorization for protectionElement:"+protectionElementName);
		
		return authorized;
	}
	
	public boolean hasAuthorization(String sessionKey,String protectionElementName,String privilege){
		boolean authorized = false;
		if(this.isBlank(sessionKey)){
			authorized = false;
			System.out.println("User is not logged in !");
			return authorized;
		}
		SessionManager sm = SessionManager.getInstance();
		UserSession us = (UserSession)sm.getSession(sessionKey);
		String userId = us.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		if(null==authorizationManager){
			authorizationManager= getAuthorizationManager();
		}
		try{
			System.out.println("Checking the permission for:"+userId);
			authorized = authorizationManager.checkPermission(userId,protectionElementName,privilege);
			System.out.println("Result for permission on "+protectionElementName+":"+authorized);
		}catch(Exception ex){
			authorized = false;
			System.out.println("User:"+userId+" does not have authorization to do this");
		}
		return authorized;
	}
	
	public int getSecurityLevel(){
		return 1;
	}
	
	public void logOut(String sessionKey){
		SessionManager sm = SessionManager.getInstance();
		sm.killSession(sessionKey);
		System.out.println("Session killed for sessionId:"+sessionKey);
	}
	
	private boolean isBlank(String str){
	     boolean test = false;
	     
	     if(str==null){
	        test= true;
	     }else{
	     	String str1 = str.trim();
	       if(str1.equals("")){
	         test = true;
	       }
	     }
	     return test;
	  }
	public static void main(String[] args) {
	}
}
